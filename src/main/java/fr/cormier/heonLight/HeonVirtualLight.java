package fr.cormier.heonLight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.cormier.heon.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.LinkedHashSet;
import java.util.Set;


@JsonDeserialize(as = HeonVirtualLight.class)
public class HeonVirtualLight extends Heon {

    @Autowired
    private HeonlDataBaseDOA heonlDataBaseDOA;

    @JsonIgnore
    private ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

    @JsonIgnore
    protected Set<Heon> data = new LinkedHashSet<>();

    protected Set<LinkVirtualLight> dataV = new LinkedHashSet<>();
    protected String name = "????";
    protected boolean visible = true;

    public Set<LinkVirtualLight> getDataV() {
        return this.dataV;
    }

    public void setDataV(Set<LinkVirtualLight> dataV) {
        this.dataV = dataV;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void SetAllLight(int r, int g, int b) {
        heonlDataBaseDOA = applicationContext.getBean(HeonlDataBaseDOA.class);

        dataV.stream().forEach(virtualLight -> {
            (heonlDataBaseDOA.SearchId(virtualLight.id_light)).SetAllLight(r, g, b);
        });
    }

    @Override
    public void ReplaceME(Heon heon) {
        HeonVirtualLight t = (HeonVirtualLight) heon;
        this.dataV = t.dataV;
        this.name = t.name;
    }

    @Override
    public void AddMe(Heon heon, boolean virtual) {
        //this.data.add(heon.id);

    }

    @Override
    public void AddMe() {

    }

    @Override
    public void AddMe(int nb, boolean virtual) {

    }

    @Override
    public void RemoveMe(Heon h) {
        this.dataV.removeIf(value -> value.equals(h.id));
    }
}


@JsonDeserialize(as = LinkVirtualLight.class)
class LinkVirtualLight {

    //public String id_lightVirtual;
    public String id_light;

}

