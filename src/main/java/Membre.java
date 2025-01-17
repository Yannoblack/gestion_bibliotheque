import java.time.LocalDate;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate adhesionDate;  // Modifier ici pour utiliser LocalDate au lieu de String

    // Constructeur avec LocalDate pour adhesionDate
    public Membre(int id, String nom, String prenom, String email, LocalDate adhesionDate) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = adhesionDate;
    }

    public Membre(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public Membre(String nom, String prenom, String email, LocalDate adhesionDate) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = adhesionDate;
    }

    // Getters et Setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public LocalDate getAdhesionDate() { return adhesionDate; }

    // Méthode pour afficher les détails du membre
    public void afficherDetails() {
        System.out.println("ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom + ", Email: " + email + ", Date d'adhésion: " + adhesionDate);
    }
}
