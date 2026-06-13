package com.colegio.msgestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.msgestionacademica.model.CargaAcademica;

@Repository
public interface CargaAcademicaRepository extends JpaRepository<CargaAcademica, Long>{
}