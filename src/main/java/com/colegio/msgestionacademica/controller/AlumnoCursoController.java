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

import com.colegio.msgestionacademica.model.AlumnoCurso;
import com.colegio.msgestionacademica.service.AlumnoCursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alumnosCurso")
public class AlumnoCursoController {
    @Autowired
    private AlumnoCursoService alumnoCursoService;

    @GetMapping
    public ResponseEntity<List<AlumnoCurso>> getAllAlumnoCurso() {
        List<AlumnoCurso> alumnoCursos = alumnoCursoService.getAllAlumnosCurso();
        if (alumnoCursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alumnoCursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoCurso> getAlumnoCursoById(@PathVariable Long id) {
        AlumnoCurso alumnoCurso = alumnoCursoService.getAlumnoCursoById(id);
        if (alumnoCurso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumnoCurso);
    }

    @PostMapping
    public ResponseEntity<AlumnoCurso> createAlumnoCurso(@Valid @RequestBody AlumnoCurso alumnoCurso) {
        AlumnoCurso createdAlumnoCurso = alumnoCursoService.createAlumnoCurso(alumnoCurso);
        return ResponseEntity.status(201).body(createdAlumnoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoCurso> updateAlumnoCurso(@PathVariable Long id, @Valid @RequestBody AlumnoCurso alumnoCurso) {
        alumnoCurso.setId(id);
        AlumnoCurso updatedAlumnoCurso = alumnoCursoService.createAlumnoCurso(alumnoCurso);
        if (updatedAlumnoCurso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAlumnoCurso);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlumnoCurso> patchAlumnoCurso(@PathVariable Long id, @RequestBody AlumnoCurso alumnoCurso) {
        alumnoCurso.setId(id);
        AlumnoCurso updatedAlumnoCurso = alumnoCursoService.updateAlumnoCurso(alumnoCurso);
        if (updatedAlumnoCurso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAlumnoCurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumnoCurso(@PathVariable Long id) {
        alumnoCursoService.deleteAlumnoCursoById(id);
        return ResponseEntity.noContent().build();
    }
}