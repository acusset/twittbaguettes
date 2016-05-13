# Twittbaguettes
## Des Tweets et des Baguettes

### Installation du projet

* Récupérer le projet sur gitlab.com avec un clone

> git clone https//gitlab.com/a.cusset/Twittbaguettes.git

* Ouvrir le projet avec votre IDE préféré (on sait tous que c'est IntelliJ et pas Eclipse)
* En fonction de l'IDE, il faut pouvoir lui indiquer plusieurs choses
    * L'emplacement du fichier pom.xml pour MAVEN (gestionnaires de dépendances)
    * Que le le projet utilise le Framework Spring
* Copier le fichier application.properties.dist en application.properties
* Remplir le fichier avec vos identifiants de base de données (username, password, nom de la base, emplacement et port)
* Lancer Maven pour installer les dépendances et récupérer les librairies SPRING
* Lancer le fichier Application.java (méthode main) pour lancer le projet
* Tester sur localhost:8080/