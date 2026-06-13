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

import com.colegio.msgestionacademica.model.Usuario;
import com.colegio.msgestionacademica.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CargaAcademicaService cargaAcademicaService;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void getAllUsuarios_retornaListaDeUsuarios() {
        Usuario usuario = crearUsuario();

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioService.getAllUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("DOCENTE", resultado.get(0).getRol());
        verify(usuarioRepository).findAll();
    }

    @Test
    void getUsuarioById_siNoExiste_retornaNull() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.getUsuarioById(99L);

        assertNull(resultado);
        verify(usuarioRepository).findById(99L);
    }

    private Usuario crearUsuario() {
        return new Usuario(
                1L,
                "11111111-1",
                "Eduardo",
                "Valenzuela",
                "eduardoAULAbo@gmail.cl",
                "DOCENTE"
        );
    }
}