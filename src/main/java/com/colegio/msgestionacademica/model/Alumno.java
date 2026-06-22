package com.colegio.msgestionacademica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "alumnos")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT es obligatorio")
    @Size(max = 12, message = "El RUT no puede superar los 12 caracteres")
    @Pattern(regexp = "^\\d{8}-[\\dkK]$", message = "El RUT debe tener formato 12345678-9 o 12345678-K")
    @Column(unique = true, nullable = false)
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
    @Column(nullable = false, length = 50)
    private String apellido;

    @jakarta.validation.constraints.NotNull(message = "La edad es obligatoria")
    @jakarta.validation.constraints.Min(value = 4, message = "La edad mínima es 4 años")
    @jakarta.validation.constraints.Max(value = 100, message = "La edad máxima es 100 años")
    @Column(name = "edad")
    private Integer edad;

    @NotBlank(message = "El nombre del apoderado es obligatorio")
    @Size(max = 100, message = "El nombre del apoderado no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreApoderado;

    @NotBlank(message = "El email del apoderado es obligatorio")
    @Email(message = "El email del apoderado debe tener un formato válido")
    @Size(max = 100, message = "El email del apoderado no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String emailApoderado;

    @NotBlank(message = "El teléfono del apoderado es obligatorio")
    @Size(min = 9, max = 9, message = "El teléfono del apoderado debe tener 9 caracteres")
    @Pattern(regexp = "^9\\d{8}$", message = "El teléfono del apoderado debe comenzar con 9 y tener 9 dígitos")
    @Column(nullable = false, length = 9)
    private String telefonoApoderado;
}