import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // Méthode pour ajouter un livre
    public void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO Livre (titre, auteur, categorie, nombreExemplaires) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();  // Connection établie via la méthode statique
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getCategorie());
            stmt.setInt(4, livre.getNombreExemplaires());
            stmt.executeUpdate();
            System.out.println("Livre ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour rechercher un livre par ID
    public Livre rechercherLivreParId(int id) {
        String sql = "SELECT * FROM Livre WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombreExemplaires")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour rechercher un livre par titre
    public List<Livre> rechercherLivreParTitre(String titre) {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livre WHERE titre ILIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + titre + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombreExemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Méthode pour lister tous les livres
    public List<Livre> listerTousLesLivres() {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livre";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombreExemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Méthode pour supprimer un livre
    public void supprimerLivre(int id) {
        String sql = "DELETE FROM Livre WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre supprimé avec succès !");
            } else {
                System.out.println("Aucun livre trouvé avec l'ID : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Correction de la méthode getLivre en utilisant rechercherLivreParId
    public Livre getLivre(int id) {
        return rechercherLivreParId(id);  // Utilisation de la méthode existante pour rechercher par ID
    }
}
