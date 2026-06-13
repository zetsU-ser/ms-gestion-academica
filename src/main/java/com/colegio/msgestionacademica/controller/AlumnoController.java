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

import com.colegio.msgestionacademica.model.Alumno;
import com.colegio.msgestionacademica.service.AlumnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        if (alumnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id) {
        Alumno alumno = alumnoService.getAlumnoById(id);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }

    @PostMapping
    public ResponseEntity<Alumno> createAlumno(@Valid @RequestBody Alumno alumno) {
        Alumno createdAlumno = alumnoService.createAlumno(alumno);
        return ResponseEntity.status(201).body(createdAlumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @Valid @RequestBody Alumno alumno) {
        alumno.setId(id);
        Alumno updatedAlumno = alumnoService.createAlumno(alumno);
        if (updatedAlumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAlumno);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Alumno> patchAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        alumno.setId(id);
        Alumno patchedAlumno = alumnoService.updateAlumno(alumno);
        if (patchedAlumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedAlumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        alumnoService.deleteAlumnoById(id);
        return ResponseEntity.noContent().build();
    }
}