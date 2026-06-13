package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.Nota;
import com.colegio.msgestionacademica.repository.NotaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    public Nota getNotaById(Long id) {
        Nota nota = notaRepository.findById(id).orElse(null);
        return nota;
    }

    public Nota createNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota updateNota(Nota nota) {
        Nota existingNota = notaRepository.findById(nota.getId()).orElse(null);
        if (existingNota != null) {
            if (nota.getValor() != null) {
                existingNota.setValor(nota.getValor());
            }
            if (nota.getEvaluacion() != null) {
                existingNota.setEvaluacion(nota.getEvaluacion());
            }
            if (nota.getAlumno() != null) {
                existingNota.setAlumno(nota.getAlumno());
            }
            return notaRepository.save(existingNota);
        }
        return null;
    }

    public void deleteNotaById(Long id) {
        notaRepository.deleteById(id);
    }

    public void deleteByEvaluacionId(Long evaluacionId) {
        List<Nota> notas = notaRepository.findAll();
        for (Nota nota : notas) {
            if (nota.getEvaluacion() != null && nota.getEvaluacion().getId().equals(evaluacionId)) {
                notaRepository.deleteById(nota.getId());
            }
        }
    }

    public void deleteByAlumnoId(Long alumnoId) {
        List<Nota> notas = notaRepository.findAll();
        for (Nota nota : notas) {
            if (nota.getAlumno() != null && nota.getAlumno().getId().equals(alumnoId)) {
                notaRepository.deleteById(nota.getId());
            }
        }
    }
}