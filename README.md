# Prérequis
java 17

Docker

maven

# Configuration Docker Compose

Le fichier docker-compose.yml spécifie la configuration des services Docker. Voici une vue d'ensemble de la configuration :

### Service MySQL (mysql-admin-db)

<img width="543" alt="Capture d'écran 2023-12-21 224227" src="https://github.com/Gakoudev/scolarite-mono/assets/98522554/70028734-d9c3-469b-9b23-a26ca8ba5020">

Ce service utilise l'image Docker MySQL version 8.0 et crée une base de données avec les informations d'identification suivantes :

- Utilisateur MySQL: user
- Mot de passe MySQL: user
- Base de données MySQL: admin-db

Le conteneur expose le port 3306 pour permettre la connexion depuis l'hôte. Les données MySQL sont stockées dans un volume appelé `mysql_data`.

Le service inclut également une vérification de santé qui utilise `mysqladmin ping` pour s'assurer de la disponibilité de MySQL.

### Service PhpMyAdmin (phpmyadmin-admin-db)

<img width="412" alt="Capture d'écran 2023-12-21 224239" src="https://github.com/Gakoudev/scolarite-mono/assets/98522554/c4550d3e-5310-4467-a541-401f941ca6e9">

Ce service utilise l'image Docker la plus récente de PhpMyAdmin. Il dépend du service MySQL et est configuré pour se connecter au serveur MySQL.

PhpMyAdmin est accessible depuis l'hôte sur le port 8085.

### Volumes

<img width="142" alt="Capture d'écran 2023-12-21 224249" src="https://github.com/Gakoudev/scolarite-mono/assets/98522554/8171afff-e2d2-4df0-8c62-fe897c01833b">

Les volumes Docker permettent de persister les données au-delà de la durée de vie des conteneurs. Dans ce projet, le volume `mysql_data` est utilisé pour stocker les données de MySQL de manière persistante. Cela garantit que les données ne sont pas perdues lorsque le conteneur est arrêté.

## Comment utiliser

1. Clonez ce référentiel sur votre machine locale.
2. Assurez-vous que Docker et Docker Compose sont installés.
3. Exécutez la commande `docker-compose up` dans le répertoire du projet.

<img width="856" alt="Capture d'écran 2023-12-21 221933" src="https://github.com/Gakoudev/scolarite-mono/assets/98522554/4884df65-8c82-45cb-b113-4e3237d5fc62">

## Comment tester

Pour vous assurer que l'environnement Docker fonctionne correctement, suivez ces étapes de test après avoir exécuté la commande `docker-compose up` :

1. **Vérification de la disponibilité de MySQL :**
   - Exécutez la commande suivante pour voir les conteneurs en cours d'exécution :
     ```bash
     docker ps
     ```
     <img width="947" alt="Capture d'écran 2023-12-21 222041" src="https://github.com/Gakoudev/scolarite-mono/assets/98522554/c930ef9d-ea97-4035-9c98-b005911d81a1">

   - Localisez le conteneur MySQL dans la liste.
   - Exécutez la commande de test de santé pour MySQL :
     ```bash
     docker exec -it nom_du_conteneur mysqladmin ping -h localhost -u user --password=user
     ```

     <img width="642" alt="Capture d'écran 2023-12-21 222353" src="https://github.com/Gakoudev/scolarite-mono/assets/98522554/80e0069d-dab5-4eef-8a22-b39c093714d3">

     Assurez-vous de remplacer `nom_du_conteneur` par le nom de votre conteneur.

2. **Vérification de l'accès à PhpMyAdmin :**
   - Ouvrez votre navigateur et accédez à [http://localhost:8085](http://localhost:8085).
   
    <img width="164" alt="Capture d'écran 2023-12-21 222635" src="https://github.com/Gakoudev/scolarite-mono/assets/98522554/7f0a75d3-b450-432a-b7bd-3b712bc6e821">

3. **Arrêt et suppression des conteneurs :**
   - Pour arrêter les conteneurs en cours d'exécution, utilisez la combinaison de touches `Ctrl + C` dans le terminal où `docker-compose up` est en cours d'exécution.
   - Pour supprimer les conteneurs, exécutez la commande suivante dans le répertoire du projet :
     ```bash
     docker-compose down
     ```

Ces étapes de test vous permettront de vous assurer que les services MySQL et PhpMyAdmin sont fonctionnels et accessibles. Vous pouvez également adapter ces tests en fonction de vos besoins spécifiques.


## Notes

- Assurez-vous de personnaliser les informations d'identification dans le fichier `docker-compose.yml` selon vos besoins.
- N'oubliez pas de sécuriser vos déploiements en production avec des pratiques appropriées.

## SonarLint

