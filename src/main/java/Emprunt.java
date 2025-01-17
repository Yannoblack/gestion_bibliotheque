import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {
    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur principal
    public Emprunt(int idEmprunt, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    // Constructeur simplifié pour un nouvel emprunt
    public Emprunt(int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public Emprunt(int id, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate localDate) {
    }

    // Getters et Setters
    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getMembreId() {
        return membreId;
    }

    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    // Méthodes

    /**
     * Calcule la pénalité en cas de retard.
     * @return La pénalité en fonction du nombre de jours de retard. 0 si pas de retard.
     */
    public double calculerPenalite() {
        if (dateRetourEffective == null) return 0;
        long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
        return joursDeRetard > 0 ? joursDeRetard * 100 : 0;
    }

    /**
     * Affiche les détails de l'emprunt.
     */
    public void afficherDetails() {
        System.out.println("ID Emprunt: " + idEmprunt +
                ", Membre ID: " + membreId +
                ", Livre ID: " + livreId +
                ", Date Emprunt: " + dateEmprunt +
                ", Date Retour Prévue: " + dateRetourPrevue +
                ", Date Retour Effective: " + (dateRetourEffective != null ? dateRetourEffective : "Non retourné"));
    }
}
