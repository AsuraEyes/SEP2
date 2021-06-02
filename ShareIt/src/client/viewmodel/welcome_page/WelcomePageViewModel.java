package client.viewmodel.welcome_page;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that holds and manages data from the WelcomePage view.
 */
public class WelcomePageViewModel {
    private MemberModel memberModel;
    private MessageModel messageModel;

    private StringProperty searchField;
    /**
     * Instantiates StringProperties used for binding with the fields in the controller
     *
     * @param memberModel The model that this ViewModel uses
     * @param messageModel The model that this ViewModel uses
     */
    public WelcomePageViewModel(MemberModel memberModel, MessageModel messageModel){
        this.memberModel = memberModel;
        this.messageModel = messageModel;
        searchField = new SimpleStringProperty();
    }

    public StringProperty getSearchField(){
        return searchField;
    }

    public String getUserType(){
        return memberModel.checkUserType();
    }

    public void setSearchText(){
        messageModel.setSearchText(searchField.getValue());
    }
}
