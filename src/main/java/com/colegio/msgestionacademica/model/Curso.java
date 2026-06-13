package com.colegio.msgestionacademica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "cursos")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nivel es obligatorio")
    @Size(max = 20, message = "El nivel no puede superar los 20 caracteres")
    @Column(nullable = false, length = 20)
    private String nivel;

    @NotBlank(message = "La letra es obligatoria")
    @Size(max = 1, message = "La letra no puede superar 1 caracter")
    @Pattern(regexp = "^[A-Z]$", message = "La letra del curso debe ser una letra mayúscula")
    @Column(nullable = false, length = 1)
    private String letra;
}