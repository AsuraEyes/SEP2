package client.viewmodel.chat_received_messages;

import client.model.ShareItModel;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Message;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatReceivedMessagesViewModel
{
  private ShareItModel model;
  public ChatReceivedMessagesViewModel(ShareItModel model){
    this.model = model;
  }

  public ArrayList<Message> getAllReceivedMessages(){

    return model.getAllReceivedMessages();
  }
  public void getUsername(Object object) throws RemoteException
  {
    if(object instanceof VBox){
       VBox vBox = (VBox) object;
      if(vBox.getChildren().get(0) instanceof Label)
      {
        Label label = (Label) vBox.getChildren().get(0);
        System.out.println(label.getText());
        model.setMemberUsername(label.getText());
      }
    }
  }
}
