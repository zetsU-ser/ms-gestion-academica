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

import com.colegio.msgestionacademica.model.Asignatura;
import com.colegio.msgestionacademica.service.AsignaturaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {
    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
        if (asignaturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Long id) {
        Asignatura asignatura = asignaturaService.getAsignaturaById(id);
        if (asignatura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignatura);
    }

    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@Valid @RequestBody Asignatura asignatura) {
        Asignatura createdAsignatura = asignaturaService.createAsignatura(asignatura);
        return ResponseEntity.status(201).body(createdAsignatura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Long id, @Valid @RequestBody Asignatura asignatura) {
        asignatura.setId(id);
        Asignatura updatedAsignatura = asignaturaService.createAsignatura(asignatura);
        if (updatedAsignatura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAsignatura);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Asignatura> patchAsignatura(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        asignatura.setId(id);
        Asignatura patchedAsignatura = asignaturaService.updateAsignatura(asignatura);
        if (patchedAsignatura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedAsignatura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id) {
        asignaturaService.deleteAsignaturaById(id);
        return ResponseEntity.noContent().build();
    }
}