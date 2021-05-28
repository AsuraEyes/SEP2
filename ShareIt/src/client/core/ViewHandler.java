package client.core;

import client.views.add_rental.AddRentalController;
import client.views.chat_received_messages.ChatReceivedMessagesController;
import client.views.chat_write_message.ChatWriteMessageController;
import client.views.create_account.CreateAccountController;
import client.views.edit_account.EditAccountController;
import client.views.edit_rental.EditRentalController;
import client.views.log_in.LogInController;
import client.views.main_view.MainController;
import client.views.manage_account.ManageAccountController;
import client.views.manage_rentals.ManageRentalsController;
import client.views.menu.MenuController;
import client.views.rate_feedback.RateFeedbackController;
import client.views.report_member.ReportMemberController;
import client.views.search_for_member.SearchForMemberController;
import client.views.search_for_rental.SearchForRentalController;
import client.views.send_warning.SendWarningController;
import client.views.view_member_profile.ViewMemberProfileController;
import client.views.view_rating.ViewRatingController;
import client.views.view_rental.ViewRentalController;
import client.views.view_reported_member.ViewReportedMemberController;
import client.views.view_reported_member_list.ViewReportedMemberListController;
import client.views.welcome_page.WelcomePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewHandler {
  private final Stage stage;
  private final Scene scene;
  private final ViewModelFactory viewModelFactory;

    public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
    this.viewModelFactory = viewModelFactory;
    this.stage = stage;
    scene = new Scene(new Region());
  }

  public void start(){
    setView(menu(), welcomePage());
  }

  public void setView(Node menu, Node content){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/main_view/Main.fxml"));
    loadDotLoad(loader);
    MainController main = loader.getController();
    main.getMainPane().getChildren().setAll(menu,content);
    scene.setRoot(main.getMainPane());
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  public Node menu(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/menu/Menu.fxml"));
    Node menu = loadDotLoad(loader);
    MenuController menuController = loader.getController();
    menuController.init(this, viewModelFactory);
    return menu;
  }

  public Node addRental(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/add_rental/AddRental.fxml"));
    Node content = loadDotLoad(loader);
    AddRentalController addRentalController = loader.getController();
    addRentalController.init(this, viewModelFactory);
    stage.setTitle("Add Rental");
    return content;
  }

  public Node chatReceived(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_received_messages/ChatReceivedMessages.fxml"));
    Node content = loadDotLoad(loader);
    ChatReceivedMessagesController chatReceivedMessagesController = loader.getController();
    chatReceivedMessagesController.init(this, viewModelFactory);
    return content;
  }

  public Node chatWrite(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_write_message/ChatWriteMessage.fxml"));
    Node content = loadDotLoad(loader);
    ChatWriteMessageController chatWriteMessageController = loader.getController();
    chatWriteMessageController.init(this, viewModelFactory);
    return content;
  }

  public Node sendWarning(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/send_warning/SendWarning.fxml"));
    Node content = loadDotLoad(loader);
    SendWarningController sendWarningController = loader.getController();
    sendWarningController.init(this, viewModelFactory);
    return content;
  }

  public Node createAccount(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/create_account/CreateAccount.fxml"));
    Node content = loadDotLoad(loader);
    CreateAccountController createAccountController = loader.getController();
    createAccountController.init(this, viewModelFactory);
    return content;
  }

  public Node logIn(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/log_in/LogIn.fxml"));
    Node content = loadDotLoad(loader);
    LogInController logInController = loader.getController();
    logInController.init(this, viewModelFactory);
    return content;
  }

  public Node manageAccount(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/manage_account/ManageAccount.fxml"));
    Node content = loadDotLoad(loader);
    ManageAccountController manageAccountController = loader.getController();
    manageAccountController.init(this, viewModelFactory);
    return content;
  }

  public Node editOrDeleteAccount(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/edit_account/EditAccount.fxml"));
    Node content = loadDotLoad(loader);
    EditAccountController editAccountController = loader.getController();
    editAccountController.init(this, viewModelFactory);
    return content;
  }

  public Node manageRentals(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/manage_rentals/ManageRentals.fxml"));
    Node content = loadDotLoad(loader);
    ManageRentalsController manageRentalsController = loader.getController();
    manageRentalsController.init(this, viewModelFactory);
    return content;
  }

  public Node editRental(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/edit_rental/EditRental.fxml"));
    Node content = loadDotLoad(loader);
    EditRentalController editRentalController = loader.getController();
    editRentalController.init(this, viewModelFactory);
    return content;
  }

  public Node rateFeedback(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/rate_feedback/RateFeedback.fxml"));
    Node content = loadDotLoad(loader);
    RateFeedbackController rateFeedbackController = loader.getController();
    rateFeedbackController.init(this, viewModelFactory);
    return content;
  }

  public Node reportMember(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/report_member/ReportMember.fxml"));
    Node content = loadDotLoad(loader);
    ReportMemberController reportMemberController = loader.getController();
    reportMemberController.init(this, viewModelFactory);
    return content;
  }

  public Node searchForMember(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/search_for_member/SearchForMember.fxml"));
    Node content = loadDotLoad(loader);
    SearchForMemberController searchForMemberController = loader.getController();
    searchForMemberController.init(this, viewModelFactory);
    return content;
  }

  public Node searchForRental(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/search_for_rental/SearchForRental.fxml"));
    Node content = loadDotLoad(loader);
    SearchForRentalController searchForRentalController = loader.getController();
    searchForRentalController.init(this, viewModelFactory);
    return content;
  }

  public Node viewMemberProfile(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_member_profile/ViewMemberProfile.fxml"));
    Node content = loadDotLoad(loader);
    ViewMemberProfileController viewMemberProfileController = loader.getController();
    viewMemberProfileController.init(this,viewModelFactory);
    return content;
  }

  public Node viewRating(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rating/ViewRating.fxml"));
    Node content = loadDotLoad(loader);
    ViewRatingController viewRatingController = loader.getController();
    viewRatingController.init(this, viewModelFactory);
    return content;
  }

  public Node viewRental(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rental/ViewRental.fxml"));
    Node content = loadDotLoad(loader);
    ViewRentalController viewRentalController = loader.getController();
    viewRentalController.init(this,viewModelFactory);
    return content;
  }

  public Node viewReportedMember(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_reported_member/ViewReportedMember.fxml"));
    Node content = loadDotLoad(loader);
    ViewReportedMemberController viewReportedMemberController = loader.getController();
    viewReportedMemberController.init(this, viewModelFactory);
    return content;
  }

  public Node viewReportedMemberList(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_reported_member_list/ViewReportedMemberList.fxml"));
    Node content = loadDotLoad(loader);
    ViewReportedMemberListController viewReportedMemberListController = loader.getController();
    viewReportedMemberListController.init(this, viewModelFactory);
    return content;
  }

  public Node welcomePage(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/welcome_page/WelcomePage.fxml"));
    Node content = loadDotLoad(loader);
    WelcomePageController welcomePageController = loader.getController();
    welcomePageController.init(this, viewModelFactory);
    return content;
  }

  public Stage getStage()
  {
    return stage;
  }
  private Node loadDotLoad(FXMLLoader loader)
  {
    try
    {
      return loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return  null;
  }

}
