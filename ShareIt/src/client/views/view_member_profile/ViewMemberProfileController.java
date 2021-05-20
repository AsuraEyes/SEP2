package client.views.view_member_profile;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ViewMemberProfileController
{
  @FXML private TextField searchField;
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;
  @FXML private Label addressLabel;
  @FXML private Label contactLabel;
  @FXML private Label otherInformationLabel;
  @FXML private Button deleteButton;
  @FXML private Button rateButton;
  @FXML private Button chatButton;
  @FXML private Button reportButton;

  public VBox rentalVBox;
  public Label nameOfRentalLabel;
  public Label cityLabel;
  public Label priceLabel;
  public ImageView ImageView;

  private ViewMemberProfileViewModel viewMemberProfileViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    this.viewHandler = viewHandler;
    viewMemberProfileViewModel = viewModelFactory.getViewMemberProfileViewModel();
    searchField.textProperty().bindBidirectional(viewMemberProfileViewModel.getSearchField());
    usernameLabel.textProperty().bind(viewMemberProfileViewModel.getUsernameLabel());
    locationLabel.textProperty().bind(viewMemberProfileViewModel.getLocationLabel());
    ratingLabel.textProperty().bind(viewMemberProfileViewModel.getRatingLabel());
    addressLabel.textProperty().bind(viewMemberProfileViewModel.getAddressLabel());
    contactLabel.textProperty().bind(viewMemberProfileViewModel.getContactLabel());
    otherInformationLabel.textProperty().bind(viewMemberProfileViewModel.getOtherInformationLabel());


    switch (viewMemberProfileViewModel.checkUserType()){
      case "Visitor":
        deleteButton.setVisible(false);
        chatButton.setVisible(false);
        reportButton.setVisible(false);
        rateButton.setVisible(false);
        break;
      case "Member":
        deleteButton.setVisible(false);
        break;
      case "Administrator":
        reportButton.setVisible(false);
        rateButton.setVisible(false);
        chatButton.setText("Warning");
        break;
    }
  }

  public void searchButton(ActionEvent actionEvent) {

  }

  public void reportButton(ActionEvent actionEvent) {
  }

  public void chatButton(ActionEvent actionEvent) {
  }

  public void rateButton(ActionEvent actionEvent) {
  }

  public void deleteButton(ActionEvent actionEvent) throws SQLException, IOException {
    Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.setTitle("Delete account");
    alert.setHeaderText("Are you sure?");
    alert.initOwner(stage);
    alert.getDialogPane().setContentText("Are you sure you want to permanent delete your account?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
      MemberDAOImpl.getInstance().delete(String.valueOf(viewMemberProfileViewModel.getUsernameLabel()));
    }
  }
  
  public void goBackToViewedRentalButton(ActionEvent actionEvent)
      throws IOException
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
  }

  public void loadMoreRentalButton(ActionEvent actionEvent)
  {
  }

  public void viewRatingButton(ActionEvent actionEvent)
  {
  }

  public void rentalVBoxClicked(MouseEvent mouseEvent)
  {
  }
}
