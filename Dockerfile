FROM maven:3.8.4-openjdk-17-slim

# Instalar o git
RUN apt-get update && apt-get install -y git

# Definir o diretório de trabalho
WORKDIR /app/servico-de-reservas

# Clonar o repositório
RUN git clone https://github.com/ArtScapin/servico-de-reservas.git /app/servico-de-reservas

## Compilar e executar o projeto com Maven
CMD mvn compile exec:java -Dexec.mainClass="Main"

EXPOSE 8080
