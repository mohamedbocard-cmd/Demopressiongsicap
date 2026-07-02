# Étape 1 : Build avec Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY src ./src
#VARIABLE D'ENVIRONNEMENT

#ENV SPRING_DATASOURCE_USERNAME=diagana
#ENV SPRING_DATASOURCE_PASSWORD=diagana

# Compiler le projet et créer le jar (sans exécuter les tests)
RUN mvn clean package -DskipTests

# Étape 2 : Image finale (plus légère)
#FROM eclipse-temurin:17-jre AS run
#FROM openjdk:17-jdk-alpine
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copier le jar généré depuis l’étape de build
COPY --from=build /app/target/demo-*.jar demo.jar

# Commande de lancement
ENTRYPOINT ["java", "-jar", "demo.jar"]

#En résumé : Un volume Docker est un mécanisme de stockage persistant
#qui conserve les données même après l'arrêt ou la suppression d'un conteneur,
#essentiel pour les applications nécessitant une persistance des données
#comme les bases de données
