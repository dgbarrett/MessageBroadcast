package messagebroadcast.api;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
    Base type for request/responses send to/from the MessageBroadcastAPI
*/
public class APIMessage {
    public static final String PARAM_ASSIGNER = "=";    
    public static final String PARAM_SEPERATOR = ";";
    
    // API message paramaters. (ex MESSAGE=Yay, a message!)
    public List<Map.Entry<String,String>> params;
    
    APIMessage() {
        this.params = new ArrayList<>();
    }
    
    /* 
        Add a parameter (names are not unique). 
        Expects strings in complete parameter form (ie [PARAM_NAME]=[PARAM_VALUE])
    */
    public void addParam(String fullParam) {
        String[] paramParts = fullParam.split(PARAM_ASSIGNER);
        if (paramParts.length == 2) {
            addParam(paramParts[0], paramParts[1]);
        }
    }
    
    /* 
        Add a parameter to the list of parameters after it has been seperated in
        to key and value
    */
    public void addParam(String key, String value) {
        Map.Entry newEntry = new AbstractMap.SimpleEntry<>(key, value);
        this.params.add(newEntry);
    }
    
    // Return the first parameter in the list with PARAM_NAME = param
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
    
    // Get all parameter values with PARAM_NAME = name
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
