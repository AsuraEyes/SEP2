package client.viewmodel.welcome_page;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WelcomePageViewModel {
    private MemberModel memberModel;
    private MessageModel messageModel;

    private final StringProperty searchField;

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
