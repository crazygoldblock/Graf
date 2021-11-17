import java.util.List;

public class Graf<E> {
    HashMapa<LinearniSeznam<E>, E> propojeni = new HashMapa<>();  // V, K

    public void VLozitPrvek(E prvek) {
        propojeni.Vlozit(new LinearniSeznam<>(), prvek);
    }
    public void SmazatPrvek(E prvek) {
        propojeni.Smazat(prvek);
    }
    public void PridatPropojeni(E prvek1, E prvek2) {
        propojeni.Najit(prvek1).vlozPosledni(prvek2);
        propojeni.Najit(prvek2).vlozPosledni(prvek1);
    }
    public void OdebratPropojeni(E prvek1, E prvek2) {
        propojeni.Najit(prvek1).VymazatPrvekData(prvek2);
        propojeni.Najit(prvek2).VymazatPrvekData(prvek1);
    }
    public List<E> GetPropojeniPrvku(E prvek) {
        return propojeni.Najit(prvek).ToList();
    }
}
