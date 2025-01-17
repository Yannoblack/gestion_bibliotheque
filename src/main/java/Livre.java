public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String categorie;
    private int nombreExemplaires;

    // Constructeur principal sans l'ID (il sera généré par la base de données)
    public Livre(String titre, String auteur, String categorie, int nombreExemplaires) {
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nombreExemplaires = nombreExemplaires;
    }

    // Constructeur avec tous les paramètres (si vous souhaitez inclure un ID, par exemple lors de la récupération des livres)
    public Livre(int id, String titre, String auteur, String categorie, int nombreExemplaires) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nombreExemplaires = nombreExemplaires;
    }

    // Getters et Setters
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getCategorie() { return categorie; }
    public int getNombreExemplaires() { return nombreExemplaires; }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    // Méthodes
    public void afficherDetails() {
        System.out.println("ID: " + id + ", Titre: " + titre + ", Auteur: " + auteur + ", Catégorie: " + categorie + ", Nombre d'exemplaires: " + nombreExemplaires);
    }
}
