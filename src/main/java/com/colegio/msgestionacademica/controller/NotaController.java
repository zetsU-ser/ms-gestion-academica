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

import com.colegio.msgestionacademica.model.Nota;
import com.colegio.msgestionacademica.service.NotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<List<Nota>> getAllNotas() {
        List<Nota> notas = notaService.getAllNotas();
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaById(@PathVariable Long id) {
        Nota nota = notaService.getNotaById(id);
        if (nota == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public ResponseEntity<Nota> createNota(@Valid @RequestBody Nota nota) {
        Nota createdNota = notaService.createNota(nota);
        return ResponseEntity.status(201).body(createdNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable Long id, @Valid @RequestBody Nota nota) {
        nota.setId(id);
        Nota updatedNota = notaService.createNota(nota);
        if (updatedNota == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedNota);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Nota> patchNota(@PathVariable Long id, @RequestBody Nota nota) {
        nota.setId(id);
        Nota patchedNota = notaService.updateNota(nota);
        if (patchedNota == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedNota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaService.deleteNotaById(id);
        return ResponseEntity.noContent().build();
    }
}