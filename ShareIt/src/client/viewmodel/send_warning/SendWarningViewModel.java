package client.viewmodel.send_warning;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
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
public class SendWarningViewModel
{
  private MemberModel memberModel;
  private MessageModel messageModel;

  private StringProperty username;
  private StringProperty warnings;
  private StringProperty inputTextChat;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param memberModel The model that this ViewModel uses
   * @param messageModel The model that this ViewModel uses
   */
  public SendWarningViewModel(MemberModel memberModel,
      MessageModel messageModel)
  {
    this.memberModel = memberModel;
    this.messageModel = messageModel;

    username = new SimpleStringProperty();
    warnings = new SimpleStringProperty();
    inputTextChat = new SimpleStringProperty();
    messageModel.addListener("newWarning", this::onNewWarning);
  }
    /**
     * Updates administrator's text area with the new warning
     * @param evt
     */
  private void onNewWarning(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      warnings
          .setValue(warnings.getValue() + "\n" + evt.getNewValue().toString());
    });
  }

  public StringProperty getUsername()
  {
    return username;
  }

  public StringProperty getWarnings()
  {
    return warnings;
  }

  public StringProperty getInputTextChat()
  {
    return inputTextChat;
  }

  public void sendWarning()
  {
    String administratorFrom = "administrator";
    int idTo = memberModel.getMemberByUsername(memberModel.getMemberUsername())
        .getId();
    Date timeStamp = Calendar.getInstance().getTime();
    Warning warning = new Warning(administratorFrom, idTo,
        inputTextChat.getValue(), timeStamp);
    messageModel.sendWarning(warning);
  }
    /**
     * Loads initial list of warnings towards a specific member.
     */
  public void loadLogs()
  {
    warnings.setValue("");
    int idTo = memberModel.getMemberByUsername(memberModel.getMemberUsername())
        .getId();
    ArrayList<Warning> listOfWarnings = messageModel
        .getWarnings("administrator", idTo);
    if (listOfWarnings != null)
    {
      for (int i = 0; i < listOfWarnings.size(); i++)
      {
        warnings.setValue(
            warnings.getValue() + "\n" + listOfWarnings.get(i).toString());
      }
    }
  }

  public void getMember()
  {
    username.setValue(memberModel.getMemberUsername());
  }
}
