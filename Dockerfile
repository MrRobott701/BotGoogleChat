# Usar una imagen base de OpenJDK (Java 17 en este caso)
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR al contenedor
COPY target/googlechat-webhook-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que Spring Boot usará (por defecto es 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación JAR
CMD ["java", "-jar", "app.jar"]
