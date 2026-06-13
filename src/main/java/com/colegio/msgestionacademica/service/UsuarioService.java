package com.colegio.msgestionacademica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colegio.msgestionacademica.model.Usuario;
import com.colegio.msgestionacademica.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargaAcademicaService cargaAcademicaService;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario;
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (existingUsuario != null) {
            if (usuario.getRut() != null) {
                existingUsuario.setRut(usuario.getRut());
            }
            if (usuario.getNombre() != null) {
                existingUsuario.setNombre(usuario.getNombre());
            }
            if (usuario.getApellido() != null) {
                existingUsuario.setApellido(usuario.getApellido());
            }
            if (usuario.getEmail() != null) {
                existingUsuario.setEmail(usuario.getEmail());
            }
            if (usuario.getRol() != null) {
                existingUsuario.setRol(usuario.getRol());
            }
            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    public void deleteUsuarioById(Long id) {
        cargaAcademicaService.deleteByDocenteId(id);
        usuarioRepository.deleteById(id);
    }
}