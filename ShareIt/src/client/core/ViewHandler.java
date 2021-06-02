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
/**
 * A class that helps to manage all views that ShareIt Application has
 */
public class ViewHandler
{
  private final Stage stage;
  private final Scene scene;
  private final ViewModelFactory viewModelFactory;
  /**
   * Instantiates a new View handler.
   *
   * @param stage            the stage
   * @param viewModelFactory the view model factory
   */
  public ViewHandler(Stage stage, ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.stage = stage;
    scene = new Scene(new Region());
  }
  /**
   * Sets default view at the start of an Application.
   */
  public void start()
  {
    setView(menu(), welcomePage());
  }
  /**
   * Sets view with particular chosen content.
   *
   * @param menu    Menu bar placed at the top part of an Application
   * @param content The content below Menu bar
   */
  public void setView(Node menu, Node content)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/main_view/Main.fxml"));
    loaderDotLoad(loader);
    MainController main = loader.getController();
    main.getMainPane().getChildren().setAll(menu, content);
    scene.setRoot(main.getMainPane());
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }
  /**
   * Loads Menu view.
   *
   *  @return a Node for this view
   */
  public Node menu()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/client/views/menu/Menu.fxml"));
    Node menu = loaderDotLoad(loader);
    MenuController menuController = loader.getController();
    menuController.init(this, viewModelFactory);
    return menu;
  }
  /**
   * loads AddRental view.
   *
   *  @return a Node for this view
   */
  public Node addRental()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/add_rental/AddRental.fxml"));
    Node content = loaderDotLoad(loader);
    AddRentalController addRentalController = loader.getController();
    addRentalController.init(this, viewModelFactory);
    stage.setTitle("Add Rental");
    return content;
  }
  /**
   * Loads ChatReceivedMessages view.
   *
   *  @return a Node for this view
   */
  public Node chatReceived()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/client/views/chat_received_messages/ChatReceivedMessages.fxml"));
    Node content = loaderDotLoad(loader);
    ChatReceivedMessagesController chatReceivedMessagesController = loader
        .getController();
    chatReceivedMessagesController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ChatWriteMessage view.
   *
   * @return    this view
   */
  public Node chatWrite()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/chat_write_message/ChatWriteMessage.fxml"));
    Node content = loaderDotLoad(loader);
    ChatWriteMessageController chatWriteMessageController = loader
        .getController();
    chatWriteMessageController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Load SendWarning view
   *
   *  @return a Node for this view
   */
  public Node sendWarning()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/send_warning/SendWarning.fxml"));
    Node content = loaderDotLoad(loader);
    SendWarningController sendWarningController = loader.getController();
    sendWarningController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads CreateAccount view.
   *
   *  @return a Node for this view
   */
  public Node createAccount()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/create_account/CreateAccount.fxml"));
    Node content = loaderDotLoad(loader);
    CreateAccountController createAccountController = loader.getController();
    createAccountController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads LogIn view.
   *
   *  @return a Node for this view
   */
  public Node logIn()
  {
    FXMLLoader loader = new FXMLLoader();
    loader
        .setLocation(getClass().getResource("/client/views/log_in/LogIn.fxml"));
    Node content = loaderDotLoad(loader);
    LogInController logInController = loader.getController();
    logInController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ManageAccount view.
   *
   *  @return a Node for this view
   */
  public Node manageAccount()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/manage_account/ManageAccount.fxml"));
    Node content = loaderDotLoad(loader);
    ManageAccountController manageAccountController = loader.getController();
    manageAccountController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Load EditAccount view.
   *
   *  @return a Node for this view
   */
  public Node editOrDeleteAccount()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/edit_account/EditAccount.fxml"));
    Node content = loaderDotLoad(loader);
    EditAccountController editAccountController = loader.getController();
    editAccountController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ManageRentals view.
   *
   *  @return a Node for this view
   */
  public Node manageRentals()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/manage_rentals/ManageRentals.fxml"));
    Node content = loaderDotLoad(loader);
    ManageRentalsController manageRentalsController = loader.getController();
    manageRentalsController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads EditRental view.
   *
   *  @return a Node for this view
   */
  public Node editRental()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/edit_rental/EditRental.fxml"));
    Node content = loaderDotLoad(loader);
    EditRentalController editRentalController = loader.getController();
    editRentalController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads RateFeedback view
   *
   *  @return a Node for this view
   */
  public Node rateFeedback()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/rate_feedback/RateFeedback.fxml"));
    Node content = loaderDotLoad(loader);
    RateFeedbackController rateFeedbackController = loader.getController();
    rateFeedbackController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ReportMember view.
   *
   *  @return a Node for this view
   */
  public Node reportMember()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/report_member/ReportMember.fxml"));
    Node content = loaderDotLoad(loader);
    ReportMemberController reportMemberController = loader.getController();
    reportMemberController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads SearchForMember view.
   *
   *  @return a Node for this view
   */
  public Node searchForMember()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/search_for_member/SearchForMember.fxml"));
    Node content = loaderDotLoad(loader);
    SearchForMemberController searchForMemberController = loader
        .getController();
    searchForMemberController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads SearchForRental view.
   *
   *  @return a Node for this view
   */
  public Node searchForRental()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass()
        .getResource("/client/views/search_for_rental/SearchForRental.fxml"));
    Node content = loaderDotLoad(loader);
    SearchForRentalController searchForRentalController = loader
        .getController();
    searchForRentalController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ViewMemberProfile view.
   *
   *  @return a Node for this view
   */
  public Node viewMemberProfile()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/client/views/view_member_profile/ViewMemberProfile.fxml"));
    Node content = loaderDotLoad(loader);
    ViewMemberProfileController viewMemberProfileController = loader
        .getController();
    viewMemberProfileController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ViewRating view.
   *
   *  @return a Node for this view
   */
  public Node viewRating()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/view_rating/ViewRating.fxml"));
    Node content = loaderDotLoad(loader);
    ViewRatingController viewRatingController = loader.getController();
    viewRatingController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ViewRental view.
   *
   *  @return a Node for this view
   */
  public Node viewRental()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/view_rental/ViewRental.fxml"));
    Node content = loaderDotLoad(loader);
    ViewRentalController viewRentalController = loader.getController();
    viewRentalController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ViewReportedMember view.
   *
   *  @return a Node for this view
   */
  public Node viewReportedMember()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/client/views/view_reported_member/ViewReportedMember.fxml"));
    Node content = loaderDotLoad(loader);
    ViewReportedMemberController viewReportedMemberController = loader
        .getController();
    viewReportedMemberController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads ViewReportedMemberList view.
   *
   *  @return a Node for this view
   */
  public Node viewReportedMemberList()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(
        "/client/views/view_reported_member_list/ViewReportedMemberList.fxml"));
    Node content = loaderDotLoad(loader);
    ViewReportedMemberListController viewReportedMemberListController = loader
        .getController();
    viewReportedMemberListController.init(this, viewModelFactory);
    return content;
  }
  /**
   * Loads WelcomePage view.
   *
   *  @return a Node for this view
   */
  public Node welcomePage()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(
        getClass().getResource("/client/views/welcome_page/WelcomePage.fxml"));
    Node content = loaderDotLoad(loader);
    WelcomePageController welcomePageController = loader.getController();
    welcomePageController.init(this, viewModelFactory);
    return content;
  }

  /**
   * Gets stage container.
   *
   * @return the stage
   */
  public Stage getStage()
  {
    return stage;
  }

  private Node loaderDotLoad(FXMLLoader loader)
  {
    try
    {
      return loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }

}
