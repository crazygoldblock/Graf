import java.util.List;

public class Graf<V, K> {
    private class Prvek {
        V data;
        LinearniSeznam<Prvek> propojeni;
        public Prvek(V data) {
            this.data = data;
        }
        public void PridatPropojeni(Prvek prvek) {
            propojeni.vlozPosledni(prvek);
        }
        public void OdebratPropojeni(Prvek prvek) {
            propojeni.VymazatPrvekData(prvek);
        }
    }

    HashMapa<Prvek, LinearniSeznam<Prvek>> map = new HashMapa<>();

    public void VLozitPrvek(V data, K key) {
        map.Vlozit(new Prvek(data), key);
    }
    public void SmazatPrvek(K key) {
        map.Smazat(key);
    }
    public void PridatPropojeni(K key1, K key2) {
        map.Najit(key1).PridatPropojeni(map.Najit(key2));
        map.Najit(key2).PridatPropojeni(map.Najit(key1));
    }
    public void OdebratPropojeni(K key1, K key2) {
        map.Najit(key1).OdebratPropojeni(map.Najit(key2));
        map.Najit(key2).OdebratPropojeni(map.Najit(key1));
    }
}
