 # gestion_de_banque 

 ### projet du cours de programmation haut niveau L3 GL MSI 2024 

  
# DESCRIPTION DU PROJET 

L'application "lmrBank" est une application de gestion bancaire offrant des fonctionnalités de base pour gérer les comptes bancaires, effectuer des transactions et afficher des informations pertinentes aux utilisateurs via une interface graphique réalisée grâce à JavaFX pour fournir une interface graphique conviviale permettant aux utilisateurs d'interagir convenablement avec notre application de gestion des comptes bancaires.
Tout ceci en se connectant à une base de données et utilise des opérations de gestion de base de données pour manipuler les comptes.

# Présentation détaillée de différentes fonctionnalités de l'app

Voici nos fonctionnalités:

Ajouter un compte :

La méthode ajouterCompte(CompteBancaire compte) permet d'ajouter un nouveau compte bancaire à la banque.
Le compte est sauvegardé dans la base de données via la connexion établie.
Une fois ajouté, le compte est enregistré dans la structure de données comptes.

Supprimer un compte :

La méthode supprimerCompte(String numeroCompte) permet de supprimer un compte existant en utilisant son numéro de compte.
Le compte est supprimé à la fois de la base de données et de la structure de données comptes.
Vérifier l'existence d'un compte :
La méthode verifierSiCompteExiste(String numeroCompte) vérifie si un compte avec un numéro de compte spécifique existe dans la base de données.

Modifier un compte :

La méthode modifierCompte(String numeroCompte, String newName, double newSolde) permet de modifier les détails d'un compte existant, tels que le nom du titulaire et le solde.

Rechercher un compte par nom :

La méthode rechercherCompteParNom(String nom) cherche un compte en fonction du nom du titulaire.

Lister les comptes par lettre :

La méthode listerComptesParLettre(char lettre) permet de lister les comptes dont le nom du titulaire commence par une lettre spécifique.

Compter les comptes par type :

La méthode compterComptesParType(Class<? extends CompteBancaire> type) compte le nombre de comptes d'un type spécifique.

Afficher les comptes par type :

La méthode afficherComptesParType(Class<? extends CompteBancaire> type) affiche les comptes d'un type spécifique.

Afficher les détails d'un compte :

La méthode afficherDetailsCompte(String numeroCompte) affiche les détails d'un compte, y compris le titulaire et le solde.

Transférer des fonds :

La méthode transfererFonds(String numeroDeCompteSource, String numeroDeCompteDestination, double montant) permet de transférer un montant spécifique entre deux comptes.

Générer un relevé de compte :

La méthode genererReleve(String numeroCompte) génère un relevé de compte en affichant les détails du compte spécifié.

# INSTRUCTIONS DE COMPILATION ET D'EXECUTION

Assurez-vous que JavaFX est correctement configuré dans votre projet.
Vous pouvez utiliser un outil de gestion de dépendances comme Maven pour ajouter les dépendances JavaFX.

Compilation du projet :

Ouvrez un terminal.
Assurez-vous d'être dans le répertoire racine de votre projet.
Compilez vos fichiers Java en utilisant une commande similaire à javac *.java.

Exécution du projet :

Pour lancer l'application JavaFX, vous devez exécuter la classe principale qui étend Application.
Assurez-vous que la classe principale (HelloApplication.java) contient la méthode start.
Utilisez la commande java pour exécuter votre application, en spécifiant la classe principale.

Utilisation de Maven (optionnel) :

Si vous utilisez Maven, vous pouvez exécuter votre application en utilisant la commande mvn javafx:run.
Exemple des commandes:
javac *.java
java App

# DEMO 
### ecran d'accueil

![Texte alternatif](IMG/image1.png)

### ecran d'operation et de verification dans la base de donnee

![Texte alternatif](IMG/image2.png)

### ecran creation  de compte 
![Texte alternatif](IMG/image3.png)
