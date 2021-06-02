package client.viewmodel.welcome_page;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class that holds and manages data from the WelcomePage view.
 */
public class WelcomePageViewModel {
    private final MemberModel memberModel;
    private final MessageModel messageModel;

    private final StringProperty searchField;
    /**
     * Instantiates a new WelcomePageViewModel.
     *
     * @param model The model that this ViewModel uses
     */
    public WelcomePageViewModel(MemberModel memberModel, MessageModel messageModel){
        this.memberModel = memberModel;
        this.messageModel = messageModel;
        searchField = new SimpleStringProperty();
    }

    /**
     * Gets searchField.
     *
     * @return returns searchField input
     */
    public StringProperty getSearchField(){
        return searchField;
    }

    /**
     * Checks user type.
     *
     * @return returns which type of account is viewing profile
     */
    public String getUserType(){
        return memberModel.checkUserType();
    }

    /**
     * Sets search text.
     */
    public void setSearchText(){
        messageModel.setSearchText(searchField.getValue());
    }
}
