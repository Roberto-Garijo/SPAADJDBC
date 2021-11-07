package cat.paucasesnovescifp.spaad.jdbc.info;

import java.sql.Blob;
import java.util.Date;

public class Autor {
    int id;
    String nom_aut;
    Date dnaix_aut;
    String fk_nacionalitat;
    Blob img_aut;

    public Autor(int id, String nom_aut, Date dnaix_aut, String fk_nacionalitat, Blob img_aut) {
        this.id = id;
        this.nom_aut = nom_aut;
        this.dnaix_aut = dnaix_aut;
        this.fk_nacionalitat = fk_nacionalitat;
        this.img_aut = img_aut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_aut() {
        return nom_aut;
    }

    public void setNom_aut(String nom_aut) {
        this.nom_aut = nom_aut;
    }

    public Date getDnaix_aut() {
        return dnaix_aut;
    }

    public void setDnaix_aut(Date dnaix_aut) {
        this.dnaix_aut = dnaix_aut;
    }

    public String getFk_nacionalitat() {
        return fk_nacionalitat;
    }

    public void setFk_nacionalitat(String fk_nacionalitat) {
        this.fk_nacionalitat = fk_nacionalitat;
    }

    public Blob getImg_aut() {
        return img_aut;
    }

    public void setImg_aut(Blob img_aut) {
        this.img_aut = img_aut;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nom_aut='" + nom_aut + '\'' +
                ", dnaix_aut=" + dnaix_aut +
                ", fk_nacionalitat='" + fk_nacionalitat + '\'' +
                ", img_aut=" + img_aut +
                '}';
    }
}
