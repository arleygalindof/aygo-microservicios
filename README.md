# Proyecto Arquitectura de Microservicios - Plataforma de Transporte (Estilo Uber)

## Descripción general

Este proyecto representa el diseño e implementación de una **arquitectura de software basada en microservicios**, inspirada en el funcionamiento de plataformas de transporte como Uber.  
Su propósito es demostrar la **aplicación práctica de los principios de arquitectura de software, arquitectura empresarial y gobernanza de TI** dentro de un entorno distribuido y escalable.

El sistema permite la **gestión de pasajeros, conductores y viajes**, manteniendo independencia entre componentes.

---

## Arquitectura del sistema

La aplicación está compuesta por varios microservicios, cada uno con su propia responsabilidad, base de datos y endpoints.  
Toda la comunicación externa pasa a través de un **API Gateway**, que actúa como punto de entrada.

<img width="1019" height="735" alt="image" src="https://github.com/user-attachments/assets/9f6ec06c-4663-4dbf-b79d-7cfb7ca270cb" />


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

<img width="280" height="364" alt="image" src="https://github.com/user-attachments/assets/9023fab3-b541-4716-b86f-7779297d10bb" />

Registros JSON

Usando Gateway
<img width="822" height="101" alt="image" src="https://github.com/user-attachments/assets/eb39e7ad-f7e6-45a1-bc07-9e142c021587" />
Mediante microservicio
<img width="835" height="116" alt="image" src="https://github.com/user-attachments/assets/0ad43b84-97d0-4b85-9869-814a0c908c90" />


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

<img width="253" height="356" alt="image" src="https://github.com/user-attachments/assets/4a739093-2156-4ead-b8d8-5fdb98061080" />

Drivers en BD vía Gateway
<img width="817" height="108" alt="image" src="https://github.com/user-attachments/assets/5d6f3daa-ffcb-4fff-91d9-e9e06fb0ce95" />

Vía microservicio 
<img width="805" height="108" alt="image" src="https://github.com/user-attachments/assets/a9d822ec-4e87-4825-989c-8db39d4dcdd8" />


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

<img width="257" height="336" alt="image" src="https://github.com/user-attachments/assets/198bb33b-de32-49d7-b415-742e8dfcd393" />

Formato JSON para viajes (trips)

{"id":1,"passengerId":1,"driverId":1,"origin":"Calle 10 #5-20","destination":"Avenida 30 #15-80","cost":25000}


**Colección en base de datos:**
`trips`

### 4. **APIGateway**
Punto único de acceso para las solicitudes externas.  
Se encarga de redirigir las peticiones al microservicio correspondiente.

<img width="260" height="281" alt="image" src="https://github.com/user-attachments/assets/8656a80e-961d-487f-b6bc-77221ba2fd85" />

**Responsabilidades:**
- Enrutamiento de peticiones.

Funcion lambda para cálculo de tarifas de viaje

<img width="255" height="95" alt="image" src="https://github.com/user-attachments/assets/ce5130e8-ad93-47dc-8f71-a2bb2d995cf0" />

```
package com.aygo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.HashMap;
import java.util.Map;

public class TripCostCalculatorHandler implements RequestHandler<String, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(String input, Context context) {

        double distance = 0.0;
        try {
            distance = Double.parseDouble(input.trim());
        } catch (Exception e) {
            context.getLogger().log("Error parsing input: " + e.getMessage());
        }

        double baseFare = 5000;
        double perKmRate = 2500;
        double total = baseFare + (distance * perKmRate);

        Map<String, Object> response = new HashMap<>();
        response.put("distance", distance);
        response.put("total_cost", total);
        response.put("currency", "COP");

        return response;
    }
}

```

Llamada a función para calcular tarifas desde una función Lambda alojada en AWS

<img width="761" height="688" alt="image" src="https://github.com/user-attachments/assets/beb24a9c-401a-4e1d-bb5d-13bb571b05db" />

# Estructura Docker compose

```
services:
  mongo:
    image: mongo:6
    container_name: ms_mongo
    ports:
      - "27017:27017"
    volumes:
      - mongo-data:/data/db
    networks:
      - aygo-net

  ms-passenger:
    build: ./ms-passenger
    image: ms-passenger:latest
    container_name: ms_passenger
    depends_on:
      - mongo
    ports:
      - "4081:4081"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/transportdb
    networks:
      - aygo-net
      
  ms-driver:
    build: ./ms-driver
    image: ms-driver:latest
    container_name: ms_driver    
    depends_on:
      - mongo
    ports:
      - "4082:4082"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/transportdb
    networks:
      - aygo-net

  ms-trip:
    build: ./ms-trip
    image: ms-trip:latest
    container_name: ms_trip
    depends_on:
      - mongo
    ports:
      - "4083:4083"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/transportdb
    networks:
      - aygo-net
      
  gateway:
    build: ./gateway
    image: gateway:latest
    container_name: ms_gateway
    depends_on:
      - ms-passenger
      - ms-driver      
    ports:
      - "7080:7080"
    networks:
      - aygo-net

volumes:
  mongo-data:

networks:
  aygo-net:
    driver: bridge

```

