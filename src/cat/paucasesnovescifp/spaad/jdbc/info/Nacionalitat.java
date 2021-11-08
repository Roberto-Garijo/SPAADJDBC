package cat.paucasesnovescifp.spaad.jdbc.info;
import cat.paucasesnovescifp.spaad.jdbc.helpers.JDBCException;

public class Nacionalitat {
    String nacionalitat;

    public Nacionalitat(String nacionalitat) {
        if (nacionalitat == null && !nacionalitat.isEmpty() && !nacionalitat.isBlank()) {
            System.err.println("La nacionalitat no pot ser null");
        } else
            this.nacionalitat = nacionalitat;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    @Override
    public String toString() {
        return "Nacionalitat{" +
                "nacionalitat='" + nacionalitat + '\'' +
                '}';
    }
}
