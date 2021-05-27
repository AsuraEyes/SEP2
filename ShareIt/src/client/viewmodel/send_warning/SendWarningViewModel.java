package client.viewmodel.send_warning;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Warning;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SendWarningViewModel {
    private RentalModel rentalModel;
    private MemberModel memberModel;
    private MessageModel messageModel;

    private StringProperty username;
    private StringProperty warnings;
    private StringProperty inputTextChat;


    public SendWarningViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel){
        this.rentalModel = rentalModel;
        this.memberModel = memberModel;
        this.messageModel = messageModel;

        username = new SimpleStringProperty();
        warnings = new SimpleStringProperty();
        inputTextChat = new SimpleStringProperty();
        messageModel.addListener("newWarning", this::onNewWarning);
    }

    private void onNewWarning(PropertyChangeEvent evt) {
        Platform.runLater(() ->{
            warnings.setValue(warnings.getValue() + "\n" + evt.getNewValue().toString());
        });
    }

    public StringProperty getUsername(){
        return username;
    }
    public StringProperty getWarnings() {
        return warnings;
    }

    public StringProperty getInputTextChat()
    {
        return inputTextChat;
    }

    public void sendWarning(){
        String administratorFrom = "administrator";
        int idTo = memberModel.getMemberByUsername(memberModel.getMemberUsername()).getId();
        Date timeStamp = Calendar.getInstance().getTime();
        Warning warning = new Warning(administratorFrom, idTo, inputTextChat.getValue(), timeStamp);
        messageModel.sendWarning(warning);
    }
    public void loadLogs() {
        warnings.setValue("Warning for this guy");
        int idTo = memberModel.getMemberByUsername(memberModel.getMemberUsername()).getId();
        ArrayList<Warning> listOfWarnings = messageModel.getWarnings("administrator", idTo);
        if(listOfWarnings != null){
            for (int i = 0; i < listOfWarnings.size(); i++) {
                warnings.setValue(warnings.getValue() + "\n" + listOfWarnings.get(i).toString());
            }
        }
    }
    public void getMember(){
        username.setValue(memberModel.getMemberUsername());
    }
}
