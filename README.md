# Esup-MonDossierWeb pour Pégase


## Technologies
 - Vaadin 14 
 - Spring Boot 2

## Prérequis
- JDK 11+
- Maven

## Configurer le projet
- Créer et compléter un fichier `application.properties` sur le modèle de `/src/main/resources/application.sample.properties`

## Configurer et lancer le projet dans Eclipse
- Importer le projet Maven dans eclipse.
- Démarrer l'application en faisant un clic droit sur `fr.univlorraine.mondosssierweb.Application.java` et en choisissant 'Run As / Java Application'.

## Tâches Maven
- Lancer l'application (hors d'un IDE) :

```
mvn spring-boot:run
```

- Créer le package pour production (création du .war) :

```
mvn clean package -Pproduction
```


