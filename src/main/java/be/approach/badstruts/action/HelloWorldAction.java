package be.approach.badstruts.action;

import be.approach.badstruts.model.MessageStore;

public class HelloWorldAction {

    private static final long serialVersionUID = 1L;

    /**
     * The model class that stores the message
     * to display in the view.
     */
    private MessageStore messageStore;

    /*
     * Creates the MessageStore model object and 
     * returns success.  The MessageStore model
     * object will be available to the view.
     * (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute() {
        messageStore = new MessageStore() ;
        
        return "success";
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }

}
