package cat.paucasesnovescifp.spaad.jdbc;
import cat.paucasesnovescifp.spaad.jdbc.db.Database;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database db = new Database("jdbc:mysql://localhost:3306/biblioteca?user=root&password=CalaPilar");
        //db.llengues(db.getUrl());
        //db.llenguesWhere(db.getUrl(), "'Castellana'");
        db.preparedLlenguesWhere(db.getUrl(), "'Castellana'");
    }
}
