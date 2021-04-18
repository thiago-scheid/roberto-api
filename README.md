# Roberto Api

> Api de impressão.

## Requisitos

```sh
Java 11
Plugin Lombok
```

## Instalação OS X & Linux:

**Java 11 - SDKMAN:**

```sh
https://sdkman.io/install
sdk i java 11.0.5-open
```

**Lombok plugin:**

```sh
Intellij: https://projectlombok.org/setup/intellij
Eclipse : https://projectlombok.org/setup/eclipse
VSCode : https://projectlombok.org/setup/vscode
```

## Configuração para Desenvolvimento

Acessar a pasta raiz do projeto:

**Compilar o projeto:**

```sh
sdk use java 11.0.2-open
./mvnw clean package
```

**Executar o coverage:**

```sh
sdk use java 11.0.2-open
./mvnw clean install jacoco:report
```

**Executar o projeto com configuração local:**

```sh
sdk use java 11.0.2-open
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local
```

**Executar o projeto**

```
mvn spring-boot:run
```
