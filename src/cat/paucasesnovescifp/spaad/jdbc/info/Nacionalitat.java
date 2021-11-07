package cat.paucasesnovescifp.spaad.jdbc.info;

public class Nacionalitat {
    String nacionalitat;

    public Nacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public String getNacionalitat() {
        if (nacionalitat == null) {
            assert false;
            if (nacionalitat.equals("")) {
                nacionalitat = "Nacionalitat desconeguda";
            }
        }
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
