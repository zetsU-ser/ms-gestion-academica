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
import com.colegio.msgestionacademica.repository.AsignaturaRepository;

@ExtendWith(MockitoExtension.class)
class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private CargaAcademicaService cargaAcademicaService;

    @InjectMocks
    private AsignaturaService asignaturaService;

    @Test
    void getAllAsignaturas_retornaListaDeAsignaturas() {
        Asignatura asignatura = crearAsignatura();

        when(asignaturaRepository.findAll()).thenReturn(List.of(asignatura));

        List<Asignatura> resultado = asignaturaService.getAllAsignaturas();

        assertEquals(1, resultado.size());
        assertEquals("Matemática", resultado.get(0).getNombre());
        verify(asignaturaRepository).findAll();
    }

    @Test
    void getAsignaturaById_siNoExiste_retornaNull() {
        when(asignaturaRepository.findById(99L)).thenReturn(Optional.empty());

        Asignatura resultado = asignaturaService.getAsignaturaById(99L);

        assertNull(resultado);
        verify(asignaturaRepository).findById(99L);
    }

    private Asignatura crearAsignatura() {
        return new Asignatura(
                1L,
                "Matemática"
        );
    }
}