# Gestor de Ventas API

API REST desarrollada con Spring Boot para la administracion de ventas.

Actualmente el proyecto se encuentra en una etapa inicial de desarrollo. Ya cuenta con la base de arquitectura del backend y con el modulo `User` implementado, sobre el que se apoyaran las siguientes entidades del sistema.

## Tecnologias

- Java 21
- Spring Boot 4
- Spring Web
- Spring Data JPA
- Spring Validation
- MySQL
- H2
- Lombok
- Maven

## Estado actual

Por el momento el proyecto incluye la gestion basica de usuarios:

- crear usuarios
- listar usuarios
- buscar usuario por id
- actualizar usuarios
- eliminar usuarios

Tambien incluye:

- validaciones sobre `userName`
- DTOs de request y response
- mapper dedicado para `User`
- manejo global de errores
- control de username duplicado

El resto del dominio de ventas todavia se encuentra en desarrollo.

## Arquitectura actual

```text
src/main/java/org/example/gestor_de_ventas_api
├── controller
├── dto/user
├── entity
├── exception
├── mapper
├── repository
└── service
```

La API sigue una organizacion por capas, separando responsabilidades entre acceso HTTP, logica de negocio, persistencia, transformacion de datos y manejo de errores.

## Endpoints actuales

Base path: `/api/users`

### Obtener usuario por id

```http
GET /api/users/{id}
```

### Crear usuario

```http
POST /api/users
Content-Type: application/json
```

Body:

```json
{
  "userName": "juan_123"
}
```

### Listar usuarios

```http
GET /api/users/findAll
```

### Actualizar usuario

```http
PUT /api/users/{id}
Content-Type: application/json
```

Body:

```json
{
  "userName": "juan_admin",
  "roleUser": "ADMIN",
  "stateUser": "ACTIVATED"
}
```

### Eliminar usuario

```http
DELETE /api/users/{id}
```

## Validaciones actuales

En la creacion y actualizacion de usuarios, `userName` debe cumplir estas reglas:

- no puede estar vacio
- debe tener entre 3 y 30 caracteres
- solo puede contener letras, numeros, puntos y guion bajo

## Respuestas de error

La API usa un `GlobalExceptionHandler` para responder errores de forma consistente.

Ejemplos:

- `404 NOT_FOUND` cuando el usuario no existe
- `400 VALIDATION_ERROR` cuando el request es invalido
- `409 RESOURCE_ALREADY_EXISTS` cuando el username ya esta en uso

## Configuracion actual

El perfil activo por defecto es `dev` y el proyecto esta configurado para usar MySQL local.

Propiedades actuales:

- database: `gestor_ventas_db`
- user: `root`
- password: `root`

Archivo:

- `src/main/resources/application-dev.properties`

## Como ejecutar el proyecto

### 1. Crear la base de datos

Crear una base de datos MySQL llamada:

```sql
CREATE DATABASE gestor_ventas_db;
```

### 2. Ejecutar la aplicacion

En Windows:

```bash
mvnw.cmd spring-boot:run
```

O compilar:

```bash
mvnw.cmd clean compile
```

## Proximos pasos

- incorporar nuevas entidades relacionadas a la gestion de ventas
- ampliar la logica de negocio del dominio
- seguir refinando endpoints y manejo de errores

## Autor

Proyecto desarrollado por Juan Guerrero.
