package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.CargaAcademica;
import com.colegio.msgestionacademica.repository.CargaAcademicaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CargaAcademicaService {
    @Autowired
    private CargaAcademicaRepository cargaAcademicaRepository;

    @Autowired
    private EvaluacionService evaluacionService;

    public List<CargaAcademica> getAllCargasAcademicas() {
        return cargaAcademicaRepository.findAll();
    }

    public CargaAcademica getCargaAcademicaById(Long id) {
        CargaAcademica cargaAcademica = cargaAcademicaRepository.findById(id).orElse(null);
        return cargaAcademica;
    }

    public CargaAcademica createCargaAcademica(CargaAcademica cargaAcademica) {
        return cargaAcademicaRepository.save(cargaAcademica);
    }

    public CargaAcademica updateCargaAcademica(CargaAcademica cargaAcademica) {
        CargaAcademica existingCargaAcademica = cargaAcademicaRepository.findById(cargaAcademica.getId()).orElse(null);
        if (existingCargaAcademica != null) {
            if (cargaAcademica.getDiaSemana() != null) {
                existingCargaAcademica.setDiaSemana(cargaAcademica.getDiaSemana());
            }
            if (cargaAcademica.getBloqueHorario() != null) {
                existingCargaAcademica.setBloqueHorario(cargaAcademica.getBloqueHorario());
            }
            if (cargaAcademica.getCurso() != null) {
                existingCargaAcademica.setCurso(cargaAcademica.getCurso());
            }
            if (cargaAcademica.getDocente() != null) {
                existingCargaAcademica.setDocente(cargaAcademica.getDocente());
            }
            if (cargaAcademica.getAsignatura() != null) {
                existingCargaAcademica.setAsignatura(cargaAcademica.getAsignatura());
            }
            return cargaAcademicaRepository.save(existingCargaAcademica);
        }
        return null;
    }

    public void deleteCargaAcademicaById(Long id) {
        evaluacionService.deleteByCargaAcademicaId(id);
        cargaAcademicaRepository.deleteById(id);
    }

    public void deleteByAsignaturaId(Long asignaturaId) {
        List<CargaAcademica> cargasAcademica = cargaAcademicaRepository.findAll();
        for (CargaAcademica cargaAcademica : cargasAcademica) {
            if (cargaAcademica.getAsignatura() != null && cargaAcademica.getAsignatura().getId().equals(asignaturaId)) {
                deleteCargaAcademicaById(cargaAcademica.getId());
            }
        }
    }

    public void deleteByDocenteId(Long docenteId) {
        List<CargaAcademica> cargasAcademica = cargaAcademicaRepository.findAll();
        for (CargaAcademica cargaAcademica : cargasAcademica) {
            if (cargaAcademica.getDocente() != null && cargaAcademica.getDocente().getId().equals(docenteId)) {
                deleteCargaAcademicaById(cargaAcademica.getId());
            }
        }
    }

    public void deleteByCursoId(Long cursoId) {
        List<CargaAcademica> cargasAcademica = cargaAcademicaRepository.findAll();
        for (CargaAcademica cargaAcademica : cargasAcademica) {
            if (cargaAcademica.getCurso() != null && cargaAcademica.getCurso().getId().equals(cursoId)) {
                deleteCargaAcademicaById(cargaAcademica.getId());
            }
        }
    }
}