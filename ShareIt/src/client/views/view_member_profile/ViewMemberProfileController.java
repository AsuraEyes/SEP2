package client.views.view_member_profile;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Rental;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * A class that manages an interface and handle interactions in ViewMemberProfile view
 */
public class ViewMemberProfileController
{
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
  @FXML private Button goBackToViewedRentalButton;
  @FXML private FlowPane flowPane;


  public ImageView ImageView;

  private ViewMemberProfileViewModel viewMemberProfileViewModel;
  private ViewHandler viewHandler;

  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   * @throws IOException the io exception
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws IOException
  {
    this.viewHandler = viewHandler;
    viewMemberProfileViewModel = viewModelFactory.getViewMemberProfileViewModel();
    usernameLabel.textProperty().bind(viewMemberProfileViewModel.getUsernameLabel());
    locationLabel.textProperty().bind(viewMemberProfileViewModel.getLocationLabel());
    ratingLabel.textProperty().bind(viewMemberProfileViewModel.getRatingLabel());
    addressLabel.textProperty().bind(viewMemberProfileViewModel.getAddressLabel());
    contactLabel.textProperty().bind(viewMemberProfileViewModel.getContactLabel());
    otherInformationLabel.textProperty().bind(viewMemberProfileViewModel.getOtherInformationLabel());

    displayRentals(viewMemberProfileViewModel.getRentalsOfMemberList(
        viewMemberProfileViewModel.getMemberUsername()));

    switch (viewMemberProfileViewModel.checkUserType())
    {
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
        chatButton.setText("Warn");
        goBackToViewedRentalButton.setText("Go back to search results");
        break;
    }
  }

  /**
   * Changes view when button was pressed and sets data for selected Member.
   *
   * @throws IOException
   */
  public void reportButton() throws IOException
  {
    viewMemberProfileViewModel.setMemberUsername();
    viewHandler.setView(viewHandler.menu(), viewHandler.reportMember());
  }

  /**
   * Changes view when button was pressed dependable on user type.
   *
   * @throws IOException
   */
  public void chatButton() throws IOException {
    if (viewMemberProfileViewModel.checkUserType().equals("Administrator")){
      viewHandler.setView(viewHandler.menu(), viewHandler.sendWarning());
      viewMemberProfileViewModel.setMemberUsername();
    }
    else {
      viewHandler.setView(viewHandler.menu(), viewHandler.chatWrite());
      viewMemberProfileViewModel.setMemberUsername();
    }
  }

  /**
   * Changes view when button was pressed and sets data for selected Member.
   *
   * @throws IOException
   */
  public void rateButton () throws IOException {
    viewMemberProfileViewModel.setMemberUsername();
    viewHandler.setView(viewHandler.menu(), viewHandler.rateFeedback());

  }

  /**
   * Deletes a member after validation.
   *
   * @throws IOException
   */
  public void deleteButton() throws IOException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
    alert.setTitle("Delete account");
    alert.setHeaderText("Are you sure?");
    alert.getDialogPane().setContentText("Are you sure you want to permanently delete this account?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK)
    {
      boolean success = viewMemberProfileViewModel.deleteAccount();
      if (success)
      {
        Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
        alert = new Alert(Alert.AlertType.INFORMATION, "");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Account successfully deleted");
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Click ok to return to reported members.");

        result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
          viewHandler.setView(viewHandler.menu(), viewHandler.viewReportedMemberList());
        }
      }
    }

  }

  /**
   * Changes a view when button was pressed dependable on user type.
   *
   * @throws IOException
   */
  public void goBackToViewedRentalButton() throws IOException {
    if (viewMemberProfileViewModel.checkUserType().equals("Administrator"))
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.searchForMember());
    }
    else
    {
      viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
    }

  }

  /**
   *  Changes view when button was pressed and sets data for selected Member.
   *
   * @throws IOException
   */
  public void viewRatingButton() throws IOException {
    viewMemberProfileViewModel.setMemberUsername();
    viewHandler.setView(viewHandler.menu(), viewHandler.viewRating());
  }

  /**
   * Displays rentals.
   *
   * @param rentals Member's rentals
   */
  public void displayRentals (List < Rental > rentals) {
      if (rentals != null && !rentals.isEmpty())
      {
        for (int i = 0; i < rentals.size(); i++)
        {
          Image image = new Image(rentals.get(i).getPictureLink());
          ImageView imageView = new ImageView();
          imageView.setImage(image);
          imageView.setFitWidth(275);
          imageView.setPreserveRatio(true);
          imageView.setSmooth(true);
          imageView.setCache(true);
          imageView.setId(String.valueOf(rentals.get(i).getId()));
          flowPane.getChildren().add(new StackPane(new InfoOverlay(imageView, rentals.get(i).toString())));
          flowPane.getChildren().get(i)
              .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                try
                {
                  viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
                  viewMemberProfileViewModel.getRental(event.getSource());
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
