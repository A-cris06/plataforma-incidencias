package com.empresa.incidencias.service;

import com.empresa.incidencias.domain.dto.IncidenciaFiltroDTO;
import com.empresa.incidencias.domain.entity.EstadoIncidencia;
import com.empresa.incidencias.domain.entity.Incidencia;
import com.empresa.incidencias.domain.entity.Prioridad;
import com.empresa.incidencias.repository.IncidenciaRepository;
import com.empresa.incidencias.service.impl.IncidenciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IncidenciaServiceImplTest {

    @Mock
    private IncidenciaRepository incidenciaRepository;

    @InjectMocks
    private IncidenciaServiceImpl incidenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarConFiltros() {
        Incidencia i = new Incidencia();
        i.setId(1L);
        i.setTitulo("Error filtro");
        IncidenciaFiltroDTO filtro = new IncidenciaFiltroDTO();
        filtro.setEstado(EstadoIncidencia.ABIERTA);
        filtro.setPrioridad(Prioridad.ALTA);
        filtro.setPage(0);
        filtro.setSize(10);

        Page<Incidencia> page = new PageImpl<>(List.of(i));
        when(incidenciaRepository.buscarConFiltros(
                any(), any(), any(), any(PageRequest.class)
        )).thenReturn(page);

        Page<Incidencia> result = incidenciaService.buscarIncidencias(filtro);
        assertEquals(1, result.getContent().size());
        assertEquals("Error filtro", result.getContent().get(0).getTitulo());
    }

    @Test
    void testObtenerIncidenciaPorId() {
        Incidencia i = new Incidencia();
        i.setId(1L);
        i.setTitulo("Test ID");

        when(incidenciaRepository.findById(1L)).thenReturn(Optional.of(i));

        Optional<Incidencia> result = incidenciaService.obtenerIncidenciaPorId(1L);
        assertTrue(result.isPresent());
        assertEquals("Test ID", result.get().getTitulo());
    }
}