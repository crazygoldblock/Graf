import java.util.ArrayList;
import java.util.List;

public class LinearniSeznam<E> {
    
    private int pocetPrvku;
    private PrvekSeznamu prvni, posledni, aktualni;

    /**
    vrátí počet prvků O(1)
    @return  vrátí počet prvků
    */
    public int getPocetPrvku() {
        return pocetPrvku;
    }
    public class PrvekSeznamu {
        PrvekSeznamu predchozi, nasledujici;
        E data;
    }
    /**
    vypíše první, poslední a aktuální prvek a počet prvků O(1)
    */
    public void Debug() {
        System.out.printf("\n Prvni: " + prvni.data + ", Posledni: " + posledni.data);
        System.out.printf(", Aktualni: " + aktualni.data + ", Pocet Prvku: " + pocetPrvku + "\n");
    }
    /**
    Vloží do seznamu nový prvek s danými daty jako první. Nastaví tento nový prvek jako aktuální O(1)
    */
    public void vlozPrvni(E data) {             
        VlozPrvek(data, null, prvni);
    }
    /**
    Vloží do seznamu nový prvek s danými daty jako poslední. Nastaví tento nový prvek jako aktuální O(1)
    */
    public void vlozPosledni(E data) {          
        VlozPrvek(data, posledni, null);
    }
    /**
    Vloží do seznamu nový prvek s danými daty za aktuální. Nastaví tento nový prvek jako aktuální O(1)
    */
    public void vlozNasledujici(E data) {       
        VlozPrvek(data, aktualni, aktualni.nasledujici);
    }
    /**
    Vloží do seznamu nový prvek s danými daty před katuální  O(1)
    */
    public void vlozPredchozi(E data) {         
        VlozPrvek(data, aktualni.predchozi, aktualni);
    }
    /**
    Vymaže poslední prvek. Pokud je daný prvek aktuální aktuálním prvkem se stane následující, pokud je null tak předchozí  O(1)
    @return  vrátí data vymazaného prvku
    */
    public E VymazatPosledni() {                  
        return VymazatCheck(posledni);
    }
    /**
    Vymaže první prvek. Pokud je daný prvek aktuální aktuálním prvkem se stane následující, pokud je null tak předchozí  O(1)
    @return  vrátí data vymazaného prvku
    */
    public E VymazatPrvni() {                    
        return VymazatCheck(prvni);
    }
    /**
    Vymaže aktuální prvek. aktuálním prvkem se stane následující, pokud je null tak předchozí  O(1)
    @return  vrátí data vymazaného prvku
    */
    public E VymazatAktualni() {                  
        return VymazatCheck(aktualni);
    }
    /**
    Vymaže prvek s danými daty. Pokud je daný prvek aktuální aktuálním prvkem se stane následující, pokud je null tak předchozí  O(n)
    */
    public void VymazatPrvekData(E data) {      
        VymazatPrvek(NajitPrvek(data));
    }
    /**
    vrátí data prvku na daném indexu v seznamu  O(n)
    @return  vrátí data na daném prvku nebo null pokud tento index neexistuje
    */
    public E GetAtIndex(int index) {            
        PrvekSeznamu prvek = PrvekAtIndex(index);
        if (prvek != null)
            return prvek.data;
        return null;
    }
    /**
    Nastaví prvek na daném indexu na danou hodnotu v seznamu  O(n)
    */
    public void SetAtIndex(int index, E data) {      
        PrvekSeznamu prvek = PrvekAtIndex(index);
        if (prvek != null)
            VlozPrvek(data, prvek.predchozi, prvek.nasledujici);
    }
    /**
    Smaže prvek na daném indexu v seznamu. Pokud je daný prvek aktuální aktuálním prvkem se stane následující, pokud je null tak předchozí  O(n)
    */
    public void RemoveAtIndex(int index) {         
        PrvekSeznamu prvek = PrvekAtIndex(index);
        VymazatPrvek(prvek);
    }
    /**
    nastaví prvni, posledni a aktualni prvek na null a pocet prvků na 0  O(1)
    */
    public void Clear() {                            
        prvni = posledni = aktualni = null;
        pocetPrvku = 0;
    }
    /**
    Vypíše data všech prvků
    */
    public void VypsatSeznam() {                     
        PrvekSeznamu prvek = prvni;
        while (prvek != null) {
            System.out.printf(prvek.data + ", ");
            prvek = prvek.nasledujici;
        }
    }
    /**
    vrátí list se všemi daty ze všech prvků které jsou v seznamu  O(n)
    @return  vrátí list se všemi daty, pokud v seznamu není nic uložené vrátí prázdný list
    */
    public List<E> ToList() {                              
        List<E> list = new ArrayList<>();
        PrvekSeznamu prvek = prvni;
        while (prvek != null) {
            list.add(prvek.data);
            prvek = prvek.nasledujici;
        }
        return list;
    }
    /**
    vrátí list se všemi daty ze všech prvků pozpádku které jsou v seznamu  O(n)
    @return vrátí list se všemi daty pozpádku, pokud v seznamu není nic uložené vrátí prázdný list
    */
    public List<E> ToListReversed() {                              
        List<E> list = new ArrayList<>();
        PrvekSeznamu prvek = posledni;
        while (prvek != null) {
            list.add(prvek.data);
            prvek = prvek.predchozi;
        }
        return list;
    }
    /**
    vrátí data posledního prvku O(1)
    @return vrátí data posledního prvku nebo null pokud je poslední null
    */
    public E GetPosledni() {
        if (posledni == null)   
            return null;
        else
            return posledni.data;
    }
    /**
    vrátí data aktuálního prvku O(1)
    @return vrátí data aktuálního prvku nebo null pokud je aktuální null
    */
    public E GetAktualni() {
        if (aktualni == null)   
            return null;
        else
            return aktualni.data;
    }
    /**
    Vymaže prvek ze seznamu O(1)
    @return vrátí data smazaného prvku nebo null pokud byl prvek null
    */
    private E VymazatCheck(PrvekSeznamu prvek) {          
        VymazatPrvek(prvek);
        if (prvek != null)
            return prvek.data;
        return null;
    }
    /**
    Vytvoří nový prvek mezi dvěma danými prvky. Mohou být null pokud bude vkládaný prvek první nebo poslední. Nastaví tento nový prvek jako aktuální  O(1)
    */
    private void VlozPrvek(E data, PrvekSeznamu predchozi, PrvekSeznamu nasledujici) {   
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
    /**
    Vymaže daný prvek ze seznamu. Pokud je daný prvek aktuální aktuálním prvek se stane následující, pokud je null tak předchozí O(1)
    */
    private void VymazatPrvek(PrvekSeznamu prvek) {  
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
    /**
    Najde a vrátí prvek na indexu z argumentu  O(n)
    @return vrtání prvek nebo null pokud takový index neexistuje
    */
    private PrvekSeznamu PrvekAtIndex(int index) {   
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
    /**
    Najde a vrátí prvek se stejnými daty jako v agumentu  O(n)
    @return vrátí prvek nebo null pokud prvek s těmito daty neexistuje  
    */
    private PrvekSeznamu NajitPrvek(E data) {
        PrvekSeznamu prvek = prvni; 
        while (prvek != null) {
            if (prvek.data == data)
                return prvek;
            prvek = prvek.nasledujici;
        }
        return null;
    }
}
