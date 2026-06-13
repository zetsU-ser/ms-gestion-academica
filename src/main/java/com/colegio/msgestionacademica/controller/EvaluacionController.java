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

import com.colegio.msgestionacademica.model.Evaluacion;
import com.colegio.msgestionacademica.service.EvaluacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {
    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<Evaluacion>> getAllEvaluaciones() {
        List<Evaluacion> evaluaciones = evaluacionService.getAllEvaluaciones();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getEvaluacionById(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionById(id);
        if (evaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evaluacion);
    }

    @PostMapping
    public ResponseEntity<Evaluacion> createEvaluacion(@Valid @RequestBody Evaluacion evaluacion) {
        Evaluacion createdEvaluacion = evaluacionService.createEvaluacion(evaluacion);
        return ResponseEntity.status(201).body(createdEvaluacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> updateEvaluacion(@PathVariable Long id, @Valid @RequestBody Evaluacion evaluacion) {
        evaluacion.setId(id);
        Evaluacion updatedEvaluacion = evaluacionService.createEvaluacion(evaluacion);
        if (updatedEvaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEvaluacion);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Evaluacion> patchEvaluacion(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        evaluacion.setId(id);
        Evaluacion patchedEvaluacion = evaluacionService.updateEvaluacion(evaluacion);
        if (patchedEvaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedEvaluacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable Long id) {
        evaluacionService.deleteEvaluacionById(id);
        return ResponseEntity.noContent().build();
    }
}