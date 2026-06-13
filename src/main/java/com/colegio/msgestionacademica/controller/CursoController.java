package com.colegio.msgestionacademica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.msgestionacademica.model.Curso;
import com.colegio.msgestionacademica.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = cursoService.getAllCursos();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.getCursoById(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@Valid @RequestBody Curso curso) {
        Curso createdCurso = cursoService.createCurso(curso);
        return ResponseEntity.status(201).body(createdCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        curso.setId(id);
        Curso updatedCurso = cursoService.createCurso(curso);
        if (updatedCurso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCurso);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Curso> patchCurso(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso patchedCurso = cursoService.updateCurso(curso);
        if (patchedCurso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedCurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCursoById(id);
        return ResponseEntity.noContent().build();
    }
}