FROM maven:3.8.4-openjdk-17-slim

# Instalar o git
RUN apt-get update && apt-get install -y git

# Clonar o repositório
RUN git clone https://github.com/ArtScapin/servico-de-reservas.git

# Definir o diretório de trabalho
WORKDIR /servico-de-reservas

EXPOSE 80

# Compilar e executar o projeto com Maven
CMD mvn compile exec:java -Dexec.mainClass="Main"
