import java.util.List;

public class Graf<E> {
    HashMapa<LinearniSeznam<E>, E> propojeni = new HashMapa<>();  // V, K

    public void VLozitPrvek(E prvek) {
        propojeni.Vlozit(new LinearniSeznam<>(), prvek);
    }
    public boolean SmazatPrvek(E prvek) {
        return propojeni.Smazat(prvek);
    }
    public boolean PridatPropojeni(E prvek1, E prvek2) {
        if (prvek1 == null || prvek2 == null) 
            return false;
        propojeni.Najit(prvek1).vlozPosledni(prvek2);
        propojeni.Najit(prvek2).vlozPosledni(prvek1);
        return true;
    }
    public boolean OdebratPropojeni(E prvek1, E prvek2) {
        if (prvek1 == null || prvek2 == null) 
            return false;
        propojeni.Najit(prvek1).VymazatPrvekData(prvek2);
        propojeni.Najit(prvek2).VymazatPrvekData(prvek1);
        return true;
    }
    public List<E> GetPropojeniPrvku(E prvek) {
        return propojeni.Najit(prvek).ToList();
    }
}
