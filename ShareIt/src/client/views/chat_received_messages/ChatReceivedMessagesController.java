package client.views.chat_received_messages;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChatReceivedMessagesController
{
  @FXML
  private TextField searchField;

  private ViewHandler viewHandler;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    chatReceivedMessagesViewModel = viewModelFactory.getChatReceivedMessagesViewModel();
    //searchField.textProperty().bindBidirectional(chatReceivedMessagesViewModel.getSearchField);
  }

  public void searchButton(ActionEvent actionEvent) throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.searchForRental());
  }
}
