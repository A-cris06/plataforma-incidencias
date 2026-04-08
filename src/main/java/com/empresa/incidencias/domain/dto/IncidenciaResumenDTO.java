package com.empresa.incidencias.domain.dto;

import com.empresa.incidencias.domain.entity.EstadoIncidencia;
import com.empresa.incidencias.domain.entity.Prioridad;

import java.time.LocalDateTime;

public class IncidenciaResumenDTO {

    private final Long id;
    private final String titulo;
    private final String descripcion;
    private final EstadoIncidencia estado;
    private final Prioridad prioridad;
    private final LocalDateTime fechaCreacion;
    private final LocalDateTime fechaActualizacion;
    private final Long usuarioId;
    private final String usuarioNombre;
    private final String usuarioEmail;

    public IncidenciaResumenDTO(Long id, String titulo, String descripcion, EstadoIncidencia estado,
                                Prioridad prioridad, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion,
                                Long usuarioId, String usuarioNombre, String usuarioEmail) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioEmail = usuarioEmail;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public EstadoIncidencia getEstado() { return estado; }
    public Prioridad getPrioridad() { return prioridad; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public Long getUsuarioId() { return usuarioId; }
    public String getUsuarioNombre() { return usuarioNombre; }
    public String getUsuarioEmail() { return usuarioEmail; }
}
