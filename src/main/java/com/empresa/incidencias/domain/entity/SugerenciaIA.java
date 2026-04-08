package com.empresa.incidencias.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sugerencia_ia")
public class SugerenciaIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    @Column(name = "prompt_ejecutado", columnDefinition = "TEXT")
    private String promptEjecutado;

    @Column(columnDefinition = "TEXT")
    private String respuesta;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Incidencia getIncidencia() { return incidencia; }
    public void setIncidencia(Incidencia incidencia) { this.incidencia = incidencia; }

    public String getPromptEjecutado() { return promptEjecutado; }
    public void setPromptEjecutado(String promptEjecutado) { this.promptEjecutado = promptEjecutado; }

    public String getRespuesta() { return respuesta; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }

    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
}