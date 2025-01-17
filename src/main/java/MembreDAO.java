import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {
    // Ajouter un membre
    public void ajouterMembre(Membre membre) {
        String sql = "INSERT INTO Membre (nom, prenom, email, adhesionDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setString(3, membre.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(membre.getAdhesionDate())); // conversion de LocalDate en java.sql.Date

            stmt.executeUpdate();
            System.out.println("Membre ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Rechercher un membre par ID
    public Membre rechercherMembreParId(int id) {
        String sql = "SELECT * FROM Membre WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("adhesionDate").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Rechercher un membre par nom
    public List<Membre> rechercherMembreParNom(String nom) {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM Membre WHERE nom ILIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nom + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                membres.add(new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("adhesionDate").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    // Supprimer un membre
    public void supprimerMembre(int id) {
        String sql = "DELETE FROM Membre WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Membre supprimé avec succès !");
            } else {
                System.out.println("Aucun membre trouvé avec l'ID : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lister tous les membres
    public List<Membre> listerTousLesMembres() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM Membre";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                membres.add(new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("adhesionDate").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    public Membre getMembre(int i) {
        return null;
    }
}
