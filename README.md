# Esup-MonDossierWeb pour Pégase


## Technologies
 - Vaadin 14 
 - Spring Boot 2

## Prérequis
- JDK 11+
- Maven
- Tomcat 9

## Configurer le projet
- Créer et compléter un fichier `application.properties` sur le modèle de `/src/main/resources/application.sample.properties`

## Configurer et lancer le projet dans Eclipse
- Importer le projet Maven dans eclipse.
- Démarrer l'application en faisant un clic droit sur `fr.univlorraine.mondosssierweb.Application.java` et en choisissant 'Run As / Java Application'.

## Configurer et lancer le projet dans Tomcat
- Créer le war (cf "Tâches Maven" / "Créer le package pour production")
- Dézipper le war dans le répertoire webapps de Tomcat
- Configurer l'application (cf "Configurer le projet")
- Configurer server.xml de Tomcat : 
-    > Host avec unpackWARs=false 
-    > Ajout du context de l'application dans le Host
- Démarrer Tomcat

## Tâches Maven
- Lancer l'application (hors d'un IDE) :

```
mvn spring-boot:run
```

- Créer le package pour production (création du .war) :

```
mvn clean package -Pproduction
```


