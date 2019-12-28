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

    @RequestMapping(method = RequestMethod.GET, path = "/AddSysheon")
    public String AddSysheon(){
        System.out.println("Add a Sys heon");
        heonPixelDataBase.addSystemLight(new HeonSystemLightDOA("A définir","172.20.10.11",2000));
        return heonPixelDataBase.GetJSON();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/heon", consumes = "text/plain")
    @ResponseBody
    public String heonPost(@RequestBody String  o )  {
        System.out.println("Get a POST heon" + o);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode sysnode = objectMapper.readTree(o);
            HeonSystemLightDOA temp = objectMapper.treeToValue(sysnode, HeonSystemLightDOA.class);
            System.out.println("Is Sys From React: " + temp.getId());

            //sys2.RefreshLight();
            HeonSystemLightDOA sys3 = (HeonSystemLightDOA)heonPixelDataBase.SearchId(temp.getId());
            sys3.setData(temp.getData());
            sys3.RefreshLight();
            //sys3 = sys2;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/Supheon", consumes = "text/plain")
    @ResponseBody
    public String heonSup(@RequestParam String id )  {
        System.out.println("SUP heon : " + id);
        HeonSystemLightDOA sys3 = (HeonSystemLightDOA)heonPixelDataBase.SearchId(id);
        heonPixelDataBase.delSystemLight(sys3);
        return heonPixelDataBase.GetJSON();
    }

}
