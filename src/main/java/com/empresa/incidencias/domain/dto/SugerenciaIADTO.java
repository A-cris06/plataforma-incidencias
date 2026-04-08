package com.empresa.incidencias.domain.dto;

import java.time.LocalDateTime;

public class SugerenciaIADTO {

    private Long id;
    private Long incidenciaId;
    private String promptEjecutado;
    private String respuesta;
    private LocalDateTime fechaGeneracion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIncidenciaId() { return incidenciaId; }
    public void setIncidenciaId(Long incidenciaId) { this.incidenciaId = incidenciaId; }

    public String getPromptEjecutado() { return promptEjecutado; }
    public void setPromptEjecutado(String promptEjecutado) { this.promptEjecutado = promptEjecutado; }

    public String getRespuesta() { return respuesta; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }

    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
}