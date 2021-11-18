import java.util.List;

public class App {
    public static void main(String[] args) {
        Graf<String> graf = new Graf<>();

        graf.VLozitPrvek("michal");
        graf.VLozitPrvek("david");
        graf.VLozitPrvek("vojta");
        graf.VLozitPrvek("ondra");

        graf.PridatPropojeni("michal", "david");
        graf.PridatPropojeni("michal", "vojta");
        graf.PridatPropojeni("ondra", "david");
        graf.PridatPropojeni("david", "vojta");

        List<String> osoby = graf.GetPropojeniPrvku("david");

        for (String osoba : osoby) {
            System.out.printf(osoba + ", ");
        }
        
    }
}
