package client.viewmodel.send_warning;

import client.model.ShareItModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import shared.transferobjects.Warning;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SendWarningViewModel {
    private StringProperty username;
    private StringProperty warnings;
    private StringProperty inputTextChat;
    private ShareItModel model;
    private ObservableList<Warning> listOfWarnings;

    public SendWarningViewModel(ShareItModel model){
        this.model = model;
        username = new SimpleStringProperty();
        warnings = new SimpleStringProperty();
        inputTextChat = new SimpleStringProperty();
    }

    public StringProperty getUsername(){
        return username;
    }
//    public void getMember(){
//        username.setValue(model.getMemberUsername());
//        System.out.println("Chat write View model" + username.getValue());
//    }
    public StringProperty getMessages()
    {
        return warnings;
    }
    public void loadLogs() {
//        ArrayList<Warning> listOfWarnings = model.getMessagesFromUser(model.getMemberByUsername(model.getLoggedInUsername()).getId(), model.getMemberByUsername(
//                model.getMemberUsername()).getId());
//        for (int i = 0; i < listOfWarnings.size(); i++)
//        {
//            warnings.setValue(warnings.getValue() + "\n" + listOfWarnings.get(i).toString());
//        }
    }
    public StringProperty getInputTextChat()
    {
        return inputTextChat;
    }
    public ObservableList<Warning> getListOfWarnings() {
        return listOfWarnings;
    }
    public void sendWarning(){
        String administratorFrom = "administrator";
        int idTo = model.getMemberByUsername(model.getMemberUsername()).getId();
        Date timeStamp = Calendar.getInstance().getTime();
        Warning warning = new Warning(administratorFrom, idTo, inputTextChat.getValue(), timeStamp);
        model.sendWarning(warning);
    }
}
