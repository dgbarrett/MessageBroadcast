package messagebroadcast.api;

public final class APIResponse extends APIMessage {
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    
    private String status;
    
    public APIResponse(int status) {
        super();
        
        if (status == SUCCESS || status == FAIL) {
            this.status = Integer.toString(status);
        } else {
            this.status = "-1";
        }
    }
    
    public APIResponse(String response) {
        super();
        
        String[] strParams = response.split(PARAM_SEPERATOR);
        String[] statusParam = strParams[0].split(PARAM_ASSIGNER);
        
        if(statusParam.length == 2) {
            String statusKey = statusParam[0];
            String statusValue = statusParam[1];
   
            if(statusKey.equals("STATUS")) {
                this.status = statusValue;
            }
        }
        
        for (int i = 1 ; i < strParams.length ; i++) {
            this.addParam(strParams[i]);
        }
    }
    
    @Override
    public String toString() {
        String msg = "STATUS=" + this.status + ";";
        msg += this.getStringParams();
        
        return msg;
    }  
}
