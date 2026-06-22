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
import com.colegio.msgestionacademica.repository.AlumnoRepository;

@ExtendWith(MockitoExtension.class)
class AlumnoServiceTest {

    @Mock // mock para simular acceso a datos
    private AlumnoRepository alumnoRepository;

    @Mock // mock de notas y alumno-curso porque AlumnoService por dependencia
    private NotaService notaService;

    @Mock
    private AlumnoCursoService alumnoCursoService;

    @InjectMocks // instancia real de AlumnoService inyectando mocks
    private AlumnoService alumnoService;

    @Test
    void getAllAlumnos_retornaListaDeAlumnos() {
        Alumno alumno = crearAlumno();

        when(alumnoRepository.findAll()).thenReturn(List.of(alumno)); // simula retorno de lista con un alumno
        
        List<Alumno> resultado = alumnoService.getAllAlumnos(); // ejecuta metodo real

        assertEquals(1, resultado.size()); // valida que solo tiene un elemento
        assertEquals("Brad", resultado.get(0).getNombre()); // valida que el nombre sea del creador del sexo
        verify(alumnoRepository).findAll(); // verificacion del llamado al método "findAll"
    }

    @Test
    void getAlumnoById_siNoExiste_retornaNull() {
        when(alumnoRepository.findById(99L)).thenReturn(Optional.empty()); // simula que el repo no encuentra un alumno

        Alumno resultado = alumnoService.getAlumnoById(99L); // ejecuta el método real buscando el id

        assertNull(resultado); // valida que retorne null
        verify(alumnoRepository).findById(99L); // verifica que service consulto el repo con el id 99
    }
    
    // datos pa los test
    private Alumno crearAlumno() {
        return new Alumno(
                1L,
                "12367678-9",
                "Brad",
                "Sexo",
                15,
                "Don Sexo",
                "donSexo@gmail.com",
                "916745678"
        );
    }
}