public class Osoba {
    private String jmeno;
    private int vek;
    private String prijmeni;
    private boolean pes;

    public Osoba(String jmeno, String prijmeni, int vek, boolean pes) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.pes = pes;
    }

    public boolean isPes() {
        return pes;
    }
    public String getPrijmeni() {
        return prijmeni;
    }
    public int getVek() {
        return vek;
    }
    public String getJmeno() {
        return jmeno;
    }
}
