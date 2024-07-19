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
