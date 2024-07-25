import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lire = new Scanner(System.in);
        List<CompteBancaire> listeComptes = new ArrayList<>();
        HashMap<String, CompteBancaire> compteBancaireHashMap = new HashMap<>();
        Banque comptes= new Banque();
        chargerDonnees(comptes);
        while (true) {
            boolean trouver = false;
            String nomTitulaire;
            String intitule;
            String identifiant;
            double montant;


            System.out.println("------------MENU-------------\n" +
                    "1. Ajouter un compte bancaire\n" +
                    "2. Supprimer un compte bancaire.\n" +
                    "3. Modifier un compte bancaire par son identifiant.\n" +
                    "4. Rechercher le compte par nom du titulaire\n" +
                    "5. Lister les comptes bancaires en saisissant une lettre alphabétique\n" +
                    "6. Afficher le nombre de comptes bancaires par type\n" +
                    "7. Afficher les comptes par type.\n" +
                    "8. Afficher les détails d'un compte par son identifiant\n" +
                    "9. Débiter un compte.\n" +
                    "10.Créditer un compte.\n" +
                    "11.Quitter.\n" +

                    "Saisir : "
            );


            int option = lire.nextInt();
            try {
                switch (option) {

                    case 1:
                        System.out.println("Saisir le nom du titulaire :");
                        lire.nextLine();
                        nomTitulaire = lire.nextLine();
                        System.out.println("Saisir l'intitulé du compte :");
                        intitule = lire.nextLine();
                        System.out.println("Saisir le montant :");
                        montant = lire.nextDouble();
                        CompteBancaire nouveauCompte = new CompteBancaire(nomTitulaire,intitule,montant);
                        comptes.ajouterUnCompte(nouveauCompte);
                        System.out.println("Compte ajouté avec succès.");
                        System.out.println(comptes.afficherDetailsDuCompteParIdentifiant(nouveauCompte.getIdentifiant()));
                        chargerDonnees(comptes);
                        break;

                    case 2:
                        System.out.println("Saisir l'identifiant du compte à supprimer :");
                        identifiant = lire.next();
                        if (compteBancaireHashMap.containsKey(identifiant)) {
                            CompteBancaire compteASupprimer = compteBancaireHashMap.get(identifiant);
                            listeComptes.remove(compteASupprimer);
                            compteBancaireHashMap.remove(identifiant);
                            System.out.println("Compte supprimé avec succès.");
                        } else {
                            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
                        }
                        break;

                    case 3:
                        System.out.println("Saisir l'identifiant du compte à modifier :");
                        identifiant = lire.next();
                        if (compteBancaireHashMap.containsKey(identifiant)) {
                            CompteBancaire compteAModifier = compteBancaireHashMap.get(identifiant);
                            // Ajouter ici la logique de modification du compte
                            System.out.println("Compte modifié avec succès.");
                        } else {
                            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
                        }
                        break;

                    case 4:
                        // Rechercher le compte par nom du titulaire
                        System.out.println("Saisir le nom du titulaire à rechercher :");
                        nomTitulaire = lire.next();
                        for (CompteBancaire compte : listeComptes) {
                            if (compte.getNomTitulaire().equals(nomTitulaire)) {
                                System.out.println(compte);
                                trouver = true;
                            }
                        }
                        if (!trouver) {
                            System.out.println("Aucun compte trouvé pour le titulaire " + nomTitulaire);
                        }
                        break;

                    case 5:
                        // Lister les comptes bancaires en saisissant une lettre alphabétique
                        System.out.println("Saisir une lettre alphabétique :");
                        char lettre = lire.next().charAt(0);
                        for (CompteBancaire compte : listeComptes) {
                            if (compte.getNomTitulaire().charAt(0) == lettre) {
                                System.out.println(compte);
                            }
                        }
                        break;
                    case 6:
                        int compteCourant = 0;
                        int compteEpargne = 0;
                        for (CompteBancaire compte : listeComptes) {
                            if (compte instanceof CompteCourant) {
                                compteCourant++;
                            } else if (compte instanceof CompteEpargne) {
                                compteEpargne++;
                            }
                        }
                        System.out.println("Nombre de comptes courants : " + compteCourant);
                        System.out.println("Nombre de comptes épargne : " + compteEpargne);

                        break;
                    case 7:
                        for (CompteBancaire compte : listeComptes) {
                            if (compte instanceof CompteCourant) {
                                System.out.println("Compte courant : " + compte);
                            } else if (compte instanceof CompteEpargne) {
                                System.out.println("Compte épargne : " + compte);
                            }
                        }

                        break;

                    case 8:
                        System.out.println("Saisir l'identifiant du compte à afficher :");
                        identifiant = lire.next();
                        if (compteBancaireHashMap.containsKey(identifiant)) {
                            CompteBancaire compteAAfficher = compteBancaireHashMap.get(identifiant);
                            System.out.println(compteAAfficher);
                        } else {
                            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
                        }
                        break;

                    case 9:
                        System.out.println("Saisir l'identifiant du compte à débiter :");
                        identifiant = lire.next();
                        if (compteBancaireHashMap.containsKey(identifiant)) {
                            System.out.println("Saisir le montant à débiter :");
                            montant = lire.nextDouble();
                            CompteBancaire compte = compteBancaireHashMap.get(identifiant);
                            double nouveauSolde = compte.debiterCompte(montant);
                            if (nouveauSolde != -1) {
                                compte.setSolde(nouveauSolde);
                                System.out.println("Débit effectué avec succès. Nouveau solde : " + nouveauSolde);
                            } else {
                                System.out.println("Solde insuffisant pour effectuer le débit.");
                            }
                        } else {
                            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
                        }

                        break;

                    case 10:
                        System.out.println("Saisir l'identifiant du compte à créditer :");
                        identifiant = lire.next();
                        if (compteBancaireHashMap.containsKey(identifiant)) {
                            System.out.println("Saisir le montant à créditer :");
                            montant = lire.nextDouble();
                            CompteBancaire compte = compteBancaireHashMap.get(identifiant);
                            double nouveauSolde = compte.crediterCompte(montant);
                            compte.setSolde(nouveauSolde);
                            System.out.println("Crédit effectué avec succès. Nouveau solde : " + nouveauSolde);
                        } else {
                            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
                        }
                        break;

                    // Quitter
                    case 11:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
            catch (Exception e){
                System.err.println("Une erreur s'est produite : " + e.getMessage());
            }
        }
    }
    public static void chargerDonnees(Banque comptes) {
        try{
            FileWriter fileWriter= new FileWriter("donnees.txt", false);
            BufferedWriter writer= new BufferedWriter(fileWriter);
            String comptesAsString = comptes.toString();
            writer.write(comptesAsString);
            writer.newLine();
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


}