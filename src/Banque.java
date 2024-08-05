import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Banque {
    private HashMap<String, CompteBancaire> compteBancaireHashMap;

    public Banque() {
        compteBancaireHashMap = new HashMap<>();
    }

    public void ajouterUnCompte(CompteBancaire compte) {
        String numeroCompte = compte.genererNumeroCompte();
        compte.setIdentifiant(numeroCompte);
        compteBancaireHashMap.put(numeroCompte, compte);
    }

    public void supprimerUnCompte(String numeroCompte) {
        compteBancaireHashMap.remove(numeroCompte);
    }

    /*public void modifierUnCompteParIdentifiant(String identifiant, CompteBancaire nouveauCompte) {
        if (compteBancaireHashMap.containsKey(identifiant)) {
            compteBancaireHashMap.put(identifiant, nouveauCompte);
        } else {
            System.out.println("Le compte avec l'identifiant " + identifiant + " n'existe pas.");
        }
    }
    */
     public void modifierUnCompteParIdentifiant(String identifiant, CompteBancaire nouveauCompte) {
        if (compteBancaireHashMap.containsKey(identifiant)) {
            
            nouveauCompte.mettreAJourEnBaseDeDonnees();
            
          
            compteBancaireHashMap.put(identifiant, nouveauCompte);
            
            System.out.println("Compte modifié avec succès.");
        } else {
            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
        }
    }

   /* public void rechercherUnCompteParTitulaire(String nomTitulaire) {
        for (Map.Entry<String, CompteBancaire> entry : compteBancaireHashMap.entrySet()) {
            if (entry.getValue().getNomTitulaire().equals(nomTitulaire)) {
                System.out.println(entry.getValue());
            }
        }
    }
    */
     public void rechercherUnCompteParTitulaire(String nomTitulaire) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT * FROM compte_bancaire WHERE nom_titulaire=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nomTitulaire);
            ResultSet resultSet = statement.executeQuery();

            List<CompteBancaire> comptesTrouves = new ArrayList<>();
            while (resultSet.next()) {
                String identifiant = resultSet.getString("identifiant");
                String intitule = resultSet.getString("intitule");
                double solde = resultSet.getDouble("solde");

                CompteBancaire compte = new CompteBancaire(nomTitulaire, intitule, solde);
                compte.setIdentifiant(identifiant);

                comptesTrouves.add(compte);
            }

            if (comptesTrouves.isEmpty()) {
                System.out.println("Aucun compte trouvé pour le titulaire " + nomTitulaire);
            } else {
                for (CompteBancaire compte : comptesTrouves) {
                    System.out.println(compte);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listerComptesParSaisieA(char lettre) {
        for (Map.Entry<String, CompteBancaire> entry : compteBancaireHashMap.entrySet()) {
            if (entry.getValue().getNomTitulaire().charAt(0) == lettre) {
                System.out.println(entry.getValue());
            }
        }
    }

    public void afficherNombreParType() {
        int nbComptesCourant = 0;
        int nbComptesEpargne = 0;

        for (CompteBancaire compte : compteBancaireHashMap.values()) {
            if (compte instanceof CompteCourant) {
                nbComptesCourant++;
            } else if (compte instanceof CompteEpargne) {
                nbComptesEpargne++;
            }
        }

        System.out.println("Nombre de comptes courant : " + nbComptesCourant);
        System.out.println("Nombre de comptes épargne : " + nbComptesEpargne);
    }

    public void afficherCompteParType() {
        int nbComptesCourant = 0;
        int nbComptesEpargne = 0;

        for (CompteBancaire compte : compteBancaireHashMap.values()) {
            if (compte instanceof CompteCourant) {
                System.out.println("Compte courant : " + compte);
                nbComptesCourant++;
            } else if (compte instanceof CompteEpargne) {
                System.out.println("Compte épargne : " + compte);
                nbComptesEpargne++;
            }
        }

        System.out.println("Nombre de comptes courant : " + nbComptesCourant);
        System.out.println("Nombre de comptes épargne : " + nbComptesEpargne);
    }

    public Boolean afficherDetailsDuCompteParIdentifiant(String identifiant) {
        if (compteBancaireHashMap.containsKey(identifiant)) {
            System.out.println(compteBancaireHashMap.get(identifiant));
        } else {
            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
        }
        return true;
    }

    public void debiterCompte(String identifiant, double montant) {
        if (compteBancaireHashMap.containsKey(identifiant)) {
            CompteBancaire compte = compteBancaireHashMap.get(identifiant);
            double nouveauSolde = compte.debiterCompte(montant);
            compte.setSolde(nouveauSolde);
            System.out.println("Débit effectué avec succès. Nouveau solde : " + nouveauSolde);
        } else {
            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
        }
    }

    public void crediterCompte(String identifiant, double montant) {
        if (compteBancaireHashMap.containsKey(identifiant)) {
            CompteBancaire compte = compteBancaireHashMap.get(identifiant);
            double nouveauSolde = compte.crediterCompte(montant);
            compte.setSolde(nouveauSolde);
            System.out.println("Crédit effectué avec succès. Nouveau solde : " + nouveauSolde);
        } else {
            System.out.println("Aucun compte trouvé avec l'identifiant " + identifiant);
        }
    }

    @Override
    public String toString() {
        return "Banque{" +
                "compteBancaireHashMap=" + compteBancaireHashMap +
                '}';
    }
}
