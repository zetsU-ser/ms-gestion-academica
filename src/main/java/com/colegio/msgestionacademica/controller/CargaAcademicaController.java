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

import com.colegio.msgestionacademica.model.CargaAcademica;
import com.colegio.msgestionacademica.service.CargaAcademicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cargas-academicas")
public class CargaAcademicaController {
    @Autowired
    private CargaAcademicaService cargaAcademicaService;

    @GetMapping
    public ResponseEntity<List<CargaAcademica>> getAllCargasAcademicas() {
        List<CargaAcademica> cargasAcademicas = cargaAcademicaService.getAllCargasAcademicas();
        if (cargasAcademicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cargasAcademicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargaAcademica> getCargaAcademicaById(@PathVariable Long id) {
        CargaAcademica cargaAcademica = cargaAcademicaService.getCargaAcademicaById(id);
        if (cargaAcademica == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cargaAcademica);
    }

    @PostMapping
    public ResponseEntity<CargaAcademica> createCargaAcademica(@Valid @RequestBody CargaAcademica cargaAcademica) {
        CargaAcademica createdCargaAcademica = cargaAcademicaService.createCargaAcademica(cargaAcademica);
        return ResponseEntity.status(201).body(createdCargaAcademica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargaAcademica> updateCargaAcademica(@PathVariable Long id, @Valid @RequestBody CargaAcademica cargaAcademica) {
        cargaAcademica.setId(id);
        CargaAcademica updatedCargaAcademica = cargaAcademicaService.createCargaAcademica(cargaAcademica);
        if (updatedCargaAcademica == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCargaAcademica);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CargaAcademica> patchCargaAcademica(@PathVariable Long id, @RequestBody CargaAcademica cargaAcademica) {
        cargaAcademica.setId(id);
        CargaAcademica patchedCargaAcademica = cargaAcademicaService.updateCargaAcademica(cargaAcademica);
        if (patchedCargaAcademica == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedCargaAcademica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCargaAcademica(@PathVariable Long id) {
        cargaAcademicaService.deleteCargaAcademicaById(id);
        return ResponseEntity.noContent().build();
    }
}