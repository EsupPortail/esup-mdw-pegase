# Changelog
Les modifications notables apportées au projet sont documentées dans ce fichier.

Le format est basé sur [Keep a Changelog](https://keepachangelog.com/fr/1.0.0).

## [Non publié]
### Ajouté
- Détecte la préférence du mode sombre

## [0.2.0] - 2020-04-14
### Ajouté
- Configure l'accès à la base
- Utilise les utilisateurs en base pour le contrôle d'accès

### Changé
- MAJ [Vaadin 14.1.25](https://github.com/vaadin/platform/releases/tag/14.1.25)

## [0.1.0] - 2020-04-08
### Ajouté
- Connexion au serveur LDAP

### Changé
- MAJ [Vaadin 14.1.23](https://github.com/vaadin/platform/releases/tag/14.1.23)
- MAJ [Spring Boot 2.2.6](https://github.com/spring-projects/spring-boot/releases/tag/v2.2.6.RELEASE)
- Les utilisateurs sont chargés depuis l'annuaire LDAP
- Le bouton 'utilisateur' en haut a droite contient les initiales de l'utilisateur
- Refactoring tests
- CI: Utilise la template du projet [ci-config](https://gitlab.univ-lorraine.fr/dn-sied/ci-config)

## [0.0.32] - 2020-03-13
### Ajouté
- Permet de configurer la clé CAS via la propriété `cas.key`

### Changé
- Refactorise la navigation via tabs (cf https://vaadin.com/components/vaadin-app-layout/java-examples/routing-example)

## [0.0.31] - 2020-03-03
### Ajouté
- Ajout d'un bouton déconnexion dans le menu

### Changé
- MAJ [Vaadin 14.1.18](https://github.com/vaadin/platform/releases/tag/14.1.18)
- MAJ [Spring Boot 2.2.5](https://github.com/spring-projects/spring-boot/releases/tag/v2.2.5.RELEASE)
- Un menu utilisateur est ajouté en haut a droite
- Désactive le log 'Connection remotely closed for ...'

### Corrigé
- Corrige flash erreur après connexion venant de page non protégée (#1)

## [0.0.30] - 2020-02-13
### Ajouté
- ReactiveUtils simplifie la souscription lorsqu'un composant est attaché
- Le logo change en fonction de l'activation ou non du mode sombre

### Changé
- Utilise ReactiveUtils pour les souscriptions des composants

## [0.0.29] - 2020-02-12
### Ajouté
- Prise en charge du mode sombre
- Ajout de CurrentUiService permettant de stocker des variables concernant l'UI courante
- Vue Accueil : Case à cocher permettant de contrôler le mdoe sombre

### Changé
- Checkboxes : le curseur main est utilisé
- Heartbeat diminué à 15 secondes, une UI disparaîtra donc de la liste des connexions une minute après avoir fermé le navigateur
- LumoCustomColorStyleComponent renommé en AppColorStyle

## [0.0.28] - 2020-02-11
### Corrigé
- Correction du yaml GitLab CI : Prise en charge du caractère "deux points" dans la description

## [0.0.19] - 2020-02-11
### Ajouté
- Prise en charge des utilisateurs non autorisés

### Changé
- MAJ [Vaadin 14.1.17](https://github.com/vaadin/platform/releases/tag/14.1.17)

## [0.0.18] - 2020-01-30
### Ajouté
- Prise en charge de [CHANGELOG](CHANGELOG.md)
- Permet de tester le changement de couleur du thème dans la page d'accueil

### Changé
- Refactorisation de la personnalisation de la couleur du thème

## [0.0.16] - 2020-01-23
### Ajouté
- Bouton de demande d'assistance
- Optimisation du favicon

### Changé
- Passage du theme à Lumo
- Refactorisation des properties
- MAJ [Vaadin 14.1.5](https://github.com/vaadin/platform/releases/tag/14.1.5)
- MAJ [Spring Boot 2.2.4](https://github.com/spring-projects/spring-boot/releases/tag/v2.2.4.RELEASE)
