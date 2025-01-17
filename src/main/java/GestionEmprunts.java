import java.time.LocalDate;
import java.util.ArrayList;

public class GestionEmprunts {
    private ArrayList<Emprunt> emprunts;

    public GestionEmprunts() {
        this.emprunts = new ArrayList<>();
    }

    public void enregistrerEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        System.out.println("Emprunt enregistré pour le membre ID : " + emprunt.getMembreId());
    }

    public void gérerRetour(int idEmprunt, LocalDate dateRetourEffective) {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getIdEmprunt() == idEmprunt) {
                emprunt.setDateRetourEffective(dateRetourEffective);
                double penalite = emprunt.calculerPenalite();
                System.out.println("Retour enregistré. Pénalité : " + penalite + " F CFA");
                return;
            }
        }
        System.out.println("Aucun emprunt trouvé avec l'ID : " + idEmprunt);
    }

    public void afficherTousLesEmprunts() {
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt enregistré.");
        } else {
            for (Emprunt emprunt : emprunts) {
                System.out.println("ID Emprunt: " + emprunt.getIdEmprunt() +
                        ", Membre ID: " + emprunt.getMembreId() +
                        ", Livre ID: " + emprunt.getLivreId() +
                        ", Date d'emprunt: " + emprunt.getDateEmprunt() +
                        ", Date retour prévue: " + emprunt.getDateRetourPrevue() +
                        ", Date retour effective: " + emprunt.getDateRetourEffective());
            }
        }
    }
}
