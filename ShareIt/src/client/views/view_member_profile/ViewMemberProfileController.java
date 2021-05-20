package client.views.view_member_profile;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.model.database.member.MemberDAOImpl;
import shared.transferobjects.Member;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Rental;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

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
  @FXML private FlowPane flowPane;

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

    displayRentals(viewMemberProfileViewModel.getRentalsOfMemberList(viewMemberProfileViewModel.getMemberUsername()));

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

  public void rateButton(ActionEvent actionEvent) throws IOException {
    viewMemberProfileViewModel.setMemberUsername();
    viewHandler.setView(viewHandler.menu(),viewHandler.rateFeedback());

  }

  public void deleteButton(ActionEvent actionEvent) throws SQLException, IOException {

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
  public void displayRentals(List<Rental> rentals) throws RemoteException
  {
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
        System.out.println(rentals.get(i).getPictureLink());
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
