package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.Evaluacion;
import com.colegio.msgestionacademica.repository.EvaluacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private NotaService notaService;

    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion getEvaluacionById(Long id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id).orElse(null);
        return evaluacion;
    }

    public Evaluacion createEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion updateEvaluacion(Evaluacion evaluacion) {
        Evaluacion existingEvaluacion = evaluacionRepository.findById(evaluacion.getId()).orElse(null);
        if (existingEvaluacion != null) {
            if (evaluacion.getNombre() != null) {
                existingEvaluacion.setNombre(evaluacion.getNombre());
            }
            if (evaluacion.getFecha() != null) {
                existingEvaluacion.setFecha(evaluacion.getFecha());
            }
            if (evaluacion.getPonderacion() != null) {
                existingEvaluacion.setPonderacion(evaluacion.getPonderacion());
            }
            if (evaluacion.getCargaAcademica() != null) {
                existingEvaluacion.setCargaAcademica(evaluacion.getCargaAcademica());
            }
            return evaluacionRepository.save(existingEvaluacion);
        }
        return null;
    }

    public void deleteEvaluacionById(Long id) {
        notaService.deleteByEvaluacionId(id);
        evaluacionRepository.deleteById(id);
    }

    public void deleteByCargaAcademicaId(Long cargaAcademicaId) {
        List<Evaluacion> evaluaciones = evaluacionRepository.findAll();
        for (Evaluacion evaluacion : evaluaciones) {
            if (evaluacion.getCargaAcademica() != null && evaluacion.getCargaAcademica().getId().equals(cargaAcademicaId)) {
                deleteEvaluacionById(evaluacion.getId());
            }
        }
    }
}