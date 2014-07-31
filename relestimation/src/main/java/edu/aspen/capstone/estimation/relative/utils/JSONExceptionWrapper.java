package edu.aspen.capstone.estimation.relative.utils;

//import org.hibernate.StaleStateException;

import org.hibernate.StaleStateException;


/**
 *
 * @author jaishankar
 */
public class JSONExceptionWrapper {

    private String msg;
    private String title;

    /**
     * Constructor
     * @param title title to be used for the clients
     * @param exec  Exception Object from which the error message is used
     */
    public JSONExceptionWrapper(String title, Exception exec) {
        exec.printStackTrace();
        this.title = title;
        this.msg = exec.getMessage();

        /*if (exec instanceof StaleStateException) {
            this.msg = "Row not found";
        }*/

    }
    
    public JSONExceptionWrapper(String title, String msg){
        this.title = title;
        this.msg = msg;
    }

    /**
     * Constructor
     * @param title title to be used for clients
     * @param exec Exception object from which the error message is used
     * @param extraMsg User defined error message, message from exec will be 
     *                 ignored
     */
    public JSONExceptionWrapper(String title, Exception exec, String extraMsg) {
        this.title = title;
        this.msg = exec.getMessage();
        
        //TODO remove later or find a better way to see the error
        exec.printStackTrace();
        
        if (exec instanceof StaleStateException) {
            this.msg = "Row not found";
        }

        if (null != extraMsg && extraMsg.isEmpty() == false) {
            this.msg = extraMsg;
        }
    }

    /**
     * getter for message
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * setter for message
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * getter for title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
