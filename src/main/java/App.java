import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Créer des instances des classes de gestion
        LivreDAO livreDAO = new LivreDAO();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();
        GestionMembres gestionMembres = new GestionMembres();

        // Menu de l'application
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Ajouter un membre");
            System.out.println("3. Emprunter un livre");
            System.out.println("4. Retourner un livre");
            System.out.println("5. Rechercher un livre");
            System.out.println("6. Lister tous les livres");
            System.out.println("7. Lister tous les membres");
            System.out.println("8. Afficher la liste des emprunts");
            System.out.println("9. Quitter");

            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    // Ajouter un livre
                    System.out.print("Titre du livre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur du livre : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Catégorie du livre : ");
                    String categorie = scanner.nextLine();
                    System.out.print("Nombre d'exemplaires : ");
                    int nombreExemplaires = scanner.nextInt();
                    scanner.nextLine(); // Consommer le retour à la ligne

                    Livre livre = new Livre(titre, auteur, categorie, nombreExemplaires);
                    livreDAO.ajouterLivre(livre);
                    break;

                case 2:
                    // Ajouter un membre
                    System.out.print("Nom du membre : ");
                    String nomMembre = scanner.nextLine();
                    System.out.print("Prénom du membre : ");
                    String prenomMembre = scanner.nextLine();
                    System.out.print("Email du membre : ");
                    String emailMembre = scanner.nextLine();
                    System.out.print("Date d'adhésion (YYYY-MM-DD) : ");
                    String adhesionDateStr = scanner.nextLine();
                    LocalDate adhesionDate = LocalDate.parse(adhesionDateStr);

                    Membre membre = new Membre(nomMembre, prenomMembre, emailMembre, adhesionDate);
                    membreDAO.ajouterMembre(membre);
                    break;

                case 3:
                    // Emprunter un livre
                    System.out.print("ID du membre : ");
                    int membreIdEmprunt = scanner.nextInt();
                    System.out.print("ID du livre : ");
                    int livreIdEmprunt = scanner.nextInt();
                    scanner.nextLine(); // Consommer le retour à la ligne
                    System.out.print("Date de retour prévue (YYYY-MM-DD) : ");
                    String dateRetourPrevueStr = scanner.nextLine();
                    LocalDate dateRetourPrevue = LocalDate.parse(dateRetourPrevueStr);

                    Emprunt emprunt = new Emprunt(membreIdEmprunt, livreIdEmprunt, LocalDate.now(), dateRetourPrevue);
                    empruntDAO.enregistrerEmprunt(emprunt);
                    break;

                case 4:
                    // Retourner un livre
                    System.out.print("ID de l'emprunt : ");
                    int empruntIdRetour = scanner.nextInt();
                    scanner.nextLine(); // Consommer le retour à la ligne
                    System.out.print("Date de retour effective (YYYY-MM-DD) : ");
                    String dateRetourEffectiveStr = scanner.nextLine();
                    LocalDate dateRetourEffective = LocalDate.parse(dateRetourEffectiveStr);

                    empruntDAO.gererRetour(empruntIdRetour, dateRetourEffective);
                    break;

                case 5:
                    // Rechercher un livre
                    System.out.print("Titre du livre à rechercher : ");
                    String titreRecherche = scanner.nextLine();
                    livreDAO.rechercherLivreParTitre(titreRecherche).forEach(Livre::afficherDetails);
                    break;

                case 6:
                    // Lister tous les livres
                    livreDAO.listerTousLesLivres().forEach(Livre::afficherDetails);
                    break;

                case 7:
                    // Lister tous les membres
                    membreDAO.listerTousLesMembres().forEach(Membre::afficherDetails);
                    break;

                case 8:
                    // Afficher la liste des emprunts
                    empruntDAO.listerTousLesEmprunts().forEach(Emprunt::afficherDetails);
                    break;

                case 9:
                    // Quitter l'application
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide, veuillez essayer à nouveau.");
                    break;
            }
        }
    }
}
