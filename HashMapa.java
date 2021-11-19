import java.util.ArrayList;
import java.util.List;

public class HashMapa<V, K> {                            // pokud jsou v mape dva prvky se stejnym klicem vraci se a maže ten co byl pridan jako prvni
    private List<LinearniSeznam<Prvek>> prvky = new ArrayList<>();
    private int pocetPrvku = 0;
                                                
    private float maxLoadFactor = 1;           // pokud je load factor vetsi mapa se zvetsi                  load factor -> velikostMapy / pocetPrvku 
    private float minLoadFactor = 0.2f;        // pokud je mensi mapa se zmensi
    private float scaleUp = 2;                 // pokud se zvetsuje mapa zvetsi se o tento faktor
    private float scaleDown = 0.5f;            // pokud se zmensuje zmensi se na tento faktor
    private int startSize = 10;                 // velikost mapy pri vytvoreni
    private int minSize = 10;                   // pokud by se pri zmenseni mela mapa zmensit pod tuto velikost tak se nezmensi
    

    /**
    vrátí počet prvků v mapě O(1)
    */
    public HashMapa(float maxLoadFactor, float minLoadFactor, float scaleUp, int startSize) {   // kontruktor s parametry
        this.maxLoadFactor = maxLoadFactor;
        this.minLoadFactor = minLoadFactor;
        this.scaleUp = scaleUp;
        this.scaleDown = 1 / scaleUp;
        this.minSize = startSize;
        this.startSize = startSize;

        prvky = new ArrayList<>(this.startSize);
        for (int i = 0; i < this.startSize; i++) {     // vytvoreni prazdne mapy se startovni velikosti
            prvky.add(new LinearniSeznam<>());
        }
    }
    public HashMapa() {                               // konstruktor bez parametru 
        prvky = new ArrayList<>(startSize);
        for (int i = 0; i < startSize; i++) {     // vytvoreni prazdne mapy se startovni velikosti
            prvky.add(new LinearniSeznam<>());
        }
    }
    /**
    vrátí počet prvků v mapě O(1)
    @return vrátí počet prvků v mapě
    */
    public int GetPocetPrvku() {
        return pocetPrvku;
    }
    /**
    Vytvoří z daných dat nový prvek a vloží ho do mapy na pozici podle daného klíče O(1)
    */
    public void Vlozit(V data, K key) {                                 
        int index = Math.abs(key.hashCode() % prvky.size());           

        if (prvky.get(index) == null) {
            prvky.set(index, new LinearniSeznam<Prvek>());
        }
        prvky.get(index).vlozPosledni(new Prvek(data, key));
        pocetPrvku++;

        if (pocetPrvku / prvky.size() >= maxLoadFactor) {            // zvetseni mapy pokud je potreba
            prvky = NastaveniVelikostiMapy(prvky, scaleUp);
        }
    }
    /**
    Najde a vrátí první hodnotu v mapě podle daného klíče O(1) ... O(n)
    @return vrátí první hodnotu v mapě podle daného klíče pokud v mapě takový není vrátí null
    */
    public V Najit(K key) {                                             
        int index = Math.abs(key.hashCode() % prvky.size());                   

        if (prvky.get(index) != null) {
            List<Prvek> listPrvky = prvky.get(index).ToList();

            for (Prvek prvek : listPrvky) {
                if (prvek.key.equals(key))   
                    return prvek.data;
            }
        }
        return null;
    }
    /**
    Smaže z mapy první prvek který má daný klíč. Změnší mapy pokud po smazání je load factor pod danou hodnotou O(1) ... O(n)
    */
    public void Smazat(K key) {                                          
        int index = Math.abs(key.hashCode() % prvky.size());

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
    /**
    vrátí zvětšenou mapu s velikostí podle daného faktoru O(n)
    @return vrátí zvětšenou mapu s velikostí podle daného faktoru
    */
    private List<LinearniSeznam<Prvek>> NastaveniVelikostiMapy(List<LinearniSeznam<Prvek>> prvkyPuvodni, float scale) {       
        int length = prvkyPuvodni.size();
        int Newlength = Math.round(length * scale);
        List<LinearniSeznam<Prvek>> zvetsenePrvky = new ArrayList<>(Newlength);
        for (int i = 0; i < Newlength; i++) {
            zvetsenePrvky.add(new LinearniSeznam<Prvek>());
        }
        
        for (LinearniSeznam<Prvek> seznam : prvkyPuvodni) {
            while (seznam.GetAtIndex(0) != null) {
                Prvek prvek = seznam.GetAtIndex(0);
                int index = Math.abs(prvek.key.hashCode() % zvetsenePrvky.size());
                zvetsenePrvky.get(index).vlozPosledni(prvek);
                seznam.VymazatPrvni();
            }
        }
        return zvetsenePrvky;
    } 
    /**
    Vypíše hodnoty všech prvků. Vypíše prázdný řádek pokud v daném seznamu není žádný prvek O(n)
    */
    public void Vypsat() {                                      
        for (LinearniSeznam<Prvek> seznam : prvky) {            // 
            if (seznam != null) {
                System.out.printf("\n");
                List<Prvek> list = seznam.ToList();
                for (Prvek prvek : list) {
                    System.out.printf(prvek.data + ", ");
                }
            }
        }
    }
    /**
    Prvek mapy s daným klíčem a hodnotou
    */
    private class Prvek {                                       
        V data;
        K key;
        public Prvek(V data, K key) {
            this.key = key;
            this.data = data;
        }
    }
}
