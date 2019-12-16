package fr.cormier.heonLight;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
abstract class Heon {
    protected String id;


    public Heon(){
       id = this.generateId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String GetJSON()  {
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




     protected String generateId(){
        Random random = new Random();
        int val = random.nextInt();
        String Hex = new String();
        return Integer.toHexString(val);

    }





    abstract public void SearchId(String id);
}


