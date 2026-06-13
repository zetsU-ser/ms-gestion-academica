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

import com.colegio.msgestionacademica.model.Asignatura;
import com.colegio.msgestionacademica.model.CargaAcademica;
import com.colegio.msgestionacademica.model.Curso;
import com.colegio.msgestionacademica.model.Usuario;
import com.colegio.msgestionacademica.repository.CargaAcademicaRepository;

@ExtendWith(MockitoExtension.class)
class CargaAcademicaServiceTest {

    @Mock
    private CargaAcademicaRepository cargaAcademicaRepository;

    @Mock
    private EvaluacionService evaluacionService;

    @InjectMocks
    private CargaAcademicaService cargaAcademicaService;

    @Test
    void getAllCargasAcademicas_retornaListaDeCargasAcademicas() {
        CargaAcademica cargaAcademica = crearCargaAcademica();

        when(cargaAcademicaRepository.findAll()).thenReturn(List.of(cargaAcademica));

        List<CargaAcademica> resultado = cargaAcademicaService.getAllCargasAcademicas();

        assertEquals(1, resultado.size());
        assertEquals("Lunes", resultado.get(0).getDiaSemana());
        verify(cargaAcademicaRepository).findAll();
    }

    @Test
    void getCargaAcademicaById_siNoExiste_retornaNull() {
        when(cargaAcademicaRepository.findById(99L)).thenReturn(Optional.empty());

        CargaAcademica resultado = cargaAcademicaService.getCargaAcademicaById(99L);

        assertNull(resultado);
        verify(cargaAcademicaRepository).findById(99L);
    }

    private CargaAcademica crearCargaAcademica() {
        Curso curso = new Curso(
                1L,
                "1 Medio",
                "A"
        );

        Usuario docente = new Usuario(
                1L,
                "11111111-1",
                "Eduardo",
                "Valenzuela",
                "eduardoAULAbo@gmail.cl",
                "DOCENTE"
        );

        Asignatura asignatura = new Asignatura(
                1L,
                "Matemática"
        );

        return new CargaAcademica(
                1L,
                "Lunes",
                "08:00-09:30",
                curso,
                docente,
                asignatura
        );
    }
}