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

        graf.VypsatVsechnyPropojeni();                 // vypsání všech propojení všch prvků

        graf.OdebratPropojeni("ondra", "vojta");
        graf.OdebratPropojeni("david", "pavel");
        graf.OdebratPropojeni("david", "ondra");             // odebrání propojení určitých prvků a odebrání prvku

        graf.SmazatPrvek("michal");                          // odebrání prvku odebere také všechny jeho propojení v ostatních prvcích

        System.out.printf("----------------------------");
        graf.VypsatVsechnyPropojeni();                         // vypsání všech prpojení všch prvků

    }
}
