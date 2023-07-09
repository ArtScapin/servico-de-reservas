FROM maven:3.8.4-openjdk-17-slim

# Instalar o git
RUN apt-get update && apt-get install -y git

# Definir o diretório de trabalho
WORKDIR /app/servico-de-reservas

# Clonar o repositório
RUN git clone https://github.com/ArtScapin/servico-de-reservas.git /app/servico-de-reservas
RUN git pull origin main

EXPOSE 8080

## Compilar e executar o projeto com Maven
RUN ["mvn", "package"]

ENTRYPOINT ["java", "-cp" , "target/classes", "org.example.Main"]
