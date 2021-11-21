import java.util.List;

public class Graf<E> {
    HashMapa<LinearniSeznam<E>, E> propojeni = new HashMapa<>();  // V, K
    /**
    Vloží nový prvek do grafu O(1) ... O(n)
    @return vrátí celý tento graf
    */
    public Graf<E> VLozitPrvek(E prvek) {
        LinearniSeznam<E> seznam = new LinearniSeznam<>();
        seznam.vlozPrvni(prvek);
        propojeni.Vlozit(seznam, prvek);
        return this;
    }
    /**
    Smaže daný prvek z grafu a prpojení v ostatních prvcích O(1) ... O(n)
    @return vrátí true pokud byl prvek smazán, false pokud tento prvek v graf neexistuje
    */
    public boolean SmazatPrvek(E prvek) {
        List<E> seznam = propojeni.Najit(prvek).ToList();

        for (E prveklistu : seznam) {
            OdebratPropojeni(prvek, prveklistu);
        }
        return propojeni.Smazat(prvek);
    }
    /**
    Přidá prpojení mezi dvěma prvkama O(1) ... O(n)
    @return vrátí true pokud propojení bylo přidáno, false pokud nebylo přidáno kvůli tomu že jeden z prvků nebyl v grafu
    */
    public boolean PridatPropojeni(E prvek1, E prvek2) {
        if (prvek1 == null ||prvek2 == null)
            throw new NullPointerException();

        LinearniSeznam<E> seznam1 = propojeni.Najit(prvek1);
        LinearniSeznam<E> seznam2 = propojeni.Najit(prvek2);

        if (seznam1 == null || seznam2 == null) 
            return false;

        if (seznam1.JePrvekVSeznamu(prvek2)) 
            return false;
        if (seznam2.JePrvekVSeznamu(prvek1)) 
            return false;
        
        seznam1.vlozPosledni(prvek2);
        seznam2.vlozPosledni(prvek1);
        return true;
    }
    /**
    Odebre propojení mezi dvěma prvkama O(1) ... O(n)
    @return vrátí true pokud propojení bylo odebráno, false pokud nebylo odebráno kvůli tomu že jeden z prvků nebyl v grafu
    */
    public boolean OdebratPropojeni(E prvek1, E prvek2) {
        if (prvek1 == null || prvek2 == null) 
            throw new NullPointerException();

        LinearniSeznam<E> seznam1 = propojeni.Najit(prvek1);
        LinearniSeznam<E> seznam2 = propojeni.Najit(prvek2);

        if (seznam1 == null || seznam2 == null) 
            return false;
        
        if (seznam1.VymazatPrvekData(prvek2) == null)
            return false;
        if (seznam2.VymazatPrvekData(prvek1) == null)
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
        LinearniSeznam<E> seznam = propojeni.Najit(prvek);
        if (seznam == null)
            throw new NullPointerException();    
        return seznam.ToList();
    }
    /**
        O(n)
    @return 
    */
    public void VypsatVsechnyPropojeni() {
        List<LinearniSeznam<E>> list = propojeni.ToList();

        for (LinearniSeznam<E> list2 : list) {
            List<E> listE = list2.ToList();
            System.out.printf("\n" + listE.get(0) + " - "); 
            for (int i = 0; i < listE.size(); i++) {
                if (i != 0) {
                    if (i != 1)
                        System.out.printf(", "); 
                    System.out.printf(listE.get(i) + ""); 
                }
            }
        }
        System.out.printf("\n"); 
    }
}
