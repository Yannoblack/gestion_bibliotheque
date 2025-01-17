import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Méthode pour se connecter à la base de données "bibliotheque"
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Remplacez les valeurs de l'URL de connexion, de l'utilisateur et du mot de passe par les vôtres
            String url = "jdbc:postgresql://localhost:5432/bibliotheque";  // Base de données "bibliotheque"
            String user = "postgres"; // Votre utilisateur PostgreSQL
            String password = "Yannd0r#"; // Votre mot de passe PostgreSQL
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de données réussie.");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
        return connection;
    }
}
