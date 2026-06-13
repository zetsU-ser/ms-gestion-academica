package com.colegio.msgestionacademica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "asignaturas")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la asignatura es obligatorio")
    @Size(max = 30, message = "El nombre de la asignatura no puede superar los 30 caracteres")
    @Column(nullable = false, length = 30)
    private String nombre;
}