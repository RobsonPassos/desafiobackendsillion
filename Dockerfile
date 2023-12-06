# Use a imagem oficial do OpenJDK como base
FROM openjdk:15

# Crie o diretório de trabalho e copie o JAR para dentro do contêiner
WORKDIR /app
COPY target/desafiobackendsillion-1.0-SNAPSHOT-jar-with-dependencies.jar /app

# Comando a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "desafiobackendsillion-1.0-SNAPSHOT-jar-with-dependencies.jar"]