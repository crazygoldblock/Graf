import java.util.List;

public class Graf<E> {
    HashMapa<LinearniSeznam<E>, E> map = new HashMapa<>();

    public void VLozitPrvek(E prvek) {
        map.Vlozit(new LinearniSeznam<>(), prvek);
    }
    public void SmazatPrvek(E prvek) {
        map.Smazat(prvek);
    }
    public void PridatPropojeni(E prvek1, E prvek2) {
        map.Najit(prvek1).vlozPosledni(prvek2);
        map.Najit(prvek2).vlozPosledni(prvek1);
    }
    public void OdebratPropojeni(E prvek1, E prvek2) {
        map.Najit(prvek1).VymazatPrvekData(prvek2);
        map.Najit(prvek2).VymazatPrvekData(prvek1);
    }
    public List<E> GetPropojeniPrvku(E prvek) {
        return map.Najit(prvek).ToList();
    }
}
