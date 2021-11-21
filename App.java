import java.util.List;

public class App {
    public static void main(String[] args) { 
        Graf<String> graf = new Graf<>();

        graf.VLozitPrvek("michal");
        graf.VLozitPrvek("david");
        graf.VLozitPrvek("vojta");
        graf.VLozitPrvek("ondra");
        graf.VLozitPrvek("pavel");
        graf.VLozitPrvek("mirek");
        graf.VLozitPrvek("kuba");
        graf.VLozitPrvek("honza");        // vytvoření prvků v grafu (vertices)
        

        graf.PridatPropojeni("michal", "david");
        graf.PridatPropojeni("michal", "vojta");
        graf.PridatPropojeni("michal", "honza");
        graf.PridatPropojeni("michal", "mirek");

        graf.PridatPropojeni("ondra", "michal");
        graf.PridatPropojeni("ondra", "vojta");
        graf.PridatPropojeni("ondra", "david");
        graf.PridatPropojeni("ondra", "mirek");

        graf.PridatPropojeni("pavel", "michal");
        graf.PridatPropojeni("pavel", "vojta");
        graf.PridatPropojeni("pavel", "david");
        graf.PridatPropojeni("pavel", "mirek");        // vytvoření spojení prvků (edges)

        System.out.printf("----------------------------\n");
        VypsatPropojeni(graf.GetPropojeniPrvku("ondra"));
        VypsatPropojeni(graf.GetPropojeniPrvku("kuba"));
        VypsatPropojeni(graf.GetPropojeniPrvku("david"));         // vypsání propojení určitých prvků
        System.out.printf("----------------------------\n");

        graf.OdebratPropojeni("ondra", "vojta");
        graf.OdebratPropojeni("david", "pavel");
        graf.OdebratPropojeni("david", "ondra");             // odebrání propojení určitých prvků a odebrání prvku

        graf.SmazatPrvek("michal");                          // odebrání prvku odebere také všechny jeho propojení v ostatních prvcích

        VypsatPropojeni(graf.GetPropojeniPrvku("ondra"));
        VypsatPropojeni(graf.GetPropojeniPrvku("kuba"));
        VypsatPropojeni(graf.GetPropojeniPrvku("david"));    // vypsání propojení určitých prvků
        System.out.printf("----------------------------\n");

    }
    public static void VypsatPropojeni(List<String> list) {   // metoda pro vypsání listu
        for (String osoba : list) {
            System.out.printf(osoba + ", ");
        }
        System.out.printf("\n");
    }
}
