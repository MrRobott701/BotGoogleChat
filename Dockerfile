# Usar una imagen base de Tomcat con OpenJDK 21
FROM tomcat:9.0-jdk21-openjdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /usr/local/tomcat/webapps

# Copiar el archivo WAR al contenedor
COPY target/googlechat-webhook-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Exponer el puerto 8080 para que Tomcat lo use
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]