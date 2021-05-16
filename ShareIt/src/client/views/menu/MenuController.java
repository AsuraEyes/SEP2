package client.views.menu;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.menu.MenuViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MenuController
{
  @FXML
  private Button reviewsButton;
  @FXML
  private Button reportedMembersButton;
  @FXML
  private Button chatButton;
  @FXML
  private Label logoLabel;
  @FXML
  private Label usernameLabel;
  @FXML
  private Button logInOutLabel;
  @FXML
  private ViewHandler viewHandler;
  private MenuViewModel menuViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory){
    this.viewHandler = viewHandler;
    menuViewModel = viewModelFactory.getMenuViewModel();
    usernameLabel.textProperty().bind(menuViewModel.getUsernameLabel());

    if(menuViewModel.checkUserType().equals("Visitor"))
    {
      reviewsButton.setVisible(false);
      reportedMembersButton.setVisible(false);
      chatButton.setVisible(false);
    }
    else if(menuViewModel.checkUserType().equals("Member"))
    {
      reviewsButton.setVisible(false);
      reportedMembersButton.setVisible(false);
      chatButton.setVisible(true);
      logInOutLabel.setText("Log out");
    }
    else if(menuViewModel.checkUserType().equals("Administrator"))
    {
      reviewsButton.setVisible(true);
      reportedMembersButton.setVisible(true);
      chatButton.setVisible(true);
      logInOutLabel.setText("Log out");
    }
  }

  public void onLogoClick(MouseEvent mouseEvent) throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
  }

  public void logInOutButton(ActionEvent actionEvent) throws IOException
  {
    if(menuViewModel.checkUserType().equals("Administrator")||(menuViewModel.checkUserType().equals("Member")))
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
    }
    else
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.logIn());
    }
  }


  public void onReviewsButton(ActionEvent actionEvent) throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewRatingFull());
  }

  public void onReportedMembersButton(ActionEvent actionEvent)
      throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewReportedMemberList());
  }

  public void onChatButton(ActionEvent actionEvent) throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.chatReceived());
  }
}
