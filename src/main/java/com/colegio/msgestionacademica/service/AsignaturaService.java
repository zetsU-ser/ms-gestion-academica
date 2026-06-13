package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.Asignatura;
import com.colegio.msgestionacademica.repository.AsignaturaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class AsignaturaService {
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CargaAcademicaService cargaAcademicaService;

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Asignatura getAsignaturaById(Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElse(null);
        return asignatura;
    }

    public Asignatura createAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura updateAsignatura(Asignatura asignatura) {
        Asignatura existingAsignatura = asignaturaRepository.findById(asignatura.getId()).orElse(null);
        if (existingAsignatura != null) {
            if (asignatura.getNombre() != null) {
                existingAsignatura.setNombre(asignatura.getNombre());
            }
            return asignaturaRepository.save(existingAsignatura);
        }
        return null;
    }

    public void deleteAsignaturaById(Long id) {
        cargaAcademicaService.deleteByAsignaturaId(id);
        asignaturaRepository.deleteById(id);
    }
}