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

import com.colegio.msgestionacademica.model.Alumno;
import com.colegio.msgestionacademica.model.Asignatura;
import com.colegio.msgestionacademica.model.CargaAcademica;
import com.colegio.msgestionacademica.model.Curso;
import com.colegio.msgestionacademica.model.Evaluacion;
import com.colegio.msgestionacademica.model.Nota;
import com.colegio.msgestionacademica.model.Usuario;
import com.colegio.msgestionacademica.repository.NotaRepository;

@ExtendWith(MockitoExtension.class)
class NotaServiceTest {

    @Mock
    private NotaRepository notaRepository;

    @InjectMocks
    private NotaService notaService;

    @Test
    void getAllNotas_retornaListaDeNotas() {
        Nota nota = crearNota();

        when(notaRepository.findAll()).thenReturn(List.of(nota));

        List<Nota> resultado = notaService.getAllNotas();

        assertEquals(1, resultado.size());
        assertEquals(6.5, resultado.get(0).getValor());
        verify(notaRepository).findAll();
    }

    @Test
    void getNotaById_siNoExiste_retornaNull() {
        when(notaRepository.findById(99L)).thenReturn(Optional.empty());

        Nota resultado = notaService.getNotaById(99L);

        assertNull(resultado);
        verify(notaRepository).findById(99L);
    }

    private Nota crearNota() {
        Alumno alumno = new Alumno(
                1L,
                "12367678-9",
                "Brad",
                "Sexo",
                15,
                "Don Sexo",
                "donSexo@correo.cl",
                "916745678"
        );

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

        Evaluacion evaluacion = new Evaluacion(
                1L,
                "Prueba Unidad 1",
                LocalDate.of(2026, 6, 15),
                40.0,
                cargaAcademica
        );

        return new Nota(
                1L,
                6.5,
                evaluacion,
                alumno
        );
    }
}