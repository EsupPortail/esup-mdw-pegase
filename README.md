# Esup-MonDossierWeb pour Pégase


## Technologies
 - Vaadin 14 
 - Spring Boot 2

## Prérequis
- JDK 11+
- Maven
- Tomcat 9

## Configurer et lancer le projet depuis Eclipse à partir des sources
- Récupérer les sources depuis le repository github
- Importer le projet en tant que "Projet Maven" dans Eclipse.
- Configurer l'application :
  - Créer et compléter un fichier `application.properties` sur le modèle de `/src/main/resources/application.sample.properties`
- Démarrer l'application en faisant un clic droit sur `fr.univlorraine.mondosssierweb.Application.java` et en choisissant 'Run As / Java Application'.

## Configurer et lancer le projet dans Tomcat
- En partant des sources il faut tout d'abord créer le war (cf "Tâches Maven" / "Créer le package pour production")
- Dézipper le war dans le répertoire webapps de Tomcat
- Configurer l'application :
  - Créer et compléter un fichier `application.properties` sur le modèle de `/WEB-INF/classes/application.sample.properties`
- Configurer server.xml de Tomcat : 
  - Host avec unpackWARs=false 
  - Ajout du context de l'application dans le Host
- Démarrer Tomcat

## Tâches Maven
- Lancer l'application (hors d'un IDE) :

```
mvn spring-boot:run
```

- Créer le package pour production (création du .war) :

```
mvn clean package -P production -Dmaven.test.skip=true
```


