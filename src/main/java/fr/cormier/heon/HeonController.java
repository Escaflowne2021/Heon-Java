package fr.cormier.heon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cormier.heonLight.HeonSystemLightDOA;
import fr.cormier.heonLight.HeonlDataBaseDOA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Map;

@RestController
@Component
public class HeonController {

    @Autowired
    private HeonlDataBaseDOA heonPixelDataBase;

    @RequestMapping(method = RequestMethod.GET, path = "/heon")
    public String heon(){
        System.out.println("Get a GET heon");
        return heonPixelDataBase.GetJSON();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/heon", consumes = "text/plain")
    @ResponseBody
    public String heonPost(@RequestBody String  o )  {
        System.out.println("Get a POST heon" + o);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode sysnode = objectMapper.readTree(o);
            HeonSystemLightDOA sys2 = objectMapper.treeToValue(sysnode, HeonSystemLightDOA.class);
            System.out.println("Is Sys From React: " + sys2.getId());

            sys2.RefreshLight();
            HeonSystemLightDOA sys3 = (HeonSystemLightDOA)heonPixelDataBase.SearchId(sys2.getId());
            sys3 = sys2;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }

}
