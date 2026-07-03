package com.colegio.msgestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.msgestionacademica.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    Nota findByAlumnoIdAndEvaluacionId(Long alumnoId, Long evaluacionId);
}