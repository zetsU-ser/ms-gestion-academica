package com.colegio.msgestionacademica.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "evaluaciones")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la evaluación es obligatorio")
    @Size(max = 60, message = "El nombre de la evaluación no puede superar los 60 caracteres")
    @Column(nullable = false, length = 60)
    private String nombre;

    @NotNull(message = "La fecha de la evaluación es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "La ponderación es obligatoria")
    @DecimalMin(value = "0.0", message = "La ponderación no puede ser menor a 0")
    @DecimalMax(value = "100.0", message = "La ponderación no puede ser mayor a 100")
    @Column(nullable = false)
    private Double ponderacion;

    @NotNull(message = "La carga académica es obligatoria")
    @ManyToOne
    @JoinColumn(name = "cargaAcademica_id", nullable = false)
    private CargaAcademica cargaAcademica;
}