import java.util.ArrayList;
import java.util.List;

public class LinearniSeznam<E> {
    
    private int pocetPrvku;
    private PrvekSeznamu prvni, posledni, aktualni;

    public int getPocetPrvku() {
        return pocetPrvku;
    }
    public class PrvekSeznamu {
        PrvekSeznamu predchozi, nasledujici;
        E data;
    }
    public void Debug() {
        System.out.printf("\n Prvni: " + prvni.data + ", Posledni: " + posledni.data);
        System.out.printf(", Aktualni: " + aktualni.data + ", Pocet Prvku: " + pocetPrvku + "\n");
    }
    
    public void vlozPrvni(E data) {             // O(1)
        VlozPrvek(data, null, prvni);
    }
    public void vlozPosledni(E data) {          // O(1)
        VlozPrvek(data, posledni, null);
    }
    public void vlozNasledujici(E data) {       // O(1)
        VlozPrvek(data, aktualni, aktualni.nasledujici);
    }
    public void vlozPredchozi(E data) {         // O(1)
        VlozPrvek(data, aktualni.predchozi, aktualni);
    }
   
    public E VymazatPosledni() {                  // O(1)
        return VymazatCheck(posledni);
    }
    public E VymazatPrvni() {                     // O(1)
        return VymazatCheck(prvni);
    }
    public E VymazatAktualni() {                  // O(1)
        return VymazatCheck(aktualni);
    }
    public void VymazatPrvekData(E data) {      // O(n)
        VymazatPrvek(NajitPrvek(data));
    }

    public E GetAtIndex(int index) {            // O(n)
        PrvekSeznamu prvek = PrvekAtIndex(index);
        if (prvek != null)
            return prvek.data;
        return null;
    }
    public void SetAtIndex(int index, E data) {      // O(n)
        PrvekSeznamu prvek = PrvekAtIndex(index);
        if (prvek != null)
            VlozPrvek(data, prvek.predchozi, prvek.nasledujici);
    }
    public void RemoveAtIndex(int index) {           // O(n)
        PrvekSeznamu prvek = PrvekAtIndex(index);
        VymazatPrvek(prvek);
    }

    public void Clear() {                            // O(1)
        prvni = posledni = aktualni = null;
        pocetPrvku = 0;
    }
    public void VypsatSeznam() {                     // O(n)
        PrvekSeznamu prvek = prvni;
        while (prvek != null) {
            System.out.printf(prvek.data + ", ");
            prvek = prvek.nasledujici;
        }
    }
    public List<E> ToList() {                              // O(n)
        List<E> list = new ArrayList<>();
        PrvekSeznamu prvek = prvni;
        while (prvek != null) {
            list.add(prvek.data);
            prvek = prvek.nasledujici;
        }
        return list;
    }

    private E VymazatCheck(PrvekSeznamu prvek) {          // O(1)
        VymazatPrvek(prvek);
        if (prvek != null)
            return prvek.data;
        return null;
    }

    private void VlozPrvek(E data, PrvekSeznamu predchozi, PrvekSeznamu nasledujici) {   // O(1)
        PrvekSeznamu novy = new PrvekSeznamu();
        novy.data = data;

        if (predchozi != null) {
            novy.predchozi = predchozi;
            predchozi.nasledujici = novy;
        }
            
        if (nasledujici != null) {
            novy.nasledujici = nasledujici;
            nasledujici.predchozi = novy;
        }
         
        if (predchozi == posledni)
            posledni = novy;
        if (nasledujici == prvni)
            prvni = novy;

        if (predchozi == null && nasledujici == null) { 
            posledni = novy;
            prvni = novy;
        }
        pocetPrvku++;
        aktualni = novy;
    }
    private void VymazatPrvek(PrvekSeznamu prvek) {  // O(1)
        if (prvek != null) {
            if (prvek.nasledujici != null) {
                aktualni = prvek.nasledujici;
                if (prvek.predchozi != null) {
                    prvek.nasledujici.predchozi = prvek.predchozi;
                } else {
                    prvek.nasledujici.predchozi = null;
                    prvni = prvek.nasledujici;
                }
            }
            if (prvek.predchozi != null) {
                aktualni = prvek.predchozi;
                if (prvek.nasledujici != null) {
                    prvek.predchozi.nasledujici = prvek.nasledujici;
                } else {
                    prvek.predchozi.nasledujici = null;
                    posledni = prvek.predchozi;
                }
            }
            if (prvek.predchozi == null && prvek.nasledujici == null) {
                Clear();
            }
            else
                pocetPrvku--;
        }   
    }
    private PrvekSeznamu PrvekAtIndex(int index) {   // O(n)
        if (index < 0 || index >= pocetPrvku)
            return null;

        PrvekSeznamu prvek = prvni; 
        if (prvek != null) {
            for (int i = 0; i > -1; i++) {
                if (i == index)
                    return prvek;
                if (prvek != null)
                    prvek = prvek.nasledujici;
                else 
                    break;
            }
        }
        return null;
    }
    private PrvekSeznamu NajitPrvek(E data) {   // O(n)
        PrvekSeznamu prvek = prvni; 
        while (prvek != null) {
            if (prvek.data == data)
                return prvek;
            prvek = prvek.nasledujici;
        }
        return null;
    }
}
