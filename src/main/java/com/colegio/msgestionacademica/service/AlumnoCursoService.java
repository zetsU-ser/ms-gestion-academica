package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.AlumnoCurso;
import com.colegio.msgestionacademica.repository.AlumnoCursoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class AlumnoCursoService {
    @Autowired
    private AlumnoCursoRepository alumnoCursoRepository;

    public List<AlumnoCurso> getAllAlumnosCurso() {
        return alumnoCursoRepository.findAll();
    }

    public AlumnoCurso getAlumnoCursoById(Long id) {
        AlumnoCurso alumnoCurso = alumnoCursoRepository.findById(id).orElse(null);
        return alumnoCurso;
    }

    public AlumnoCurso createAlumnoCurso(AlumnoCurso alumnoCurso) {
        return alumnoCursoRepository.save(alumnoCurso);
    }

    public AlumnoCurso updateAlumnoCurso(AlumnoCurso alumnoCurso) {
        AlumnoCurso existingAlumnoCurso = alumnoCursoRepository.findById(alumnoCurso.getId()).orElse(null);
        if (existingAlumnoCurso != null) {
            if (alumnoCurso.getAlumno() != null) {
                existingAlumnoCurso.setAlumno(alumnoCurso.getAlumno());
            }
            if (alumnoCurso.getCurso() != null) {
                existingAlumnoCurso.setCurso(alumnoCurso.getCurso());
            }
            return alumnoCursoRepository.save(existingAlumnoCurso);
        }
        return null;
    }

    public void deleteAlumnoCursoById(Long id) {
        alumnoCursoRepository.deleteById(id);
    }

    public void deleteByAlumnoId(Long alumnoId) {
        List<AlumnoCurso> alumnosCurso = alumnoCursoRepository.findAll();
        for (AlumnoCurso alumnoCurso : alumnosCurso) {
            if (alumnoCurso.getAlumno() != null && alumnoCurso.getAlumno().getId().equals(alumnoId)) {
                alumnoCursoRepository.deleteById(alumnoCurso.getId());
            }
        }
    }

    public void deleteByCursoId(Long cursoId) {
        List<AlumnoCurso> alumnosCurso = alumnoCursoRepository.findAll();
        for (AlumnoCurso alumnoCurso : alumnosCurso) {
            if (alumnoCurso.getCurso() != null && alumnoCurso.getCurso().getId().equals(cursoId)) {
                alumnoCursoRepository.deleteById(alumnoCurso.getId());
            }
        }
    }
}