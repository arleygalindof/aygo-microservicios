# Proyecto Arquitectura de Microservicios - Plataforma de Transporte (Estilo Uber)

## Descripción general

Este proyecto representa el diseño e implementación de una **arquitectura de software basada en microservicios**, inspirada en el funcionamiento de plataformas de transporte como Uber.  
Su propósito es demostrar la **aplicación práctica de los principios de arquitectura de software, arquitectura empresarial y gobernanza de TI** dentro de un entorno distribuido y escalable.

El sistema permite la **gestión de pasajeros, conductores y viajes**, manteniendo independencia entre componentes.

---

## Arquitectura del sistema

La aplicación está compuesta por varios microservicios, cada uno con su propia responsabilidad, base de datos y endpoints.  
Toda la comunicación externa pasa a través de un **API Gateway**, que actúa como punto de entrada.

![Arquitectura General](arquitecturaProyecto.png)

---

## Componentes principales

### 1. **MSPassenger**
Microservicio encargado de la **gestión de pasajeros**.

**Responsabilidades:**
- Registro, consulta, actualización y eliminación de pasajeros.

**Endpoints:**
POST /passengers/register
GET /passengers/{id}
PUT /passengers/{id}
DELETE /passengers/{id}


**Colección en base de datos:**
`passengers`

---

### 2. **MSDriver**
Microservicio encargado de la **gestión de conductores**.

**Responsabilidades:**
- Registro, consulta, actualización y eliminación de conductores.
- Gestión del estado de disponibilidad.
- Consulta de conductores cercanos.

**Endpoints:**
POST /drivers
GET /drivers/{id}
PUT /drivers/{id}
DELETE /drivers/{id}
GET /drivers/status
GET /drivers/nearby?lat={lat}&lng={lng}


**Colección en base de datos:**
`drivers`

---

### 3. **MSTrip**
Microservicio que **gestiona los viajes** entre pasajeros y conductores.

**Responsabilidades:**
- Registro, consulta, actualización y eliminación de viajes.

**Endpoints:**
POST /trip
GET /trip/{id}
PUT /trip/{id}
DELETE /trip/{id}

**Colección en base de datos:**
`trips`

### 4. **APIGateway**
Punto único de acceso para las solicitudes externas.  
Se encarga de redirigir las peticiones al microservicio correspondiente.

**Responsabilidades:**
- Enrutamiento de peticiones.

