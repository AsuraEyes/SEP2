package client.viewmodel.send_warning;

import client.model.ShareItModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    public SendWarningViewModel(ShareItModel model){
        this.model = model;
        username = new SimpleStringProperty();
        warnings = new SimpleStringProperty();
        inputTextChat = new SimpleStringProperty();
        model.addListener("newWarning", this::onNewWarning);
    }

    private void onNewWarning(PropertyChangeEvent evt) {
        Platform.runLater(() ->{
            Warning warning = (Warning) evt.getNewValue();
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
        int idTo = model.getMemberByUsername(model.getMemberUsername()).getId();
        Date timeStamp = Calendar.getInstance().getTime();
        Warning warning = new Warning(administratorFrom, idTo, inputTextChat.getValue(), timeStamp);
        model.sendWarning(warning);
    }
    public void loadLogs() {
        warnings.setValue("Warning for this guy");
        int idTo = model.getMemberByUsername(model.getMemberUsername()).getId();
        ArrayList<Warning> listOfWarnings = model.getWarnings("administrator", idTo);
        if(listOfWarnings != null){
            for (int i = 0; i < listOfWarnings.size(); i++) {
                warnings.setValue(warnings.getValue() + "\n" + listOfWarnings.get(i).toString());
            }
        }
    }
    public void getMember(){
        username.setValue(model.getMemberUsername());
    }
}
