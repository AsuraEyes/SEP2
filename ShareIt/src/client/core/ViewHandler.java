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

  public void start() throws Exception {
    setView(menu(), welcomePage());
  }

  public void setView(Node menu, Node content) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/main_view/Main.fxml"));
    loader.load();
    MainController main = loader.getController();
    main.getMainPane().getChildren().setAll(menu,content);
    scene.setRoot(main.getMainPane());
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  public Node menu() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/menu/Menu.fxml"));
    Node menu = loader.load();
    MenuController menuController = loader.getController();
    menuController.init(this, viewModelFactory);
    return menu;
  }

  public Node addRental() throws IOException, SQLException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/add_rental/AddRental.fxml"));
    Node content = loader.load();
    AddRentalController addRentalController = loader.getController();
    addRentalController.init(this, viewModelFactory);
    stage.setTitle("Add Rental");
    return content;
  }

  public Node chatReceived() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_received_messages/ChatReceivedMessages.fxml"));
    Node content = loader.load();
    ChatReceivedMessagesController chatReceivedMessagesController = loader.getController();
    chatReceivedMessagesController.init(this, viewModelFactory);
    return content;
  }

  public Node chatWrite() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/chat_write_message/ChatWriteMessage.fxml"));
    Node content = loader.load();
    ChatWriteMessageController chatWriteMessageController = loader.getController();
    chatWriteMessageController.init(this, viewModelFactory);
    return content;
  }

  public Node sendWarning() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/send_warning/SendWarning.fxml"));
    Node content = loader.load();
    SendWarningController sendWarningController = loader.getController();
    sendWarningController.init(this, viewModelFactory);
    return content;
  }

  public Node createAccount() throws IOException, SQLException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/create_account/CreateAccount.fxml"));
    Node content = loader.load();
    CreateAccountController createAccountController = loader.getController();
    createAccountController.init(this, viewModelFactory);
    return content;
  }

  public Node logIn() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/log_in/LogIn.fxml"));
    Node content = loader.load();
    LogInController logInController = loader.getController();
    logInController.init(this, viewModelFactory);
    return content;
  }

  public Node manageAccount() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/manage_account/ManageAccount.fxml"));
    Node content = loader.load();
    ManageAccountController manageAccountController = loader.getController();
    manageAccountController.init(this, viewModelFactory);
    return content;
  }

  public Node editOrDeleteAccount() throws IOException, SQLException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/edit_account/EditAccount.fxml"));
    Node content = loader.load();
    EditAccountController editAccountController = loader.getController();
    editAccountController.init(this, viewModelFactory);
    return content;
  }

  public Node manageRentals() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/manage_rentals/ManageRentals.fxml"));
    Node content = loader.load();
    ManageRentalsController manageRentalsController = loader.getController();
    manageRentalsController.init(this, viewModelFactory);
    return content;
  }

  public Node editRental() throws IOException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/edit_rental/EditRental.fxml"));
    Node content = loader.load();
    EditRentalController editRentalController = loader.getController();
    editRentalController.init(this, viewModelFactory);
    return content;
  }

  public Node rateFeedback() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/rate_feedback/RateFeedback.fxml"));
    Node content = loader.load();
    RateFeedbackController rateFeedbackController = loader.getController();
    rateFeedbackController.init(this, viewModelFactory);
    return content;
  }

  public Node reportMember() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/report_member/ReportMember.fxml"));
    Node content = loader.load();
    ReportMemberController reportMemberController = loader.getController();
    reportMemberController.init(this, viewModelFactory);
    return content;
  }

  public Node searchForMember() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/search_for_member/SearchForMember.fxml"));
    Node content = loader.load();
    SearchForMemberController searchForMemberController = loader.getController();
    searchForMemberController.init(this, viewModelFactory);
    return content;
  }

  public Node searchForRental() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/search_for_rental/SearchForRental.fxml"));
    Node content = loader.load();
    SearchForRentalController searchForRentalController = loader.getController();
    searchForRentalController.init(this, viewModelFactory);
    return content;
  }

  public Node viewMemberProfile() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_member_profile/ViewMemberProfile.fxml"));
    Node content = loader.load();
    ViewMemberProfileController viewMemberProfileController = loader.getController();
    viewMemberProfileController.init(this,viewModelFactory);
    return content;
  }

  public Node viewRating() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rating/ViewRating.fxml"));
    Node content = loader.load();
    ViewRatingController viewRatingController = loader.getController();
    viewRatingController.init(this, viewModelFactory);
    return content;
  }

  public Node viewRatingFull() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rating/ViewRating.fxml"));
    Node content = loader.load();
    ViewRatingController viewRatingController = loader.getController();
    viewRatingController.init(this, viewModelFactory);
    return content;
  }

  public Node viewRental() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_rental/ViewRental.fxml"));
    Node content = loader.load();
    ViewRentalController viewRentalController = loader.getController();
    viewRentalController.init(this,viewModelFactory);
    return content;
  }

  public Node viewReportedMember() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_reported_member/ViewReportedMember.fxml"));
    Node content = loader.load();
    ViewReportedMemberController viewReportedMemberController = loader.getController();
    viewReportedMemberController.init(this, viewModelFactory);
    return content;
  }

  public Node viewReportedMemberList() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/view_reported_member_list/ViewReportedMemberList.fxml"));
    Node content = loader.load();
    ViewReportedMemberListController viewReportedMemberListController = loader.getController();
    viewReportedMemberListController.init(this, viewModelFactory);
    return content;
  }

  public Node welcomePage() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/welcome_page/WelcomePage.fxml"));
    Node content = loader.load();
    WelcomePageController welcomePageController = loader.getController();
    welcomePageController.init(this, viewModelFactory);
    return content;
  }

  public Stage getStage()
  {
    return stage;
  }
}
