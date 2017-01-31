/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.api;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author damon
 */
public class APIMessage {
    public static final String PARAM_ASSIGNER = "=";    
    public static final String PARAM_SEPERATOR = ";";

    public List<Map.Entry<String,String>> params;
    
    APIMessage() {
        this.params = new ArrayList<>();
    }
    
    public void setParam(String fullParam) {
        String[] paramParts = fullParam.split(PARAM_ASSIGNER);
        if (paramParts.length == 2) {
            setParam(paramParts[0], paramParts[1]);
        }
    }
    
    public void setParam(String key, String value) {
        Map.Entry newEntry = new AbstractMap.SimpleEntry<>(key, value);
        this.params.add(newEntry);
    }
    
    public String getParam(String param) {
        for (int i = 0 ; i < this.params.size() ; i++) {
            if (this.params.get(i).getKey().equals(param)) {
                return this.params.get(i).getValue();
            }
        }
        return null;
    }
    
    public String getStringParams() {
        String key, value;
        String msg = "";
        
         for (int i = 0; i < this.params.size() ; i++) {
            Map.Entry<String, String> e = this.params.get(i);
            key = e.getKey();
            value = e.getValue();
            
            msg += key + "=" + value + ";";
        }
         
         return msg;
    }
    
    public String[] getParams(String name) {
        ArrayList<String> lis = new ArrayList<>();

        for (Map.Entry<String, String> entry : this.params) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();

            if (paramName.equals(name)) {
                lis.add(paramValue);
            }
        }

        Object[] objArray = lis.toArray();

        return Arrays.copyOf(objArray, objArray.length, String[].class);
    }
}
