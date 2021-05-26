package client.views.chat_received_messages;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class ChatReceivedMessagesController
{
  @FXML
  private VBox vBoxRight;
  @FXML private VBox vBoxLeft;

  private ViewHandler viewHandler;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    this.viewHandler = viewHandler;
    chatReceivedMessagesViewModel = viewModelFactory.getChatReceivedMessagesViewModel();
    displayMessages();
    displayWarnings();
  }

  public void displayMessages(){
    if (chatReceivedMessagesViewModel.getAllReceivedMessages() != null && !chatReceivedMessagesViewModel.getAllReceivedMessages().isEmpty()) {
      for (int i = 0; i < chatReceivedMessagesViewModel.getAllReceivedMessages().size(); i++) {
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
        vBoxRight.getChildren().add(messageBox);

        vBoxRight.getChildren().get(i)
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

  public void displayWarnings(){
    if (chatReceivedMessagesViewModel.getAllReceivedWarnings() != null && !chatReceivedMessagesViewModel.getAllReceivedWarnings().isEmpty()) {
      for (int i = 0; i < chatReceivedMessagesViewModel.getAllReceivedWarnings().size(); i++) {
        VBox messageBox = new VBox();
        Label usernameLabel = new Label(chatReceivedMessagesViewModel.getAllReceivedWarnings().get(i).getAdministratorFrom());
        usernameLabel.setFont(Font.font ("Californian FB", 24));
        usernameLabel.setTextFill(Color.WHITE);
        Text warning = new Text("Received on: "  + chatReceivedMessagesViewModel.getAllReceivedWarnings().get(i).getTimeStamp() + "\n" + chatReceivedMessagesViewModel.getAllReceivedWarnings().get(i).getText());
        warning.setFill(Color.WHITE);
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(warning);
        messageBox.getChildren().addAll(usernameLabel,textFlow);
        messageBox.setSpacing(10);
        messageBox.setPadding(new Insets(20,160,20,160));
        warning.setStyle("-fx-border-color: #FF8C64; -fx-border-width: 3; -fx-background-color: #FF8C64;");
        vBoxLeft.getChildren().add(messageBox);

        vBoxLeft.getChildren().get(i)
                .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                  try
                  {
                    chatReceivedMessagesViewModel.getUsername(event.getSource());
                    viewHandler.setView(viewHandler.menu(), viewHandler.sendWarning());
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
