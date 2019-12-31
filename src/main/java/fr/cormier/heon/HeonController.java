package fr.cormier.heon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.cormier.heonLight.*;
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
        String t = heonPixelDataBase.GetJSON();
        //System.out.println("Get : "+t);
        return t;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/AddSysheon")
    public String AddSysheon(){
        System.out.println("Add a Sys heon");
        heonPixelDataBase.AddMe(new HeonSystemLightDOA("A définir","172.20.10.11",2000));
        return heonPixelDataBase.GetJSON();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/HeonAddLight", consumes = "text/plain")
    @ResponseBody
    public String heonAddLight(@RequestParam String id )  {
        System.out.println("Add Light : " + id);
        HeonSystemLightDOA sys = (HeonSystemLightDOA)heonPixelDataBase.SearchId(id);
        heonPixelDataBase.addLightOnSysHeon(id);
        return heonPixelDataBase.GetJSON();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/HeonAdd", consumes = "text/plain")
    @ResponseBody
    public String heonAdd(@RequestParam String id )  {
        System.out.println("Add Heon Auto: " + id);
        Heon sys = heonPixelDataBase.SearchId(id);
        //heonPixelDataBase.AddMe();
        sys.AddMe();
        System.out.println(heonPixelDataBase.GetJSON());
        return heonPixelDataBase.GetJSON();
    }




    @RequestMapping(method = RequestMethod.POST, path = "/Modifheon")
    @ResponseBody
    public void Modifheon(@RequestBody String  o){
        System.out.println("Modification de heon");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            JsonNode sysnode = objectMapper.readTree(o);
            Heon H = objectMapper.treeToValue(sysnode, Heon.class);
            System.out.println("Modif from React: " + o);
            heonPixelDataBase.ReplaceHeonNode(H);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    //Modification de la lumière
    @RequestMapping(method = RequestMethod.POST, path = "/heon", consumes = "text/plain")
    @ResponseBody
    public String heonPost(@RequestBody String  o )  {
        //System.out.println("Get a POST heon" + o);

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
        //HeonSystemLightDOA sys3 = (HeonSystemLightDOA)heonPixelDataBase.SearchId(id);
        heonPixelDataBase.DeleteId(id);
        return heonPixelDataBase.GetJSON();
    }


   /* @RequestMapping(method = RequestMethod.POST, path = "/Supheon", consumes = "text/plain")
    @ResponseBody
    public String heonSup(@RequestParam String id )  {
        System.out.println("SUP heon : " + id);
        HeonSystemLightDOA sys3 = (HeonSystemLightDOA)heonPixelDataBase.SearchId(id);
        heonPixelDataBase.delSystemLight(sys3);
        return heonPixelDataBase.GetJSON();
    }*/







}
