# ms-gestion-academica

Microservicio de gestión académica del sistema escolar AULABO.

Este servicio concentra la lógica académica base del sistema: alumnos, cursos, asignaturas, docentes, cargas académicas, evaluaciones y notas. Está diseñado para ser consumido directamente en pruebas locales o mediante el `ms-api-gateway`.

---

## Estado actual

Para la primera entrega, este microservicio está configurado para trabajar con:

- Java 21
- Spring Boot 4.0.6
- Spring WebMVC
- Spring Data JPA
- PostgreSQL
- Docker
- Spring Boot Actuator
- Springdoc OpenAPI / Swagger

El servicio expone sus endpoints en:

```txt
http://localhost:8081