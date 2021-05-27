package client.viewmodel.welcome_page;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WelcomePageViewModel {
    private RentalModel rentalModel;
    private MemberModel memberModel;
    private MessageModel messageModel;

    private final StringProperty searchField;

    public WelcomePageViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
        this.rentalModel = rentalModel;
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
