package client.views.menu;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.state.VisitorState;
import client.viewmodel.menu.MenuViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * A class that manages an interface and handle interactions in Menu view
 */
public class MenuController
{
  @FXML private Button reportedMembersButton;
  @FXML private Button chatButton;
  @FXML private Label usernameLabel;
  @FXML private Button logInOutLabel;

  private ViewHandler viewHandler;
  private MenuViewModel menuViewModel;
  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    menuViewModel = viewModelFactory.getMenuViewModel();
    usernameLabel.textProperty().bind(menuViewModel.getUsernameLabel());

    String userType = menuViewModel.checkUserType();

    if (userType.equals("Visitor"))
    {
      reportedMembersButton.setVisible(false);
      chatButton.setVisible(false);

    }
    else if (userType.equals("Member"))
    {
      reportedMembersButton.setVisible(true);
      reportedMembersButton.setText("Manage account");
      chatButton.setVisible(true);
      logInOutLabel.setText("Log out");
    }
    else if (userType.equals("Administrator"))
    {
      reportedMembersButton.setVisible(true);
      chatButton.setVisible(false);
      logInOutLabel.setText("Log out");
    }
  }
  /**
   * Changes view when logo was pressed.
   */
  public void onLogoClick()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
  }
  /**
   * Log out member after button was pressed and data validated.
   */
  public void logInOutButton()
  {
    if (menuViewModel.checkUserType().equals("Administrator") || (menuViewModel
        .checkUserType().equals("Member")))
    {
      Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
      alert.initOwner(stage);
      alert.getDialogPane().setContentText("Are you sure you want to log out?");

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK)
      {
        menuViewModel.setUserType("visitor");
        viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
      }
    }
    else
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.logIn());
    }
  }
  /**
   * Changes view when button was pressed dependable on user type.
   */
  public void onReportedMembersButton()
  {
    if (menuViewModel.checkUserType().equals("Member"))
    {
      menuViewModel.setMemberRentals();
      viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
    }
    else
    {
      menuViewModel.loadAllReportedMembers();
      viewHandler
          .setView(viewHandler.menu(), viewHandler.viewReportedMemberList());
    }
  }

  /**
   * Changes view when button was pressed and sets member's messages and warnings.
   */
  public void onChatButton()
  {
    menuViewModel.loadAllReceivedMessages();
    menuViewModel.loadAllWarnings();
    viewHandler.setView(viewHandler.menu(), viewHandler.chatReceived());
  }
}
