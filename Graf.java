import java.util.List;

public class Graf<E> {
    HashMapa<LinearniSeznam<E>, E> propojeni = new HashMapa<>();  // V, K
    /**
    Vloží nový prvek do grafu O(1) ... O(n)
    @return vrátí celý tento graf
    */
    public Graf<E> VLozitPrvek(E prvek) {
        propojeni.Vlozit(new LinearniSeznam<>(), prvek);
        return this;
    }
    /**
    Smaže daný prvek z grafu O(1) ... O(n)
    @return vrátí true pokud byl prvek smazán, false pokud tento prvek v graf neexistuje
    */
    public boolean SmazatPrvek(E prvek) {
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
        
        seznam1.VymazatPrvekData(prvek2);
        seznam2.VymazatPrvekData(prvek1);
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
}
