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

import com.colegio.msgestionacademica.model.Curso;
import com.colegio.msgestionacademica.repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private CargaAcademicaService cargaAcademicaService;

    @Mock
    private AlumnoCursoService alumnoCursoService;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void getAllCursos_retornaListaDeCursos() {
        Curso curso = crearCurso();

        when(cursoRepository.findAll()).thenReturn(List.of(curso));

        List<Curso> resultado = cursoService.getAllCursos();

        assertEquals(1, resultado.size());
        assertEquals("1 Medio", resultado.get(0).getNivel());
        verify(cursoRepository).findAll();
    }

    @Test
    void getCursoById_siNoExiste_retornaNull() {
        when(cursoRepository.findById(99L)).thenReturn(Optional.empty());

        Curso resultado = cursoService.getCursoById(99L);

        assertNull(resultado);
        verify(cursoRepository).findById(99L);
    }

    private Curso crearCurso() {
        return new Curso(
                1L,
                "1 Medio",
                "A"
        );
    }
}