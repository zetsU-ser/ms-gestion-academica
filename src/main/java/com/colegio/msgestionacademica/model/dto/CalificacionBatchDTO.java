package com.colegio.msgestionacademica.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class CalificacionBatchDTO {
    private Long curso_id;
    private Long profesor_id;
    private List<AlumnoNotasDTO> alumnos;
}
