# Prérequis
java 17
Docker
maven
# Démonstration d'utilisation
  # Scolarite-mono

Ce référentiel contient un fichier Docker Compose pour déployer deux services Docker : un serveur MySQL (mysql-admin-db) et PhpMyAdmin (phpmyadmin-admin-db).

## Configuration Docker Compose

Le fichier docker-compose.yml spécifie la configuration des services Docker. Voici une vue d'ensemble de la configuration :

### Service MySQL (mysql-admin-db)

Ce service utilise l'image Docker MySQL version 8.0 et crée une base de données avec les informations d'identification suivantes :

- Utilisateur MySQL: user
- Mot de passe MySQL: user
- Base de données MySQL: admin-db

Le conteneur expose le port 3306 pour permettre la connexion depuis l'hôte. Les données MySQL sont stockées dans un volume appelé `mysql_data`.

Le service inclut également une vérification de santé qui utilise `mysqladmin ping` pour s'assurer de la disponibilité de MySQL.

### Service PhpMyAdmin (phpmyadmin-admin-db)

Ce service utilise l'image Docker la plus récente de PhpMyAdmin. Il dépend du service MySQL et est configuré pour se connecter au serveur MySQL.

PhpMyAdmin est accessible depuis l'hôte sur le port 8085.

## Comment utiliser

1. Clonez ce référentiel sur votre machine locale.
2. Assurez-vous que Docker et Docker Compose sont installés.
3. Exécutez la commande `docker-compose up` dans le répertoire du projet.
4. Accédez à PhpMyAdmin depuis votre navigateur à l'adresse [http://localhost:8085](http://localhost:8085).

## Notes

- Assurez-vous de personnaliser les informations d'identification dans le fichier `docker-compose.yml` selon vos besoins.
- N'oubliez pas de sécuriser vos déploiements en production avec des pratiques appropriées.
