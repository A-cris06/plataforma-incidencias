package com.empresa.incidencias.service;

import com.empresa.incidencias.domain.dto.UsuarioResumenDTO;
import com.empresa.incidencias.domain.entity.Rol;
import com.empresa.incidencias.domain.entity.Usuario;
import com.empresa.incidencias.repository.UsuarioRepository;
import com.empresa.incidencias.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarUsuariosResumen() {
        UsuarioResumenDTO dto = new UsuarioResumenDTO(1L, "Juan", "juan@mail.com");

        when(usuarioRepository.findAllAsResumen()).thenReturn(List.of(dto));

        List<UsuarioResumenDTO> result = usuarioService.listarUsuarios();
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
    }

    @Test
    void testObtenerUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana");
        usuario.setEmail("ana@mail.com");
        usuario.setRol(Rol.SOPORTE);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.obtenerUsuarioPorId(1L);
        assertTrue(result.isPresent());
        assertEquals("Ana", result.get().getNombre());
        assertEquals(Rol.SOPORTE, result.get().getRol());
    }
}