package conexiondb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConexionDB {

    
    public static void main(String[] args) {
       String url = "jdbc:mariadb://127.0.0.1:5035/consulta";
        String usuario = "root";
        String contraseña = "5035";

        Connection conexion = null;
        try {
            // CONEXION A LA BASE DE DATOS
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            if (conexion != null) {
                System.out.println("CONEXION EXITOSA");
                
                //REALIZAR LAS CONSULTAS
                String consultaSQL = "SELECT * FROM consultas";
                Statement statement = conexion.createStatement();
                ResultSet resultado = statement.executeQuery(consultaSQL);

                //CARGA DE LA LISTA DE DATOS
                while (resultado.next()) {
                    String Dato1 = resultado.getString("Producto");
                    String Dato2 = resultado.getString("Cantidad");
                    
                    //DESPLEGAR LA LISTA DE DATOS
                    System.out.println("Producto: " + Dato1 + ", Cantidad: " + Dato2);
                }

                // CERRANDO LA CONEXION A LA BASE DE DATOS
                resultado.close();
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println("ERROR DE CONEXION " + e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("NO SE PUDO CERRAR LA CONEXION " + e.getMessage());
            }
        }
    }
    
}
