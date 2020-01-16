package fr.cormier.heonLight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.LinkedHashSet;
import java.util.Set;

@JsonDeserialize(as = HeonVirtualLight.class)
public class HeonVirtualLight extends Heon {


    protected Set<String> data = new LinkedHashSet<>();

    @Override
    public void ReplaceME(Heon heon) {

    }

    @Override
    public void AddMe(Heon heon,boolean virtual) {
        this.data.add(heon.id);

    }

    @Override
    public void AddMe() {

    }

    @Override
    public void AddMe(int nb, boolean virtual) {

    }

    @Override
    public void RemoveMe(Heon h) {
        this.data.removeIf(value -> value.equals(h.id));
    }
}
