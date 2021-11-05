package cat.paucasesnovescifp.spaad.jdbc.db;
import cat.paucasesnovescifp.spaad.jdbc.helpers.JDBCException;

import java.sql.*;

import java.util.Properties;

public class Database {
    String url;
    Properties properties;

    public Database() {}
    public Database(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void llenguesWhere(String url, String ll) {
        try {
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TITOL, FK_LLENGUA\n" +
                    "FROM llengues join llibres on llengues.LLENGUA = llibres.FK_LLENGUA\n" +
                    "WHERE llengues.LLENGUA = " + ll);

            while(rs.next()) {
                String titol = rs.getString("TITOL");
                String llengua = rs.getString("FK_LLENGUA");
                System.out.println(titol + " , " + llengua);
            }
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }
    }

    public void preparedLlenguesWhere(String url, String ll) throws SQLException {
        try {
            String consulta = "SELECT TITOL, FK_LLENGUA\n" +
                    "FROM llengues join llibres on llengues.LLENGUA = llibres.FK_LLENGUA\n" +
                    "WHERE llengues.LLENGUA = " + ll;
            Connection conexion= DriverManager.getConnection(url);
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet rs = sentencia.executeQuery();

            while(rs.next()) {
                String titol = rs.getString("TITOL");
                String llengua = rs.getString("FK_LLENGUA");
                System.out.println(titol + " , " + llengua);
            }
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }


    }

    public void llengues(String url) {
        try {
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TITOL, FK_LLENGUA\n" +
                    "FROM llengues join llibres on llengues.LLENGUA = llibres.FK_LLENGUA");

            while(rs.next()) {
                String titol = rs.getString("TITOL");
                String llengua = rs.getString("FK_LLENGUA");
                System.out.println(titol + " , " + llengua);
            }
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }

    }
}
