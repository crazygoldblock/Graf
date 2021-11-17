import java.util.List;

public class App {
    public static void main(String[] args) {
        Graf<Osoba> graf = new Graf<>();

        Osoba michal = new Osoba("michal", "veliky",  19, false);
        Osoba david = new Osoba("david",  "prazdny", 15, true);
        Osoba vojta = new Osoba("vojta",  "maly",    13, true);
        Osoba ondra = new Osoba("ondra",  "pravy",   17, false);

        graf.VLozitPrvek(michal);
        graf.VLozitPrvek(david);
        graf.VLozitPrvek(vojta);
        graf.VLozitPrvek(ondra);

        graf.PridatPropojeni(michal, david);
        graf.PridatPropojeni(michal, vojta);
        graf.PridatPropojeni(ondra, david);
        graf.PridatPropojeni(david, vojta);

        List<Osoba> osoby = graf.GetPropojeniPrvku(david);

        for (Osoba osoba : osoby) {
            System.out.printf(osoba.getJmeno());
        }
        
    }
}
