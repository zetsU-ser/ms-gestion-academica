package com.colegio.msgestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.msgestionacademica.model.CargaAcademica;

import java.util.List;

@Repository
public interface CargaAcademicaRepository extends JpaRepository<CargaAcademica, Long>{
    List<CargaAcademica> findByCursoIdAndDocenteId(Long cursoId, Long docenteId);
}