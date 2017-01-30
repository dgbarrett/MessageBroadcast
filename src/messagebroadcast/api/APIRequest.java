/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebroadcast.api;

/**
 *
 * @author damon
 */
public class APIRequest extends APIMessage{
    
    private String requestType;

    public APIRequest(String inputLine) {
        super();
        this.requestType = "";
        
        String[] strParams = inputLine.split(PARAM_SEPERATOR);
        String[] msgtypeParam = strParams[0].split(PARAM_ASSIGNER);
        
        if (msgtypeParam.length == 2) {
            String msgtypeKey = msgtypeParam[0]; 
            String msgtypeValue = msgtypeParam[1];

            
            if (msgtypeKey.equals("MSGTYPE")) {
                this.requestType = msgtypeValue;
            }
        }
      
        for (int i = 1 ; i < strParams.length ; i++) {
            this.setParam(strParams[i]);
        }
    }
    
    public APIRequest(String requestType, boolean flag) {
        this.requestType = requestType;
    }
    
    
    public String getRequestType() {
        return this.requestType;
    }
    
    @Override
    public String toString() {
        String msg = "MSGTYPE=" + this.requestType + ";";
        msg += this.getStringParams();
        
        return msg;
    } 
    
}
