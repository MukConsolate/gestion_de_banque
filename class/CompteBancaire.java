import java.util.Random;

public class CompteBancaire {
    private String nomTitulaire;
    private String identifiant;
    private String intitule;
    private double solde;

    public CompteBancaire(){}

    public CompteBancaire(String nomTitulaire, String intitule, double solde) {
        this.nomTitulaire=nomTitulaire;
        this.intitule = intitule;
        this.solde = solde;
    }

     public void sauvegarderEnBaseDeDonnees() {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO compte_bancaire (nom_titulaire, identifiant, intitule, solde) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nomTitulaire);
            statement.setString(2, identifiant);
            statement.setString(3, intitule);
            statement.setDouble(4, solde);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CompteBancaire chargerDepuisBaseDeDonnees(String identifiant) {
        CompteBancaire compte = null;
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT * FROM compte_bancaire WHERE identifiant = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, identifiant);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nomTitulaire = resultSet.getString("nom_titulaire");
                String intitule = resultSet.getString("intitule");
                double solde = resultSet.getDouble("solde");
                compte = new CompteBancaire(nomTitulaire, intitule, solde);
                compte.setIdentifiant(identifiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compte;
    }


    public String getNomTitulaire() {
        return nomTitulaire;
    }

    public void setNomTitulaire(String nomTitulaire) {
        this.nomTitulaire = nomTitulaire;
    }

    public String getIdentifiant() {
        return identifiant;
    }


    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }


    public String getIntitule() {
        return intitule;
    }


    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }


    public double getSolde() {
        return solde;
    }


    public void setSolde(double solde) {
        this.solde = solde;
    }


    @Override
    public String toString() {
        return "CompteBancaire{" +
                "nomTitulaire='" + nomTitulaire + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", intitule='" + intitule + '\'' +
                ", solde=" + solde +
                '}';
    }

    public double debiterCompte(double montant){
        return solde - montant;
    };

    public double crediterCompte(double montant){
        return solde + montant;
    };

    public String genererNumeroCompte(){
        String numero = "";
        for(int i = 0; i < 10; i++){
            Random random = new Random();
            if(i == 5 || i == 8){
                numero += "-";
            }
            numero += random.nextInt(10);
        }

        return numero;
    }
}
