package com.colegio.msgestionacademica.model;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "notas")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La nota es obligatoria")
    @DecimalMin(value = "1.0", message = "La nota no puede ser menor a 1.0")
    @DecimalMax(value = "7.0", message = "La nota no puede ser mayor a 7.0")
    @Column(nullable = false)
    private Double valor;

    @NotNull(message = "La evaluación es obligatoria")
    @ManyToOne
    @JoinColumn(name = "evaluacion_id", nullable = false)
    private Evaluacion evaluacion;

    @NotNull(message = "El alumno es obligatorio")
    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;
}