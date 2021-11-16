import java.util.ArrayList;
import java.util.List;

public class HashMapa<V, K> {                            // pokud jsou v mape dva prvky se stejnym klicem vraci se a maže ten co byl pridan jako prvni
    private List<LinearniSeznam<Prvek>> prvky = new ArrayList<>();
    private int pocetPrvku = 0;
                                                
    private float maxLoadFactor = 1;           // pokud je load factor vetsi mapa se zvetsi                  load factor -> velikostMapy / pocetPrvku 
    private float minLoadFactor = 0.5f;        // pokud je mensi mapa se zmensi
    private float scaleUp = 2;                 // pokud se zvetsuje mapa zvetsi se o tento faktor
    private float scaleDown = 0.5f;            // pokud se zmensuje zmensi se na tento faktor
    private int startSize = 5;                 // velikost mapy pri vytvoreni
    private int minSize = 5;                   // pokud by se pri zmenseni mela mapa zmensit pod tuto velikost tak se nezmensi
    

    public HashMapa(float maxLoadFactor, float minLoadFactor, float scaleUp, float scaleDown, int startSize, int minSize) {   // kontruktor s parametry
        this.maxLoadFactor = maxLoadFactor;
        this.minLoadFactor = minLoadFactor;
        this.scaleUp = scaleUp;
        this.scaleDown = scaleDown;
        this.minSize = minSize;
        this.startSize = startSize;

        prvky = new ArrayList<LinearniSeznam<Prvek>>(this.startSize);  // vytvoreni prazdne mapy se startovni velikosti
    }
    public HashMapa() {                               // konstruktor bez parametru 
        prvky = new ArrayList<LinearniSeznam<Prvek>>(this.startSize);  // vytvoreni prazdne mapy se startovni velikosti
    }
    public int GetPocetPrvku() {
        return pocetPrvku;
    }

    public void Vlozit(V data, K key) {                               // vlozeni dalsiho prvku s klicem     O(1) 
        int index = key.hashCode() % prvky.size();             

        if (prvky.get(index) == null) {
            prvky.set(index, new LinearniSeznam<Prvek>());
        }
        prvky.get(index).vlozPosledni(new Prvek(data, key));
        pocetPrvku++;

        if (pocetPrvku / prvky.size() >= maxLoadFactor) {            // zvetseni mapy pokud je potreba
            prvky = NastaveniVelikostiMapy(prvky, scaleUp);
        }
    }
    
    public V Najit(K key) {                                          // vrati data prvku ktery ma stejny klic jako zadany pokud je takovy v mape   O(1) ... O(n)
        int index = key.hashCode() % prvky.size();                   // pokud nenajde vrati null

        if (prvky.get(index) != null) {
            List<Prvek> listPrvky = prvky.get(index).ToList();

            for (Prvek prvek : listPrvky) {
                if (prvek.key.equals(key))   
                    return prvek.data;
            }
        }
        return null;
    }
    public void Smazat(K key) {                                    // smazani prvku podle klice         O(1) ... O(n)
        int index = key.hashCode() % prvky.size();

        if (prvky.get(index) != null) {
            List<Prvek> listPrvky = prvky.get(index).ToList();

            if (listPrvky.size() > 0) {
                for (int i = 0; i < listPrvky.size(); i++) {
                    if (listPrvky.get(i).key.equals(key)) {
                        prvky.get(index).RemoveAtIndex(i);
                        pocetPrvku--;
                        if (pocetPrvku / prvky.size() <= minLoadFactor && prvky.size() * scaleDown >= minSize) {          // zmenseni mapy pokud je potreba
                            prvky = NastaveniVelikostiMapy(prvky, scaleDown);
                        }
                        return;
                    }
                }
            }
        }
    } 
    public List<LinearniSeznam<Prvek>> NastaveniVelikostiMapy(List<LinearniSeznam<Prvek>> prvkyPuvodni, float scale) {       // zmeneni velikosti mapy      O(n)
        int length = prvkyPuvodni.size();
        List<LinearniSeznam<Prvek>> zvetsenePrvky = new ArrayList<>();
        for (int i = 0; i < length * scale; i++) {
            zvetsenePrvky.add(new LinearniSeznam<Prvek>());
        }
        
        for (LinearniSeznam<Prvek> seznam : prvkyPuvodni) {
            while (seznam.GetAtIndex(0) != null) {
                Prvek prvek = seznam.GetAtIndex(0);
                int index = prvek.key.hashCode() % zvetsenePrvky.size();
                zvetsenePrvky.get(index).vlozPosledni(prvek);
                seznam.VymazatPrvni();
            }
        }
        return zvetsenePrvky;
    } 

    public void Vypsat() {                                      // vypsani vsech prvku v mape         O(n)
        for (LinearniSeznam<Prvek> seznam : prvky) {            // vypise prazdny radek pokud v danem seznamu neni zadny prvek
            if (seznam != null) {
                System.out.printf("\n");
                List<Prvek> list = seznam.ToList();
                for (Prvek prvek : list) {
                    System.out.printf(prvek.data + ", ");
                }
            }
        }
    }

    private class Prvek {                                       // prvek mapy
        V data;
        K key;
        public Prvek(V data, K key) {
            this.key = key;
            this.data = data;
        }
    }
}
