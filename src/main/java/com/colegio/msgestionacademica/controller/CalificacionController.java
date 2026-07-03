package com.colegio.msgestionacademica.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.msgestionacademica.model.Alumno;
import com.colegio.msgestionacademica.model.CargaAcademica;
import com.colegio.msgestionacademica.model.Evaluacion;
import com.colegio.msgestionacademica.model.Nota;
import com.colegio.msgestionacademica.model.dto.AlumnoNotasDTO;
import com.colegio.msgestionacademica.model.dto.CalificacionBatchDTO;
import com.colegio.msgestionacademica.repository.AlumnoRepository;
import com.colegio.msgestionacademica.repository.CargaAcademicaRepository;
import com.colegio.msgestionacademica.repository.EvaluacionRepository;
import com.colegio.msgestionacademica.repository.NotaRepository;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    @Autowired
    private CargaAcademicaRepository cargaRepository;
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostMapping("/batch")
    @Transactional
    public ResponseEntity<?> createBatch(@RequestBody CalificacionBatchDTO batch) {
        // Encontrar la CargaAcademica del profesor en ese curso
        List<CargaAcademica> cargas = cargaRepository.findByCursoIdAndDocenteId(batch.getCurso_id(), batch.getProfesor_id());
        if (cargas == null || cargas.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "No se encontró asignatura asignada para este profesor en este curso."));
        }
        
        // Usamos la primera coincidencia
        CargaAcademica carga = cargas.get(0);

        // Buscar o crear 3 Evaluaciones obligatorias para esta carga
        List<Evaluacion> evs = evaluacionRepository.findByCargaAcademicaIdOrderByIdAsc(carga.getId());
        if (evs == null) {
            evs = new ArrayList<>();
        }
        
        if (evs.size() < 3) {
            for (int i = evs.size() + 1; i <= 3; i++) {
                Evaluacion ev = new Evaluacion();
                ev.setNombre("Evaluación " + i);
                ev.setFecha(LocalDate.now());
                ev.setPonderacion(33.3);
                ev.setCargaAcademica(carga);
                evaluacionRepository.save(ev);
                evs.add(ev);
            }
        }

        // Guardar las Notas de cada alumno
        if (batch.getAlumnos() != null) {
            for (AlumnoNotasDTO an : batch.getAlumnos()) {
                Alumno alumno = alumnoRepository.findById(an.getAlumno_id()).orElse(null);
                if (alumno == null) continue;

                saveNota(alumno, evs.get(0), an.getNota1());
                saveNota(alumno, evs.get(1), an.getNota2());
                saveNota(alumno, evs.get(2), an.getNota3());
            }
        }

        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }

    private void saveNota(Alumno alumno, Evaluacion ev, Double valor) {
        if (valor == null) return;
        Nota nota = notaRepository.findByAlumnoIdAndEvaluacionId(alumno.getId(), ev.getId());
        if (nota == null) {
            nota = new Nota();
            nota.setAlumno(alumno);
            nota.setEvaluacion(ev);
        }
        nota.setValor(valor);
        notaRepository.save(nota);
    }
    
    @GetMapping("/curso/{cursoId}/docente/{docenteId}")
    public ResponseEntity<?> getByCursoAndDocente(@PathVariable Long cursoId, @PathVariable Long docenteId) {
        List<CargaAcademica> cargas = cargaRepository.findByCursoIdAndDocenteId(cursoId, docenteId);
        if (cargas == null || cargas.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        
        CargaAcademica carga = cargas.get(0);
        List<Evaluacion> evs = evaluacionRepository.findByCargaAcademicaIdOrderByIdAsc(carga.getId());
        if (evs == null || evs.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        Map<Long, AlumnoNotasDTO> map = new HashMap<>();
        
        for (int i = 0; i < Math.min(evs.size(), 3); i++) {
            Evaluacion ev = evs.get(i);
            List<Nota> notasEv = notaRepository.findAll(); // Simplified for now, or we can use custom query
            for (Nota n : notasEv) {
                if (n.getEvaluacion() != null && n.getEvaluacion().getId().equals(ev.getId()) && n.getAlumno() != null) {
                    Long alId = n.getAlumno().getId();
                    map.putIfAbsent(alId, new AlumnoNotasDTO());
                    AlumnoNotasDTO dto = map.get(alId);
                    dto.setAlumno_id(alId);
                    if (i == 0) dto.setNota1(n.getValor());
                    else if (i == 1) dto.setNota2(n.getValor());
                    else if (i == 2) dto.setNota3(n.getValor());
                }
            }
        }
        
        return ResponseEntity.ok(new ArrayList<>(map.values()));
    }
}
