import java.util.ArrayList;
import java.util.List;

public class App {  
    static Graf<String, Spoj<String>> graf = new Graf<>();
    public static void main(String[] args) { 
        

        graf.VLozitPrvek("pm"); //    // pikometr 1 000 = 1 nm
        graf.VLozitPrvek("nm"); //    // nanometr 1 000 000 = 1 mm
        graf.VLozitPrvek("mm"); 
        graf.VLozitPrvek("cm");
        graf.VLozitPrvek("dm");
        graf.VLozitPrvek("m");
        graf.VLozitPrvek("km");       // přidání prvků (vertices)
        
        
        graf.PridatPropojeni("pm", "nm", new Spoj<String>("pm", 1000));
        graf.PridatPropojeni("nm", "mm", new Spoj<String>("nm", 1_000_000));
        graf.PridatPropojeni("mm", "cm", new Spoj<String>("mm", 10));

        graf.PridatPropojeni("nm", "dm", new Spoj<String>("nm", 100_000_000));
        graf.PridatPropojeni("mm", "m", new Spoj<String>("mm", 1000));
        graf.PridatPropojeni("m", "km", new Spoj<String>("m", 1000));            // přidání propojení (edges)

        graf.VypsatVsechnyPropojeni();                 // vypsání všech propojení všech prvků
        System.out.printf("----------------------------\n");




        double prevod1 = PrevodJednotek(6, "cm", "km");      // převod 2cm na km
        double prevod2 = PrevodJednotek(5, "km", "cm");
        double prevod3 = PrevodJednotek(4, "m", "cm");
        double prevod4 = PrevodJednotek(3, "dm", "m");
        double prevod5 = PrevodJednotek(2, "mm", "nm");


        System.out.printf("1. Převod: " + prevod1 + "\n" +
                          "2. Převod: " + prevod2 + "\n" +
                          "3. Převod: " + prevod3 + "\n" +
                          "4. Převod: " + prevod4 + "\n" +
                          "5. Převod: " + prevod5);

    }


    public static double PrevodJednotek(float hodnota, String puvodniJednotka, String jednotkaNaPrevedeni) {
        return hodnota * PrevodJednotekRecursive(1d, puvodniJednotka, jednotkaNaPrevedeni, new ArrayList<String>());
    }

    public static double PrevodJednotekRecursive(Double hodnota, String aktualniJednotka, String jednotkaNaPrevedeni, List<String> navstivene) {
        if (jednotkaNaPrevedeni.equals(aktualniJednotka)) {
            return hodnota;
        }
        List<String> spoje = graf.GetPropojeniPrvku(aktualniJednotka);
        List<String> navstivenePrvky = navstivene;
        for (String spoj : spoje) {
            if (navstivenePrvky.contains(spoj))
                continue;
            else
                navstivenePrvky.add(spoj);

            Spoj<String> spojString = graf.GetSpoj(aktualniJednotka, spoj);
            double nasobek = hodnota;
            if (spojString.getSmer().equals(spoj)) 
                nasobek *= spojString.getVaha();
            else 
                nasobek = nasobek * (1d / spojString.getVaha());
            double doubl = PrevodJednotekRecursive(nasobek, spoj, jednotkaNaPrevedeni, navstivenePrvky);
            if (doubl != 0) 
                return doubl;
        }
        return 0d;
    }
}