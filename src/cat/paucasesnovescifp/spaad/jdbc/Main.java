package cat.paucasesnovescifp.spaad.jdbc;
import cat.paucasesnovescifp.spaad.jdbc.db.Database;
import cat.paucasesnovescifp.spaad.jdbc.info.Autor;
import cat.paucasesnovescifp.spaad.jdbc.info.Nacionalitat;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<Autor> autors = new ArrayList<Autor>();
        Database db = new Database("jdbc:mysql://localhost:3306/biblioteca?user=root&password=rgccgr2002");
        Nacionalitat nacionalitat = new Nacionalitat("EUA");
        /*Autor allanMoore = new Autor(9990, "Moore, Allan", null, nacionalitat.getNacionalitat(), null);
        autors.add(allanMoore);
        Autor frankMiller = new Autor(9991, "Miller, Frank", null, nacionalitat.getNacionalitat(), null);
        autors.add(frankMiller);*/

        //db.llengues();
        //db.llenguesWhere(db.getUrl(), "'Castellana'");
        //db.preparedLlenguesWhere(db.getUrl(), "'Castellana'");
        //db.getNacionalitats(db.getUrl());
        //db.getAutor(1);
        /*for (Autor a : db.getAutors(nacionalitat)) {
            System.out.println(a);
        }*/
        //db.setNacionalitatsAutors(autors, nacionalitat);
        db.deleteNacionalitat(nacionalitat);
    }
}
