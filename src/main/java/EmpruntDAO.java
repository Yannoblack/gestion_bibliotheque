import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    public void inverserDatesRetour() {
        String sql = "UPDATE Emprunt SET dateRetourPrevue = dateRetourEffective, dateRetourEffective = dateRetourPrevue";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Les dates de retour prévue et effective ont été échangées avec succès.");
            } else {
                System.out.println("Aucun emprunt trouvé à mettre à jour.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour des dates : " + e.getMessage());
        }
    }

    // Enregistrer un emprunt
    public void enregistrerEmprunt(Emprunt emprunt) {
        if (emprunt.getDateEmprunt() == null || emprunt.getDateRetourPrevue() == null) {
            System.out.println("Erreur : Les dates d'emprunt ou de retour prévue ne peuvent pas être nulles.");
            return;
        }

        String sql = "INSERT INTO Emprunt (membreId, livreId, dateEmprunt, dateRetourPrevue) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprunt.getMembreId());
            stmt.setInt(2, emprunt.getLivreId());
            stmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            stmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            stmt.executeUpdate();
            System.out.println("Emprunt enregistré avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'enregistrement de l'emprunt : " + e.getMessage());
        }
    }

    // Gérer le retour d’un livre
    public void gererRetour(int idEmprunt, LocalDate dateRetourEffective) {
        if (dateRetourEffective == null) {
            System.out.println("Erreur : La date de retour effective ne peut pas être nulle.");
            return;
        }

        String sql = "UPDATE Emprunt SET dateRetourEffective = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(dateRetourEffective));
            stmt.setInt(2, idEmprunt);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Retour enregistré avec succès !");
            } else {
                System.out.println("Aucun emprunt trouvé avec l'ID : " + idEmprunt);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la gestion du retour : " + e.getMessage());
        }
    }

    // Calculer les pénalités
    public double calculerPenalite(int idEmprunt) {
        String sql = "SELECT dateRetourPrevue, dateRetourEffective FROM Emprunt WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmprunt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LocalDate dateRetourPrevue = rs.getDate("dateRetourPrevue").toLocalDate();
                LocalDate dateRetourEffective = rs.getDate("dateRetourEffective") != null
                        ? rs.getDate("dateRetourEffective").toLocalDate()
                        : null;

                if (dateRetourPrevue == null || dateRetourEffective == null) {
                    System.out.println("Erreur : Date de retour prévue ou effective manquante pour l'emprunt ID " + idEmprunt);
                    return 0;
                }

                // Utilisation de la méthode calculerPenalite d'Emprunt
                Emprunt emprunt = new Emprunt(idEmprunt, rs.getInt("membreId"), rs.getInt("livreId"),
                        dateRetourPrevue, dateRetourEffective);
                return emprunt.calculerPenalite(); // Appel de la méthode de calcul de pénalité
            } else {
                System.out.println("Erreur : Aucun emprunt trouvé avec l'ID : " + idEmprunt);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'accès à la base de données : " + e.getMessage());
        }

        return 0; // Valeur par défaut en cas d'erreur
    }

    // Lister tous les emprunts
    public List<Emprunt> listerTousLesEmprunts() {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM Emprunt";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprunt emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("membreId"),
                        rs.getInt("livreId"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetourPrevue").toLocalDate()
                );
                LocalDate dateRetourEffective = rs.getDate("dateRetourEffective") != null
                        ? rs.getDate("dateRetourEffective").toLocalDate()
                        : null;
                emprunt.setDateRetourEffective(dateRetourEffective);
                emprunts.add(emprunt);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des emprunts : " + e.getMessage());
        }
        return emprunts;
    }

    // Afficher la liste des emprunts
    public void afficherListeDesEmprunts() {
        List<Emprunt> emprunts = listerTousLesEmprunts();
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt trouvé.");
        } else {
            System.out.println("Liste des emprunts :");
            for (Emprunt emprunt : emprunts) {
                emprunt.afficherDetails(); // Utiliser la méthode d'affichage d'Emprunt
            }
        }
    }
}
