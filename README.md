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

### Explication des choix techniques

 * Nous avons décidé de réaliser ce projet en Spring afin d'apprendre un nouveau framework et de ne pas faire "juste" du JAVA pour le web
 * Nous nous sommes orienté vers Spring qui est connu, dans la version Spring Boot, car plus simple pour des petites applications
 * La base de données est en MySQL (simple et rapide)
 * Nous avons décidé de faire une API pour comprendre dans un premier temps comment s'organise le framework; Nous avons cherché à comprendre le MVC de Spring avant de s'attaquer aux vues


### Dépendances MAVEN
 * Spring Boot starter Web : pour faire des applicatons web rapidement avec Spring
 * Spring Boot starter data JPA : l'API de Persistance JAVA pour manipuler des classes et les enregistrer en base de données facilement. JPA gère la persistance
 * MySQL Connector Java : fait le lien entre MySQL et JPA
 * Spring Security web : pour ajouter une couche d'authentificaton à l'application, ainsi que des ACL
 * Spring Security Config : pour configurer la sécurité de l'application dans les fichiers de configuration
 * Joda Time : simple librairie pour gérer les timestamp dans les modèle (lors de la création) et les faire persister en base de données
 * Jadira UserType : Joda Time based dates and times to a database using Hibernate
 * Jackson JSON : librairies pour faire une API REST en JSON en JAVA. Ajoute des annotations comme @JsonIgnore etc
 * Hibernate : génère la base de données et met à jour les tables en fonction de nos entité automatiquement

### Les fichiers important
Les fichiers serveur de l'application se trouvent dans src > main > java > twittbaguettes

 * Application.java : contient le main et permet de lancer l'application. permet d'ajouter des ressources (users/messages) au boot de l'application
 * WebSecurityConfig : permet de définir quelles URLs sont protegées, les actions que peuvent faire les utilisateurs. C'est dans ce fichier qu'on écrit les ACL
 * WebSecurityConfiguration : indique à l'application la méthode d'authentificaton utilisée. On redefinir la méthode userDetailsService afin d'indiquer qu'on utilise notre modèle User pour s'authentifier à la place de la classe Userde Spring Security par défaut
 * /controllers : contient les controlleurs de l'applicaton. Définit les "endpoints" de l'api; Les URL à appeler et avec quels parametres
 * /models : les modèles de données. Les annotations définissent les différentes colonnes et type de donnees pour la base
 * /repository : les différentes méthodes pour manipuler les entitées. Principalement pour les sauver, les supprimer, les récupérer. On utilise de la pagination pour limiter les résultats des réponses de l'API
 * /services : les futurs classes /  fonctions qui pourront être utilisée par plusieurs composants de l'application

 ### Ce qui fonctionne
  * L'application se lance
  * Elle est connectée à MySQL
  * Hibernate génère les tables et les contraintes (IDs, clefs étrangères et relations)
  * Des données sont entrées dans les tables au démarrage
  * Modèle User / Message / Role annotés avec getters et setters
  * Relations OneToMany / ManyToOne entre Message <> User et Role <> User
  * CRUD sur les ressources grâce au repositories
  * REST sur les ressources (POST /message, GET /user?id=4 etc.)
  * Pagination sur les messages en GET ( GET /messages?page=4)
  * Spring Security (desactivé pour raison pratique) Les requetes sont refusées si non idenfités
  * Spring Security va chercher l'utilisateur / mot de passe en base de données pour l'authentificaton
  * Encryption des mots de passe (soon)