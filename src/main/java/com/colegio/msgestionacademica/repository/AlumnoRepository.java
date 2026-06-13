package com.colegio.msgestionacademica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colegio.msgestionacademica.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
}