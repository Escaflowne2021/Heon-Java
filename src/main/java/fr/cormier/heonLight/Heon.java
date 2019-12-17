package fr.cormier.heonLight;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HeonLightDOA.class, name = "Light"),
        @JsonSubTypes.Type(value = HeonSystemLightDOA.class, name = "Syst"),
        @JsonSubTypes.Type(value = HeonPixelDOA.class, name = "Pixel")
})
public abstract class Heon {
    protected String id;
    protected Set<Heon> data = new HashSet<>();

    public Heon() {
        id = this.generateId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String GetJSON() {
        ObjectMapper Obj = new ObjectMapper();
        try {
            String json = Obj.writeValueAsString(this);
            System.out.println(json);
            return json;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected String generateId() {
        Random random = new Random();
        int val = random.nextInt();
        String Hex = new String();
        return Integer.toHexString(val);

    }


     public Heon SearchId(String id) {

         Heon h = data.stream()
                 .filter(Heon -> Heon.getId().equals(id))
                 .findFirst()
                 .orElse(null);
         if (h == null) {
             for (Heon heon : data){
                Heon temp = heon.SearchId(id);
                if (temp != null) {
                    return temp;
                }
             }

             //data.stream().forEach(Heon -> Heon.SearchId(id));
         } else {
             System.out.println("TROUVEEEEE:"+h.getId());
         }

         return h;
     }

    @Override
    public String toString() {
        return "Heon{" +
                "id='" + id + '\'' +
                '}';
    }
}


