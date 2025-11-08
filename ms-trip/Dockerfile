# Etapa 1: Construcción de la aplicación
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente del proyecto
COPY src ./src

# Compila y empaqueta la aplicación (sin ejecutar tests para acelerar)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final, más liviana
FROM eclipse-temurin:17-jdk-alpine

# Define el directorio de trabajo
WORKDIR /app

# Copia el .jar desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto que usará el servicio
EXPOSE 4083

# Comando de inicio
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
