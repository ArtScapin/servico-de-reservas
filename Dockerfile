FROM maven:3.8.4-openjdk-17-slim

# Instalar o git
RUN apt-get update && apt-get install -y git

# Definir o diretório de trabalho
WORKDIR /app/servico-de-reservas

# Clonar o repositório
RUN git clone https://github.com/ArtScapin/servico-de-reservas.git /app/servico-de-reservas
RUN git pull origin main

EXPOSE 8080

# Executa o comando 'mvn package', que compila e empacota o projeto usando o Maven.
RUN ["mvn", "package"]

# Executa o comando 'java -cp target/classes Main', que inicia a execução do aplicativo Java.
ENTRYPOINT ["java", "-cp", "target/classes", "Main"]
