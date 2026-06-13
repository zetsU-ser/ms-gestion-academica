package com.colegio.msgestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.msgestionacademica.model.AlumnoCurso;

@Repository
public interface AlumnoCursoRepository extends JpaRepository<AlumnoCurso, Long>{
}