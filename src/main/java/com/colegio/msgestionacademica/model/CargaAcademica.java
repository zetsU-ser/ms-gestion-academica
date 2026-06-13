package com.colegio.msgestionacademica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "cargasAcademica")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CargaAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El día de la semana es obligatorio")
    @Column(nullable = false)
    private String diaSemana;

    @NotBlank(message = "El bloque horario es obligatorio")
    @Column(nullable = false)
    private String bloqueHorario;

    @NotNull(message = "El curso es obligatorio")
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @NotNull(message = "El docente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "docente_id", nullable = false)
    private Usuario docente;

    @NotNull(message = "La asignatura es obligatoria")
    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;
}