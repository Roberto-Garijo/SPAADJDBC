package cat.paucasesnovescifp.spaad.jdbc.db;
import cat.paucasesnovescifp.spaad.jdbc.helpers.JDBCException;
import cat.paucasesnovescifp.spaad.jdbc.info.Autor;
import cat.paucasesnovescifp.spaad.jdbc.info.Nacionalitat;

import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class Database {
    String url;
    Properties properties;
    ArrayList<Nacionalitat> nacionalitats = new ArrayList<Nacionalitat>();
    Connection con;

    public Database() throws SQLException {}
    public Database(String url, Properties properties) throws SQLException {
        this.url = url;
        this.properties = properties;
        con = DriverManager.getConnection(url + properties.getProperty("port") + properties.getProperty("database") + properties.getProperty("user") + properties.getProperty("password"));
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

    public void llengues() {
        try {
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

    public void llenguesWhere(String ll) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT TITOL, FK_LLENGUA\n" +
                    "FROM llengues join llibres on llengues.LLENGUA = llibres.FK_LLENGUA\n" +
                    "WHERE llengues.LLENGUA = '" + ll +"'");

            while(rs.next()) {
                String titol = rs.getString("TITOL");
                String llengua = rs.getString("FK_LLENGUA");
                System.out.println(titol + " , " + llengua);
            }
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }
    }

    public void preparedLlenguesWhere(String ll) {
        try {
            String consulta = "SELECT TITOL, FK_LLENGUA\n" +
                    "FROM llengues join llibres on llengues.LLENGUA = llibres.FK_LLENGUA\n" +
                    "WHERE llengues.LLENGUA = '" + ll + "'";
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

    public void getNacionalitats() {
        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT NACIONALITAT FROM biblioteca.nacionalitats");

            while(rs.next()) {
                String result = rs.getString("NACIONALITAT");
                Nacionalitat nacionalitat = new Nacionalitat(result);
                nacionalitats.add(nacionalitat);
            }
            for (Nacionalitat n : nacionalitats) {
                System.out.println(n);
            }
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }
    }

    public void getAutor(int autor_pk) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM biblioteca.autors WHERE ID_AUT = '" + autor_pk + "'");

            while(rs.next()) {
                int id = rs.getInt("ID_AUT");
                String nom_aut = rs.getString("nom_aut");
                Date dnaix_aut = rs.getDate("DNAIX_AUT");
                String fk_nacionalitat = rs.getString("FK_NACIONALITAT");
                Blob img_aut = rs.getBlob("IMG_AUT");
                System.out.println(id + ", " + nom_aut +  ", " + dnaix_aut + ", " + fk_nacionalitat + ", " + img_aut);
            }
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }
    }

    public ArrayList<Autor> getAutors(Nacionalitat nacionalitat) {
        ArrayList<Autor> autors = new ArrayList<Autor>();

        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT ID_AUT, NOM_AUT, DNAIX_AUT, FK_NACIONALITAT, IMG_AUT\n" +
                    "FROM biblioteca.autors\n" +
                    "    JOIN nacionalitats on NACIONALITAT = autors.FK_NACIONALITAT\n" +
                    "WHERE NACIONALITAT = '" + nacionalitat.getNacionalitat() + "'");

            while(rs.next()) {
                int id = rs.getInt("ID_AUT");
                String nom_aut = rs.getString("nom_aut");
                Date dnaix_aut = rs.getDate("DNAIX_AUT");
                String fk_nacionalitat = rs.getString("FK_NACIONALITAT");
                Blob img_aut = rs.getBlob("IMG_AUT");
                Autor autor = new Autor(id, nom_aut, dnaix_aut, fk_nacionalitat, img_aut);
                autors.add(autor);
            }
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }
        return autors;
    }

    public void setNacionalitatsAutors(ArrayList<Autor> autors,  Nacionalitat nacionalitat) {
        try {
            String update;
            Statement st = con.createStatement();
            int result = 0;

            for (Autor a : autors) {
                update = "INSERT INTO autors (ID_AUT, NOM_AUT, FK_NACIONALITAT)" +
                        "VALUES ('" + a.getId() + "', '" + a.getNom_aut() + "', '" + nacionalitat.getNacionalitat() + "')";
                result += st.executeUpdate(update);
            }
            System.out.println(result + " lineas afectadas");
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }
    }

    public void deleteNacionalitat(Nacionalitat nacionalitat) {
        try {
            int resultLli_aut = 0;
            int resultAutors = 0;
            int resultNacionalitats = 0;
            Statement st = con.createStatement();

            for (Autor a : getAutors(nacionalitat)) {
                resultLli_aut += st.executeUpdate("DELETE FROM lli_aut WHERE FK_IDAUT = '" + a.getId() + "';");
            }

            resultAutors += st.executeUpdate("DELETE FROM autors WHERE FK_NACIONALITAT = '" + nacionalitat.getNacionalitat() + "';");
            resultNacionalitats += st.executeUpdate("DELETE FROM nacionalitats WHERE NACIONALITAT = '" + nacionalitat.getNacionalitat() + "';");

            System.out.println(resultLli_aut + " files afectades a la taula 'lli_aut'");
            System.out.println(resultAutors + " files afectades a la taula 'autor'");
            System.out.println(resultNacionalitats + " files afectades a la taula 'nacionalitats'");
        } catch (SQLException jdbcException) {
            jdbcException.printStackTrace();
        }
    }

    public void setNacionalitatAutorsTransaccio(Nacionalitat nacionalitat, ArrayList<Autor> autors) throws SQLException {
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        String update;
        int result = 0;

        Savepoint inicio = con.setSavepoint();
        try {
            for (Autor a : autors) {
                update = "Insert into autors (ID_AUT, NOM_AUT, FK_NACIONALITAT) VALUES (" + a.getId() + ", '" + a.getNom_aut() + "', '" + a.getFk_nacionalitat() + "');";
                result += st.executeUpdate(update);
            }
            System.out.println(result + " lineas afectadas");
        } catch (SQLException jdbcException) {
            System.err.println(jdbcException);
            con.rollback(inicio);
        }
    }

    public void setNewLlengua(String llengua, String novaLlengua) throws SQLException {
        con.setAutoCommit(false);

        String comanda = "Insert into llengues VALUES ('" + novaLlengua + "');";
        Statement s = con.createStatement();
        int result = 0;

        Savepoint inicio = con.setSavepoint();
        try {
            result += s.executeUpdate(comanda);
            comanda = "update llibres set FK_LLENGUA = '" + novaLlengua + "' where FK_LLENGUA = '" + llengua + "';";
            result += s.executeUpdate(comanda);
            comanda = "Delete from llibres where FK_LLENGUA = '" + llengua + "';";
            result += s.executeUpdate(comanda);

            System.out.println(result + " lineas afectadas");
        } catch (SQLException jdbcException) {
            con.rollback(inicio);
            System.err.println(jdbcException);
        }
    }
}
