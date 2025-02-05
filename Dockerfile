# Usa OpenJDK 22 como base
FROM openjdk:22

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR compilado a la imagen
COPY target/rh-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
