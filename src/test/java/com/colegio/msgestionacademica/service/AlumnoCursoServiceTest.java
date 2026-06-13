package com.colegio.msgestionacademica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.colegio.msgestionacademica.model.Alumno;
import com.colegio.msgestionacademica.model.AlumnoCurso;
import com.colegio.msgestionacademica.model.Curso;
import com.colegio.msgestionacademica.repository.AlumnoCursoRepository;

@ExtendWith(MockitoExtension.class)
class AlumnoCursoServiceTest {

    @Mock
    private AlumnoCursoRepository alumnoCursoRepository;

    @InjectMocks
    private AlumnoCursoService alumnoCursoService;

    @Test
    void getAllAlumnosCurso_retornaListaDeAlumnosCurso() {
        AlumnoCurso alumnoCurso = crearAlumnoCurso();

        when(alumnoCursoRepository.findAll()).thenReturn(List.of(alumnoCurso));

        List<AlumnoCurso> resultado = alumnoCursoService.getAllAlumnosCurso();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        verify(alumnoCursoRepository).findAll();
    }

    @Test
    void getAlumnoCursoById_siNoExiste_retornaNull() {
        when(alumnoCursoRepository.findById(99L)).thenReturn(Optional.empty());

        AlumnoCurso resultado = alumnoCursoService.getAlumnoCursoById(99L);

        assertNull(resultado);
        verify(alumnoCursoRepository).findById(99L);
    }

    private AlumnoCurso crearAlumnoCurso() {
        Alumno alumno = new Alumno(
                1L,
                "12367678-9",
                "Brad",
                "Sexo",
                "Don Sexo",
                "donSexo@correo.cl",
                "916745678"
        );

        Curso curso = new Curso(
                1L,
                "1 Medio",
                "A"
        );

        return new AlumnoCurso(
                1L,
                alumno,
                curso
        );
    }
}