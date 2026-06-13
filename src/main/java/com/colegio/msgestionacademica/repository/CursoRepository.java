package com.colegio.msgestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.msgestionacademica.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}