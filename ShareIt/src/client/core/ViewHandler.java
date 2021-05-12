package client.core;

import client.views.chat_received_messages.ChatReceivedMessagesController;
import client.views.chat_write_message.ChatWriteMessageController;
import client.views.log_in.LogInController;
import client.views.main_view.MainController;
import client.views.menu.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private final Stage stage;
  private final Scene scene;
  private final ViewModelFactory viewModelFactory;

  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.stage = stage;
    scene = new Scene(new Region());
  }

  public void start() throws Exception
  {
    setView(menu(),createAccount());
  }

  public void setView(Node menu, Node content) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/main_view/Main.fxml"));
    loader.load();
    MainController main = loader.getController();
    main.getMainPane().getChildren().setAll(menu,content);
    scene.setRoot(main.getMainPane());
    stage.setScene(scene);
    stage.show();
  }

  public Node menu() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/menu/Menu.fxml"));
    Node menu = loader.load();
    MenuController menuController = loader.getController();
    menuController.init(this, viewModelFactory);

    return menu;
  }

  public Node addRental() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/add_rental/AddRental.fxml"));
    Node content = loader.load();
    //AddRentalController addRentalController = loader.getController();
    //addRentalController.init(viewModelFactory.getAddRentalViewModel());
    stage.setTitle("Add Rental");
    return content;
  }

  public Node chatReceived() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_received_messages/ChatRecievedMessages.fxml"));
    Node content = loader.load();
    ChatReceivedMessagesController chatReceivedMessagesController = loader.getController();
    chatReceivedMessagesController.init(this, viewModelFactory);
    return content;
  }

  public Node chatWrite() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_write_message/ChatWriteMessage.fxml"));
    Node content = loader.load();
    ChatWriteMessageController chatWriteMessageController = loader.getController();
    chatWriteMessageController.init();
    return content;
  }

  public Node createAccount() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/create_account/CreateAccount.fxml"));
    Node content = loader.load();
    //LogInController logInController = loader.getController();
    //ogInController.init(this, viewModelFactory);
    return content;
  }

  public Node logIn() throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/log_in/LogIn.fxml"));
    Node content = loader.load();
    LogInController logInController = loader.getController();
    logInController.init(this, viewModelFactory);
    return content;
  }

  public Node manageAccount()
  {
    return null;
  }
  public Node manageRentals()
  {
    return null;
  }
  public Node rateFeedback()
  {
    return null;
  }
  public Node reportMember()
  {
    return null;
  }
  public Node searchForMember()
  {
    return null;
  }
  public Node searchForRental()
  {
    return null;
  }
  public Node viewMemberProfile()
  {
    return null;
  }
  public Node viewRating()
  {
    return null;
  }
  public Node viewRatingFull()
  {
    return null;
  }
  public Node viewRental()
  {
    return null;
  }
  public Node viewReportedMember()
  {
    return null;
  }
  public Node viewReportedMemberList()
  {
    return null;
  }
  public Node welcomePage()
  {
    return null;
  }


}
