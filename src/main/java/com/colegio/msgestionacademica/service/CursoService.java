package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.Curso;
import com.colegio.msgestionacademica.repository.CursoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CargaAcademicaService cargaAcademicaService;

    @Autowired
    private AlumnoCursoService alumnoCursoService;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Long id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        return curso;
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Curso curso) {
        Curso existingCurso = cursoRepository.findById(curso.getId()).orElse(null);
        if (existingCurso != null) {
            if (curso.getNivel() != null) {
                existingCurso.setNivel(curso.getNivel());
            }
            if (curso.getLetra() != null) {
                existingCurso.setLetra(curso.getLetra());
            }
            return cursoRepository.save(existingCurso);
        }
        return null;
    }

    public void deleteCursoById(Long id) {
        alumnoCursoService.deleteByCursoId(id);
        cargaAcademicaService.deleteByCursoId(id);
        cursoRepository.deleteById(id);
    }
}