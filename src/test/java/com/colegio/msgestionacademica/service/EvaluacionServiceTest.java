package com.colegio.msgestionacademica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.colegio.msgestionacademica.model.Evaluacion;
import com.colegio.msgestionacademica.model.Usuario;
import com.colegio.msgestionacademica.repository.EvaluacionRepository;

@ExtendWith(MockitoExtension.class)
class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @Mock
    private NotaService notaService;

    @InjectMocks
    private EvaluacionService evaluacionService;

    @Test
    void getAllEvaluaciones_retornaListaDeEvaluaciones() {
        Evaluacion evaluacion = crearEvaluacion();

        when(evaluacionRepository.findAll()).thenReturn(List.of(evaluacion));

        List<Evaluacion> resultado = evaluacionService.getAllEvaluaciones();

        assertEquals(1, resultado.size());
        assertEquals("Prueba Unidad 1", resultado.get(0).getNombre());
        verify(evaluacionRepository).findAll();
    }

    @Test
    void getEvaluacionById_siNoExiste_retornaNull() {
        when(evaluacionRepository.findById(99L)).thenReturn(Optional.empty());

        Evaluacion resultado = evaluacionService.getEvaluacionById(99L);

        assertNull(resultado);
        verify(evaluacionRepository).findById(99L);
    }

    private Evaluacion crearEvaluacion() {
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
                "DOCENTE",
                null
        );

        Asignatura asignatura = new Asignatura(
                1L,
                "Matemática"
        );

        CargaAcademica cargaAcademica = new CargaAcademica(
                1L,
                "Lunes",
                "08:00-09:30",
                curso,
                docente,
                asignatura
        );

        return new Evaluacion(
                1L,
                "Prueba Unidad 1",
                LocalDate.of(2026, 6, 15),
                40.0,
                cargaAcademica
        );
    }
}