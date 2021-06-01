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

/**
 * A class that holds and manages data from the SendWarning view.
 */
public class SendWarningViewModel {
    private StringProperty username;
    private StringProperty warnings;
    private StringProperty inputTextChat;
    private ShareItModel model;

    /**
     * Instantiates a new SendWarningViewModel.
     *
     * @param model The model that this ViewModel uses
     */
    public SendWarningViewModel(ShareItModel model){
        this.model = model;
        username = new SimpleStringProperty();
        warnings = new SimpleStringProperty();
        inputTextChat = new SimpleStringProperty();
        model.addListener("newWarning", this::onNewWarning);
    }

    /**
     * (?)
     * @param evt
     */
    private void onNewWarning(PropertyChangeEvent evt) {
        Platform.runLater(() ->{
            Warning warning = (Warning) evt.getNewValue();
            warnings.setValue(warnings.getValue() + "\n" + evt.getNewValue().toString());
        });
    }

    /**
     * Gets username.
     *
     * @return returns username
     */
    public StringProperty getUsername(){
        return username;
    }

    /**
     * Gets warnings.
     *
     * @return returns warnings
     */
    public StringProperty getWarnings() {
        return warnings;
    }

    /**
     * Gets textChat.
     *
     * @return returns the input of textChat
     */
    public StringProperty getInputTextChat()
    {
        return inputTextChat;
    }

    /**
     * Sends warning.
     */
    public void sendWarning(){
        String administratorFrom = "administrator";
        int idTo = model.getMemberByUsername(model.getMemberUsername()).getId();
        Date timeStamp = Calendar.getInstance().getTime();
        Warning warning = new Warning(administratorFrom, idTo, inputTextChat.getValue(), timeStamp);
        model.sendWarning(warning);
    }

    /**
     * Loads logs.
     */
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

    /**
     * Gets member's username.
     */
    public void getMember(){
        username.setValue(model.getMemberUsername());
    }
}
