import java.util.ArrayList;

public class GestionMembres {
    private ArrayList<Membre> membres;

    public GestionMembres() {
        this.membres = new ArrayList<>();
    }

    public void inscrireMembre(Membre membre) {
        membres.add(membre);
        System.out.println("Membre ajouté : " + membre.getNom() + " " + membre.getPrenom());
    }

    public void rechercherMembreParNom(String nom) {
        for (Membre membre : membres) {
            if (membre.getNom().equalsIgnoreCase(nom)) {
                membre.afficherDetails();
                return;
            }
        }
        System.out.println("Aucun membre trouvé avec le nom : " + nom);
    }

    public void afficherTousLesMembres() {
        if (membres.isEmpty()) {
            System.out.println("Aucun membre inscrit.");
        } else {
            for (Membre membre : membres) {
                membre.afficherDetails();
            }
        }
    }
}
