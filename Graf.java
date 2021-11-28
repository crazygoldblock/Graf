import java.util.ArrayList;
import java.util.List;

public class Graf<E, P> {
    private class Spoj {
        E prvek;
        P spoj;
        public Spoj(E prvek, P spoj) {
            this.prvek = prvek; 
            this.spoj = spoj;
        }
        @Override @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {         // porovnání jen podle prvku a ne spoje
            if (obj.getClass() == getClass()) {
                Spoj spoj =  (Spoj)obj;
                if (spoj.prvek.equals(prvek))
                    return true;
            }
            return false;
        }
    }
    HashMapa<LinearniSeznam<Spoj>, E> propojeni = new HashMapa<>();  // hlavní mapa ve které jsou uložené všechny prvky
    /**
    Vloží nový prvek do grafu O(1) ... O(n)
    @return vrátí celý tento graf
    */
    public Graf<E, P> VLozitPrvek(E prvek) {
        LinearniSeznam<Spoj> seznam = new LinearniSeznam<>();
        seznam.vlozPrvni(new Spoj(prvek, null));
        propojeni.Vlozit(seznam, prvek);
        return this;
    }
    /**
    Smaže daný prvek z grafu a prpojení v ostatních prvcích O(1) ... O(n)
    @return vrátí true pokud byl prvek smazán, false pokud tento prvek v graf neexistuje
    */
    public boolean SmazatPrvek(E prvek) {
        List<Spoj> seznam = propojeni.Najit(prvek).ToList();

        for (Spoj spoj : seznam) {
            OdebratPropojeni(prvek, spoj.prvek);
        }
        return propojeni.Smazat(prvek);
    }
    /**
    Přidá prpojení mezi dvěma prvkama O(1) ... O(n)
    @return vrátí true pokud propojení bylo přidáno, false pokud nebylo přidáno kvůli tomu že jeden z prvků nebyl v grafu
    */
    public boolean PridatPropojeni(E prvek1, E prvek2, P spoj) {
        if (prvek1 == null ||prvek2 == null ||spoj == null)
            throw new NullPointerException();

        LinearniSeznam<Spoj> seznam1 = propojeni.Najit(prvek1);
        LinearniSeznam<Spoj> seznam2 = propojeni.Najit(prvek2);

        if (seznam1 == null || seznam2 == null) 
            return false;

        if (seznam1.JePrvekVSeznamu(new Spoj(prvek2, null))) 
            return false;
        if (seznam2.JePrvekVSeznamu(new Spoj(prvek1, null))) 
            return false;
        
        seznam1.vlozPosledni(new Spoj(prvek2, spoj));
        seznam2.vlozPosledni(new Spoj(prvek1, spoj));
        return true;
    }
    /**
    Odebre propojení mezi dvěma prvkama O(1) ... O(n)
    @return vrátí true pokud propojení bylo odebráno, false pokud nebylo odebráno kvůli tomu že jeden z prvků nebyl v grafu
    */
    public boolean OdebratPropojeni(E prvek1, E prvek2) {
        if (prvek1 == null || prvek2 == null) 
            throw new NullPointerException();

        LinearniSeznam<Spoj> seznam1 = propojeni.Najit(prvek1);
        LinearniSeznam<Spoj> seznam2 = propojeni.Najit(prvek2);

        if (seznam1 == null || seznam2 == null) 
            return false;
        
        if (seznam1.VymazatPrvekData(new Spoj(prvek2, null)) == null)
            return false;
        if (seznam2.VymazatPrvekData(new Spoj(prvek1, null)) == null)
            return false;
        return true;
    }
    /**
    vrátí List propojení daného prvku O(n)
    @return vrátí List propojení prvku, pokud je prvek null nebo není v grafu 
    */
    public List<E> GetPropojeniPrvku(E prvek) {
        if (prvek == null)
            throw new NullPointerException();
        LinearniSeznam<Spoj> seznam = propojeni.Najit(prvek);
        if (seznam == null)
            throw new NullPointerException();    
        List<Spoj> spoje = seznam.ToList();
        List<E> prvky = new ArrayList<>();
        for (int i = 0; i < spoje.size(); i++) {
            if (i != 0) {
                prvky.add(spoje.get(i).prvek);
            }
        }
        return prvky;
    }
    /**
    Vypíše všechny prvky v grafu a jejich propojení O(n) 
    */
    public void VypsatVsechnyPropojeni() {
        List<LinearniSeznam<Spoj>> list = propojeni.ToList();

        for (LinearniSeznam<Spoj> list2 : list) {
            List<Spoj> listE = list2.ToList();
            System.out.printf("\n" + listE.get(0).prvek + " - "); 
            for (int i = 0; i < listE.size(); i++) {
                if (i != 0) {
                    if (i != 1)
                        System.out.printf(", "); 
                    System.out.printf(listE.get(i).prvek + ""); 
                }
            }
        }
        System.out.printf("\n"); 
    }
    /**
    Najde a vrátí classu ve spoji mezi dvěmi zadanými prvky O(n)
    @return vrátí classu ve spoji mezi dvěmi zadanými prvky
    */
    public P GetSpoj(E prvek1, E prvek2) {
        LinearniSeznam<Spoj> spoje = propojeni.Najit(prvek1);
        List<Spoj> propojeni = spoje.ToList();
        for (Spoj spoj : propojeni) {
            if (spoj.prvek.equals(prvek2))
                return spoj.spoj;
        }
        return null;
    }
}
