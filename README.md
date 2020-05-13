# Modèle d'application Université de Lorraine avec Vaadin 14 et Spring Boot 2

## Prérequis
- JDK 11+
- Maven

## Configurer le projet
- Créer et compléter un fichier `application.properties` à la racine du projet sur le modèle de `/src/main/resources/application.sample.properties`

## Configurer et lancer le projet dans Eclipse
- Importer le projet Maven dans eclipse.
- Démarrer l'application en faisant un clic droit sur `fr.univlorraine.blank.Application.java` et en choisissant 'Run As / Java Application'.

## Tâches Maven
- Lancer l'application (hors d'un IDE) :

```
mvn spring-boot:run
```

- Lancer les tests avec docker (recommandé, et docker doit être installé) :

```
mvn -Pdocker-tests verify
```

- Lancer les tests sans docker (chrome doit être installé, et une base mysql doit être disponible telle que configurée dans `src/test/resources/application.properties`) :

```
mvn verify
```

- Créer le package pour production :

```
mvn clean package -Pproduction
```

## Tests d'intégration
### Prérequis
- [Goole Chrome](https://www.google.com/chrome)
ou
- Chrome dans un container Docker :

```
docker run --name chrome -d --shm-size=2g -e START_XVFB=false -p 4444:4444 selenium/standalone-chrome:latest
```

### Tâche maven
- Lancer les tests d'intégration

```
mvn verify
```

## Intégration continue avec GitLab
Si un 'shared runner' est en place sur le serveur GitLab, le 'Runner spécifique' n'est pas nécessaire.
### Runner spécifique
Il faut installer une fois gitlab-runner, puis enregistrer un runner spécifique pour chaque projet, voici comment faire avec docker :
- installer gitlab-runner (linux)

```
docker run -d --name gitlab-runner --restart always -v /srv/gitlab-runner/config:/etc/gitlab-runner -v /var/run/docker.sock:/var/run/docker.sock gitlab/gitlab-runner:latest
```
- installer gitlab-runner (windows)

```
docker run -d --name gitlab-runner --restart always -v c:/gitlab-runner/config:/etc/gitlab-runner -v //var/run/docker.sock:/var/run/docker.sock gitlab/gitlab-runner:latest
```
- enregistrer un runner spécifique (linux), exécuter puis suivre la [documentation](https://docs.gitlab.com/runner/register/index.html)

```
docker run --rm -ti -v /srv/gitlab-runner/config:/etc/gitlab-runner gitlab/gitlab-runner register
```

- enregistrer un runner spécifique (windows), exécuter puis suivre la [documentation](https://docs.gitlab.com/runner/register/index.html)

```
docker run --rm -ti -v c:/gitlab-runner/config:/etc/gitlab-runner gitlab/gitlab-runner register
```

### Variables à renseigner dans GitLab
- Depuis le projet GitLab, aller dans l'onglet `Settings` puis `CI / CD`
- Dans la partie `Variables`, cliquer sur `Expand`
- Renseigner la `Key` `API_TOKEN` avec la valeur obtenue dans le profil :
    - Cliquer en haut à droite, choisir `Settings` puis l'onglet `Access Tokens`
    - Nommer le token `ci-api`, cocher le scope `api` puis cliquer sur `Create personal access token`
    - Copier le token qui apparaît en haut dans `Your New Personal Access Token`
- Renseigner la `Key` `MAVEN_REPO_UL_RELEASES_USER` avec l'utilisateur Nexus `dn-sig-dev`
- Renseigner la `Key` `MAVEN_REPO_UL_RELEASES_PASS` avec le mot de passe Nexus (Habituellement renseigné dans le fichier de configuration Maven `settings.xml`)

## Docker
- Pour construire l'image, buildkit doit être activé:
    - soit en l'activant dans Docker, cf [documentation](https://docs.docker.com/develop/develop-images/#to-enable-buildkit-builds)
    - soit en affectant `1` à la variable d'environnement `DOCKER_BUILDKIT` :
      - linux : `export DOCKER_BUILDKIT=1`
      - windows : `set DOCKER_BUILDKIT=1`


- Construire l'image docker

```
docker build -t blank:latest -t blank:<version> .
```

- Lancer l'application dans un container

```
docker run --name blank -d --restart always -p 8080:8080 -v <chemin complet>/application.properties:/app/application.properties blank:latest
```
