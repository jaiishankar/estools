package edu.aspen.capstone.estimation.relative.utils;

/**
 *
 * @author jaishankar
 */
public class JSONResponseWrapper {
    
    private boolean success;
    private Object results;
    private static JSONResponseWrapper instance;
    
    public static JSONResponseWrapper getInstance() {
        return instance;
    }
    
    public static void setInstance(JSONResponseWrapper instance) {
        JSONResponseWrapper.instance = instance;
    }
    
    public Object getResults() {
        return results;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    private JSONResponseWrapper(Object result) {
        if (result != null) {
            this.success = true;
        } else {
            this.success = false;
        }
        this.results = result;
    }
    
    private JSONResponseWrapper() {
        this.success = true;
    }
    
    public void setResults(Object result) {
        if (result != null) {
            this.success = true;
        } else {
            this.success = false;
        }
        this.results = result;
    }
    
    public static JSONResponseWrapper getResponseInstance(Object result) {
        if (instance == null) {
            instance = new JSONResponseWrapper(result);
        } else {
            instance.setResults(result);
        }
        
        return instance;
    }
    
    public static JSONResponseWrapper getErrorResponseInstance(JSONExceptionWrapper error) {
        if (instance == null) {
            instance = new JSONResponseWrapper(error);
        } else {
            instance.setResults(error);
        }
        instance.setSuccess(false);
        
        return instance;
    }
    
    public static JSONResponseWrapper getDefaultSuccessResponseInstance() {
        if (instance == null) {
            instance = new JSONResponseWrapper();
        } else {
            instance.setResults(null);
            instance.setSuccess(true);
        }
        
        return instance;
    }
    
    public static JSONResponseWrapper getDefaultSuccessResponseInstance(String message) {
        if (instance == null) {
            instance = new JSONResponseWrapper();
        } else {
            instance.setResults(message);
            instance.setSuccess(true);
        }
        
        return instance;
    }
    
    public static JSONResponseWrapper getDefaultFailResponseInstance() {
        if (instance == null) {
            instance = new JSONResponseWrapper();
            
        } else {
            instance.setResults(null);
        }
        instance.setSuccess(false);
        
        return instance;
    }
}
