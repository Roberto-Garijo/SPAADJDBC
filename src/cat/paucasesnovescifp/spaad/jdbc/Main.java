package cat.paucasesnovescifp.spaad.jdbc;
import cat.paucasesnovescifp.spaad.jdbc.db.Database;
import cat.paucasesnovescifp.spaad.jdbc.info.Autor;
import cat.paucasesnovescifp.spaad.jdbc.info.Nacionalitat;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("port", "://localhost:3306/");
        properties.setProperty("database", "biblioteca");
        properties.setProperty("user", "?user=root");
        properties.setProperty("password", "&password=rgccgr2002");
        ArrayList<Autor> autors = new ArrayList<Autor>();
        Database db = new Database("jdbc:mysql", properties);
        Nacionalitat nacionalitat = new Nacionalitat("ES");
        Nacionalitat noExiste = new Nacionalitat("naknvnpbpf");

        Autor allanMoore = new Autor(9990, "Moore, Allan", null, nacionalitat.getNacionalitat(), null);
        autors.add(allanMoore);
        Autor frankMiller = new Autor(9991, "Miller, Frank", null, nacionalitat.getNacionalitat(), null);
        autors.add(frankMiller);
        /*Autor roberto = new Autor(9992, "Garijo, Roberto", null, noExiste.getNacionalitat(), null);
        autors.add(roberto);*/
        //db.llengues();
        //db.llenguesWhere(db.getUrl(), "'Castellana'");
        //db.preparedLlenguesWhere(db.getUrl(), "'Castellana'");
        //db.getNacionalitats(db.getUrl());
        //db.getAutor(1);
        /*for (Autor a : db.getAutors(nacionalitat)) {
            System.out.println(a);
        }*/
        //db.setNacionalitatsAutors(autors, nacionalitat);
        //db.deleteNacionalitat(nacionalitat);
        /*System.out.println(db.getAutors(nacionalitat));
        db.setNacionalitatsAutorsTransaccio(autors, nacionalitat);
        System.out.println(db.getAutors(nacionalitat));*/
        //db.setNacionalitatsAutors(autors, nacionalitat);
        db.setNewLlengua("Anglesa", "Peruana");
    }
}
