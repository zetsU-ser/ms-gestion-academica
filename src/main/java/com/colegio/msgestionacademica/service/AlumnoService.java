package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.Alumno;
import com.colegio.msgestionacademica.repository.AlumnoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private NotaService notaService;
    
    @Autowired
    private AlumnoCursoService alumnoCursoService;

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno getAlumnoById(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        return alumno;
    }

    public Alumno createAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno updateAlumno(Alumno alumno) {
        Alumno existingAlumno = alumnoRepository.findById(alumno.getId()).orElse(null);
        if (existingAlumno != null) {
            if (alumno.getRut() != null) {
                existingAlumno.setRut(alumno.getRut());
            }
            if (alumno.getNombre() != null) {
                existingAlumno.setNombre(alumno.getNombre());
            }
            if (alumno.getApellido() != null) {
                existingAlumno.setApellido(alumno.getApellido());
            }
            if (alumno.getNombreApoderado() != null) {
                existingAlumno.setNombreApoderado(alumno.getNombreApoderado());
            }
            if (alumno.getEmailApoderado() != null) {
                existingAlumno.setEmailApoderado(alumno.getEmailApoderado());
            }
            if (alumno.getTelefonoApoderado() != null) {
                existingAlumno.setTelefonoApoderado(alumno.getTelefonoApoderado());
            }
            return alumnoRepository.save(existingAlumno);
        }
        return null;
    }

    public void deleteAlumnoById(Long id) {
        alumnoCursoService.deleteByAlumnoId(id);
        notaService.deleteByAlumnoId(id);
        alumnoRepository.deleteById(id);
    }
}