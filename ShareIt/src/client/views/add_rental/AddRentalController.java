package client.views.add_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.add_rental.AddRentalViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * A class that manages an interface and handle interactions in AddRental view
 */
public class AddRentalController {

  @FXML private ImageView pictureView;
  @FXML private CheckComboBox categoryBox;
  @FXML private AnchorPane parent;
  @FXML private ChoiceBox<String> stateBox;
  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  @FXML private TextArea otherInfoField;

  private AddRentalViewModel addRentalViewModel;
  private ViewHandler viewHandler;
  private Notifications notifications;

  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   * @throws SQLException
   * @throws IOException
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, IOException {

    addRentalViewModel = viewModelFactory.getAddRentalViewModel();
    this.viewHandler = viewHandler;
    addRentalViewModel = viewModelFactory.getAddRentalViewModel();
    nameField.textProperty().bindBidirectional(addRentalViewModel.getNameField());
    descriptionField.textProperty().bindBidirectional(addRentalViewModel.getDescriptionField());
    stateBox.setItems(addRentalViewModel.getStates());
    stateBox.getSelectionModel().selectFirst();
    priceField.textProperty().bindBidirectional(addRentalViewModel.getPriceField());
    otherInfoField.textProperty().bindBidirectional(addRentalViewModel.getOtherInfoField());
    categoryBox.getItems().addAll(addRentalViewModel.getCategories());
    Bindings.bindBidirectional(pictureView.imageProperty(), addRentalViewModel.imagePropertyProperty());

    notifications =  Notifications.create()
            .title("Error - invalid input!")
            .graphic(new Rectangle(300, 300, Color.RED))
            .hideAfter(Duration.seconds(3));
  }

  /**
   * If data is valid it uses a method from viewModel
   *
   * @throws IOException
   */
  public void addRentalButton() throws IOException {
    boolean ok = true;
    if(checkField("Name", nameField) && checkField("Description",descriptionField) && checkField("Price", priceField) && checkPicture(pictureView)){
      String message = addRentalViewModel.onAddRentalButtonPressed(stateBox.getValue(), categoryBox.getCheckModel().getCheckedItems());

      switch (message){
        case "Adding successful":

          Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
          Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
          alert.setTitle("Confirmation");
          alert.setHeaderText("New rental successfully created");
          alert.initOwner(stage);
          alert.getDialogPane().setContentText("Click ok to get to welcome page.");

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK)
          {
            viewHandler.setView(viewHandler.menu(), viewHandler.welcomePage());
          }
          break;
        default:
          notifications.owner(parent).text(message).showError();
      }

    }
  }

  /**
   * Handles adding a picutre.
   */
  public void addPictureButton() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("os.name")));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
    fileChooser.addChoosableFileFilter(filter);
    int result = fileChooser.showSaveDialog(null);
    if(result == JFileChooser.APPROVE_OPTION){
      File selectedFile = fileChooser.getSelectedFile();
      String path = selectedFile.getPath();
      pictureView.setFitHeight(220);
      pictureView.setFitWidth(170);
      pictureView.setPreserveRatio(false);
      pictureView.setImage(new Image("file:///"+path));
    }
  }

  /**
   * Changes a view if button was pressed.
   *
   * @throws IOException
   */
  public void onGoBack() throws IOException {
    viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
  }

  /**
   * Validates data
   * @param message name of field in string in case of invalid data
   * @param nameOfField name of field
   * @return returns true if data is valid, false if not
   */
  private boolean checkField (String message, TextField nameOfField){
    if (nameOfField.textProperty().getValue() == null || nameOfField.textProperty().getValue().isBlank()) {
      notifications.owner(parent).text(message + " cannot be empty").showError();
      return false;
    }
    return true;
  }

  /**
   * Checks if picture was added
   * @param imageView picture
   * @return returns true if picture was added, false if not
   */
  private boolean checkPicture(ImageView imageView){
    if(imageView.getImage() == null){
      notifications.owner(parent).text("Picture has to be added").showError();
      return false;
    }
    return true;
  }
}
