# 📋 Enunciado del Proyecto
## Plataforma de Gestión de Incidencias 🏢 Enterprise Backend

---

## Stack tecnológico obligatorio

- Java 17+
- Spring Boot 3.x (autoconfiguración, perfiles, gestión de propiedades por entorno)
- Spring Data JPA
- PostgreSQL
- Flyway
- OpenAPI / Swagger (API First)
- JUnit 5 / Mockito
- Podman / Podman Compose

---

## 🎯 Objetivo del proyecto

Diseñar e implementar una API REST empresarial para la gestión de incidencias, aplicando buenas prácticas de arquitectura backend, separación de responsabilidades, acceso avanzado a datos, testing profesional y trabajo colaborativo.

El objetivo no es solo "que funcione", sino:

- Pensar el backend como un producto enterprise
- Diseñar correctamente contratos, capas, entidades y responsabilidades
- Entender varias formas de acceso a datos
- Trabajar como en un equipo real, con tareas paralelas y puntos de integración

---

## 🏗️ Arquitectura base (obligatoria)

La aplicación deberá seguir una arquitectura en capas clara:

```
controller     (API)
service        (interfaces + implementación)
repository     (JPA)
domain         (entidades + DTOs)
infrastructure (configuración, Flyway, etc.)
```

### Principios obligatorios

- Uso de interfaces en la capa de servicio
- Controllers sin lógica de negocio
- Servicios transaccionales
- Repositorios desacoplados del resto del sistema
- Uso de DTOs para exponer datos (no exponer entidades directamente en la API)

---

## 📐 Modelo de dominio

### Entidad: Usuario

| Campo      | Detalle                        |
|------------|--------------------------------|
| `id`       |                                |
| `nombre`   |                                |
| `email`    | Único                          |
| `rol`      | `ADMIN`, `SOPORTE`, `USUARIO`  |
| `fechaAlta`|                                |

### Entidad: Incidencia

| Campo               | Detalle                              |
|---------------------|--------------------------------------|
| `id`                |                                      |
| `titulo`            |                                      |
| `descripcion`       |                                      |
| `estado`            | `ABIERTA`, `EN_PROGRESO`, `CERRADA`  |
| `prioridad`         |                                      |
| `fechaCreacion`     |                                      |
| `fechaActualizacion`|                                      |
| `usuarioAsignado`   |                                      |

### Relaciones

- Un `Usuario` puede tener muchas `Incidencias`
- Una `Incidencia` debe estar asignada a un `Usuario`

---

## 🗄️ Persistencia y Flyway (nivel enterprise)

Flyway deberá configurarse con múltiples capas de migración, separadas físicamente en carpetas:

**1️⃣ Migraciones estructurales** → `db/migration/schema`
- Creación de tablas
- Claves primarias y foráneas
- Índices y restricciones

**2️⃣ Datos base del sistema** → `db/migration/data`
- Usuarios iniciales
- Catálogos mínimos (estados, prioridades, etc.)

**3️⃣ Datos de testing** → `db/migration/testdata`
- Datos específicos para pruebas y desarrollo
- Activables por perfil Spring (`test`, `local`, etc.)

> ⚙️ La configuración debe permitir elegir qué capas de datos se cargan según el entorno.

---

## 🐳 Infraestructura local

Se debe proporcionar un `podman-compose` que levante:

- PostgreSQL
- Gestor de base de datos (ej. pgAdmin)

El proyecto deberá arrancar sin dependencias externas.

---

## 📄 Diseño de API — API First (Swagger)

La API se diseñará siguiendo API First:

- Definir el contrato OpenAPI (YAML) **antes** de implementar
- Swagger será la fuente de verdad
- Versionado de API (ej. `/api/v1`)

### Funcionalidades mínimas

- CRUD de Usuarios
- CRUD de Incidencias
- Búsquedas avanzadas de incidencias:
    - Por usuario
    - Por estado
    - Por combinación de criterios
    - Con paginación

---

## 👥 Organización del trabajo

### 🤝 Fase común (todos)

- Definición de arquitectura
- Modelo de datos
- Contrato OpenAPI inicial
- Estrategia de Flyway

---

### 👤 Persona A — Persistencia y queries avanzadas

**Repositorios JPA** para `Usuario` e `Incidencia`.

**Queries obligatorias** — implementar todas las siguientes:

| Tipo | Descripción |
|------|-------------|
| Método derivado | Spring Data JPA (sin `@Query`) |
| JPQL | Con `@Query` explícita |
| Proyección DTO | Query que devuelva un DTO directamente |
| Filtros combinados | Query con múltiples parámetros opcionales |
| Paginada | Uso de `Pageable` |

**Testing:**
- Tests unitarios de repositorios
- Uso de tests parametrizados
- Validar distintos escenarios de búsqueda

---

### 👤 Persona B — Servicios, API y contratos

**Servicios:**
- Interfaces + implementación
- Gestión de transacciones
- Validaciones de reglas de negocio

**Controllers:**
- Implementación estricta del contrato Swagger
- Manejo correcto de códigos HTTP
- Gestión básica de errores (404, 400, etc.)

**Testing:**
- Tests unitarios de servicios
- Tests parametrizados
- Mock de dependencias con Mockito

---

## 🧪 Testing (requisito clave)

- JUnit 5 + Mockito
- Tests de servicios y de repositorios
- Uso **obligatorio** de tests parametrizados
- Separación clara entre tests unitarios y tests con base de datos

---

## 🔗 Integración final

Una vez completadas las tareas individuales:

- Unificar el proyecto
- Validar que todo funciona de forma integrada
- Flujo completo: `Swagger → Controller → Service → Repository → DB`

---

## 📝 Requisito de justificación técnica (evaluado)

> **Este apartado es obligatorio y cuenta en la nota final.**

A lo largo del proyecto, cada decisión técnica relevante debe estar justificada. No basta con que el código funcione: hay que demostrar que se entiende **por qué** se ha hecho así.

### ¿Qué se entiende por "decisión técnica relevante"?

Ejemplos de decisiones que deben justificarse:

- ¿Por qué has usado JPQL en lugar de un método derivado en este caso concreto?
- ¿Qué ventaja aporta separar las migraciones Flyway en carpetas distintas en un entorno real?
- ¿Por qué el servicio implementa una interfaz si solo hay una implementación?
- ¿Qué problema resuelve usar DTOs en lugar de exponer directamente la entidad JPA?
- ¿Por qué has elegido este código HTTP concreto en esta respuesta de la API?

### ¿Dónde se documenta?

En el `README.md`, en una sección obligatoria llamada **"Decisiones técnicas"**. Cada decisión debe incluir:

1. Qué se decidió
2. Qué alternativas existían
3. Por qué se eligió esta opción

> ⚠️ Respuestas genéricas del tipo *"porque es buena práctica"* sin desarrollo no serán válidas.

---

## 🔒 Requisito con especificación incompleta

> **Este requisito se completará en clase. No está definido aquí intencionadamente.**

La búsqueda avanzada de incidencias incluye un **criterio de filtrado adicional** cuya lógica exacta se comunicará verbalmente durante la sesión de seguimiento del proyecto.

Lo que sí se sabe ahora:

- Afectará al endpoint de búsqueda de incidencias
- Implicará una decisión de diseño en la capa de repositorio
- Deberá estar cubierto por al menos un test

> 🎯 Tomar nota durante la explicación en clase. No habrá versión escrita de este requisito: forma parte del ejercicio saber escuchar, interpretar y trasladar un requisito verbal a código.

---

## 🤖 Integración con GitHub Copilot CLI desde la aplicación

> **Este apartado es obligatorio y forma parte de los entregables.**

En entornos enterprise es cada vez más habitual integrar herramientas de IA directamente en flujos backend: generar sugerencias automáticas, enriquecer datos, o asistir a los operadores sin salir de la aplicación. Este ejercicio simula exactamente eso, usando **GitHub Copilot CLI como motor**, invocado directamente desde Spring Boot mediante un proceso del sistema.

### Prerrequisitos

Cada miembro del equipo debe tener instalado y autenticado:

```bash
# Instalar GitHub CLI
# https://cli.github.com/

# Instalar Copilot CLI
gh extension install github/gh-copilot

# Autenticarse (cuenta gratuita válida)
gh auth login
```

> ✅ No se necesita licencia de pago. La cuenta gratuita de GitHub con Copilot Free es suficiente.

---

### La tarea

Implementar un **servicio Spring Boot** que, dado el ID de una incidencia existente en la BBDD:

1. Lea sus datos reales (`titulo`, `descripcion`, `estado`, `prioridad`)
2. Cargue una **plantilla de prompt** desde fichero (`.txt`) con palabras reservadas
3. Sustituya las palabras reservadas con los datos reales de la incidencia
4. Ejecute el prompt resultante invocando `gh copilot suggest` como proceso del sistema
5. Capture la respuesta en tiempo real desde el `stdout`
6. Persista la respuesta en la BBDD en una nueva tabla `sugerencia_ia`

---

### Palabras reservadas de la plantilla

| Palabra reservada   | Se reemplaza por                           |
|---------------------|--------------------------------------------|
| `{{TITULO}}`        | Título de la incidencia                    |
| `{{DESCRIPCION}}`   | Descripción de la incidencia               |
| `{{ESTADO}}`        | Estado actual (`ABIERTA`, `EN_PROGRESO`…)  |
| `{{PRIORIDAD}}`     | Prioridad de la incidencia                 |
| `{{FECHA_CREACION}}`| Fecha de creación formateada               |

---

### Plantilla de prompt obligatoria

Fichero: `.copilot/prompt-sugerencia-resolucion.txt`

```
Soy un técnico de soporte y tengo la siguiente incidencia abierta:

Título: {{TITULO}}
Descripción: {{DESCRIPCION}}
Estado: {{ESTADO}}
Prioridad: {{PRIORIDAD}}
Abierta desde: {{FECHA_CREACION}}

Dame una sugerencia concisa de pasos a seguir para resolverla.
Responde solo con los pasos, sin explicaciones adicionales.
```

---

### Entidad y migración requeridas

Añadir una nueva entidad `SugerenciaIA` y su migración Flyway correspondiente:

| Campo            | Detalle                                       |
|------------------|-----------------------------------------------|
| `id`             |                                               |
| `incidencia`     | FK a `Incidencia`                             |
| `promptEjecutado`| Texto del prompt tras sustituir las variables |
| `respuesta`      | Texto devuelto por Copilot CLI                |
| `fechaGeneracion`| Timestamp de la llamada                       |

---

### Endpoint requerido

```
POST /api/v1/incidencias/{id}/sugerencia
```

Comportamiento esperado:

- Si la incidencia no existe → `404 Not Found`
- Si Copilot CLI no está disponible en el sistema → `503 Service Unavailable` con mensaje descriptivo
- Si todo va bien → `201 Created` con la `SugerenciaIA` persistida en el cuerpo

---

### Implementación del proceso del sistema

La invocación a Copilot CLI debe realizarse desde Java usando `ProcessBuilder`. Esquema orientativo:

```java
ProcessBuilder pb = new ProcessBuilder(
    "gh", "copilot", "suggest", "-t", "generic", promptInterpolado
);
pb.redirectErrorStream(true);
Process proceso = pb.start();
String respuesta = new String(proceso.getInputStream().readAllBytes());
proceso.waitFor();
```

> ⚠️ El `ProcessBuilder` debe estar encapsulado en su propio componente de infraestructura, no en el servicio ni en el controller. La capa de servicio no debe saber que la respuesta viene de un proceso del sistema.

---

### Lo que se evaluará

- Que la sustitución de palabras reservadas funcione correctamente con datos reales
- Que la invocación al proceso esté correctamente encapsulada en la capa de infraestructura
- Que los errores (CLI no instalada, timeout, proceso fallido) estén manejados y devuelvan códigos HTTP apropiados
- Que la respuesta quede persistida correctamente en la BBDD con su FK a la incidencia
- Que el endpoint esté definido en el contrato OpenAPI
- Que exista al menos un test unitario del servicio con el proceso mockeado

> 🎯 El objetivo es aprender a integrar herramientas externas en una arquitectura en capas de forma limpia, sin acoplar la lógica de negocio a los detalles de infraestructura.

---

## 🔀 Flujo de trabajo con Git (obligatorio)

Todo el trabajo debe realizarse mediante **Pull Requests**. No se permite hacer push directo a `main`.

### Ramas personales

Cada persona trabajará en su propia rama base durante todo el proyecto:

- Persona A → `dev/persona-a`
- Persona B → `dev/persona-b`

Las funcionalidades se desarrollan en subramas creadas desde la rama personal (ej. `dev/persona-a/feature/crud-usuarios`) y se integran en ella antes de abrir PR a `main`.

### Reglas obligatorias

- Cada tarea o funcionalidad debe desarrollarse en una rama propia con nombre descriptivo (ej. `feature/crud-usuarios`, `feature/repositorio-incidencias`)
- **Toda PR debe ser revisada y aprobada por el dueño del repositorio: [@JuanCBM](https://github.com/JuanCBM)**
- No se puede hacer merge sin aprobación previa
- Los comentarios de revisión deben responderse y resolverse antes del merge
- Las PRs deben tener una descripción clara de qué se implementa y por qué

### Flujo esperado

```
rama feature → PR → revisión de @JuanCBM → cambios si procede → aprobación → merge a main
```

> 🎯 Este flujo forma parte del ejercicio. Aprender a trabajar con revisiones de código es tan importante como el código en sí.

---

## 📦 Entregables finales

**Repositorio Git** con:
- Código fuente
- Migraciones Flyway organizadas
- `podman-compose`
- Contrato OpenAPI (YAML)
- Directorio `.copilot/` con la plantilla de prompt `prompt-sugerencia-resolucion.txt`

**README.md** con:
- Descripción de la arquitectura
- Cómo arrancar el proyecto
- Cómo ejecutar los tests
- **Sección "Decisiones técnicas"** *(obligatoria → ver apartado anterior)*

---

## 🏆 Criterios de evaluación

| Criterio | Descripción |
|----------|-------------|
| Claridad arquitectónica | Separación de capas, principios aplicados |
| Calidad del modelado | Entidades, relaciones, DTOs |
| Uso correcto de JPA y queries | Variedad y adecuación de cada tipo de query |
| Testing realista y útil | Cobertura, parametrización, separación unitario/integración |
| Justificación técnica | Calidad del razonamiento en el README |
| Requisito verbal | Correcta interpretación e implementación del criterio dado en clase |
| Integración Copilot CLI | Encapsulamiento, manejo de errores, persistencia y contrato OpenAPI |
| Código limpio y mantenible | Legibilidad, nombrado, estructura |
| Trabajo en paralelo e integración | Coordinación entre personas A y B |
