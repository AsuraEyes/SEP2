package client.views.manage_account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.manage_account.ManageAccountViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Rental;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageAccountController {
  @FXML private Label usernameLabel;
  @FXML private Label locationLabel;
  @FXML private Label ratingLabel;
  @FXML private Label addressLabel;
  @FXML private Label contactLabel;
  @FXML private Label otherInformationLabel;
  @FXML private FlowPane flowPane;

  private ViewHandler viewHandler;
  private ManageAccountViewModel manageAccountViewModel;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
    this.viewHandler = viewHandler;
    manageAccountViewModel = viewModelFactory.getManageAccountViewModel();
    usernameLabel.textProperty().bind(manageAccountViewModel.getUsernameLabel());
    locationLabel.textProperty().bind(manageAccountViewModel.getLocationLabel());
    ratingLabel.textProperty().bind(manageAccountViewModel.getRatingLabel());
    addressLabel.textProperty().bind(manageAccountViewModel.getAddressLabel());
    contactLabel.textProperty().bind(manageAccountViewModel.getContactLabel());
    otherInformationLabel.textProperty().bind(manageAccountViewModel.getOtherInformationLabel());
    manageAccountViewModel.setProfile();
    displayRentals(manageAccountViewModel.getRentalsOfMemberList());
  }

  public void editOrDeleteInformationButton() throws IOException, SQLException {
    viewHandler.setView(viewHandler.menu(), viewHandler.editOrDeleteAccount());
  }

  public void addRentalButton() throws SQLException, IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.addRental());
  }

  public void displayRentals(List<Rental> rentals) {
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
                  try {
                    viewHandler.setView(viewHandler.menu(), viewHandler.manageRentals());
                    manageAccountViewModel.getRental(event.getSource());
                  }
                  catch (IOException e) {
                    e.printStackTrace();
                  }
                });
      }
    }
  }

  public void viewRating() throws IOException {
    manageAccountViewModel.setMember();
    viewHandler.setView(viewHandler.menu(), viewHandler.viewRating());
  }
}
