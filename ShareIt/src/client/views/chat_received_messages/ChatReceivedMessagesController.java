package client.views.chat_received_messages;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import shared.transferobjects.Message;
import shared.transferobjects.Rating;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatReceivedMessagesController
{
  @FXML
  private VBox vBox;

  private ViewHandler viewHandler;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws IOException
  {
    this.viewHandler = viewHandler;
    chatReceivedMessagesViewModel = viewModelFactory.getChatReceivedMessagesViewModel();
    displayMessages();
  }

  public void searchButton(ActionEvent actionEvent) throws IOException, SQLException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
  }
  public void displayMessages(){

    if (chatReceivedMessagesViewModel.getAllReceivedMessages() != null && !chatReceivedMessagesViewModel.getAllReceivedMessages().isEmpty())
    {
      for (int i = 0; i < chatReceivedMessagesViewModel.getAllReceivedMessages().size(); i++)
      {
        VBox messageBox = new VBox();
        Label usernameLabel = new Label(chatReceivedMessagesViewModel.getAllReceivedMessages().get(i).getUsernameFrom());
        usernameLabel.setFont(Font.font ("Californian FB", 24));
        usernameLabel.setTextFill(Color.WHITE);
        Text message = new Text("Received on: "  + chatReceivedMessagesViewModel.getAllReceivedMessages().get(i).getTimeStamp() + "\n" + chatReceivedMessagesViewModel.getAllReceivedMessages().get(i).getText());
        message.setFill(Color.WHITE);
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(message);
        messageBox.getChildren().addAll(usernameLabel,textFlow);
        messageBox.setSpacing(10);
        messageBox.setPadding(new Insets(20,160,20,160));
        message.setStyle("-fx-border-color: #FF8C64; -fx-border-width: 3; -fx-background-color: #FF8C64;");
        vBox.getChildren().add(messageBox);

        vBox.getChildren().get(i)
            .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
              try
              {
                chatReceivedMessagesViewModel.getUsername(event.getSource());
                viewHandler.setView(viewHandler.menu(), viewHandler.chatWrite());
              }
              catch (IOException e)
              {
                e.printStackTrace();
              }
            });
      }
    }
  }

}
