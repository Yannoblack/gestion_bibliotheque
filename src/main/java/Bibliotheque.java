import java.time.LocalDate;
import java.util.List;

public class Bibliotheque {
    private LivreDAO livreDAO;
    private MembreDAO membreDAO;
    private EmpruntDAO empruntDAO;

    public Bibliotheque() {
        this.livreDAO = new LivreDAO();
        this.membreDAO = new MembreDAO();
        this.empruntDAO = new EmpruntDAO();
    }

    // Fonctionnalités pour les livres
    public void ajouterLivre(Livre livre) {
        livreDAO.ajouterLivre(livre);
    }

    public void supprimerLivre(int id) {
        livreDAO.supprimerLivre(id);
    }

    public List<Livre> listerLivres() {
        return livreDAO.listerTousLesLivres();
    }

    // Fonctionnalités pour les membres
    public void inscrireMembre(Membre membre) {
        membreDAO.ajouterMembre(membre);
    }

    public List<Membre> listerMembres() {
        return membreDAO.listerTousLesMembres();
    }

    // Fonctionnalités pour les emprunts
    public void enregistrerEmprunt(Emprunt emprunt) {
        empruntDAO.enregistrerEmprunt(emprunt);
    }

    public void gererRetourEmprunt(int idEmprunt, LocalDate dateRetourEffective) {
        empruntDAO.gererRetour(idEmprunt, dateRetourEffective);
    }

    public double calculerPenalite(int idEmprunt) {
        return empruntDAO.calculerPenalite(idEmprunt);
    }

    public List<Emprunt> listerEmprunts() {
        return empruntDAO.listerTousLesEmprunts();
    }
}
