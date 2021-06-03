package client.views.edit_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.edit_rental.EditRentalViewModel;
import javafx.beans.binding.Bindings;
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
import java.util.ArrayList;
import java.util.Optional;

/**
 * A class that manages an interface and handle interactions in EditRental view
 */
public class EditRentalController
{
  @FXML private ImageView imageView;
  @FXML private CheckComboBox<String> categoryBox;
  @FXML private AnchorPane parent;
  @FXML private ChoiceBox<String> stateBox;
  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  @FXML private TextArea otherInfoField;

  private EditRentalViewModel editRentalViewModel;
  private ViewHandler viewHandler;
  private Notifications notifications;
  /**
   * Init.
   * Instantiates the notification feature.
   * It checks which categories the rental belongs to.
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    editRentalViewModel = viewModelFactory.getEditRentalViewModel();

    Bindings.bindBidirectional(imageView.imageProperty(),
        editRentalViewModel.imagePropertyProperty());
    nameField.textProperty()
        .bindBidirectional(editRentalViewModel.getNameField());
    descriptionField.textProperty()
        .bindBidirectional(editRentalViewModel.getDescriptionField());
    stateBox.setItems(editRentalViewModel.getStates());
    stateBox.setValue(editRentalViewModel.getSelectedState());
    priceField.textProperty()
        .bindBidirectional(editRentalViewModel.getPriceField());
    otherInfoField.textProperty()
        .bindBidirectional(editRentalViewModel.getOtherInfoField());
    categoryBox.getItems().addAll(editRentalViewModel.getCategories());
    categoryBox.setShowCheckedCount(true);

    checkCategories();

    notifications = Notifications.create().title("Error - invalid input!")
        .graphic(new Rectangle(300, 300, Color.RED))
        .hideAfter(Duration.seconds(3));
  }
  /**
   * If the data is valid confirm the editing in the popup alert
   *
   */
  public void editButton()
  {
    if (checkField("Name", nameField) && checkField("Description",
        descriptionField) && checkField("Price", priceField) && checkPicture(
        imageView))
    {
      String message = editRentalViewModel
          .onEditRentalButtonPressed(stateBox.getValue(),
              categoryBox.getCheckModel().getCheckedItems());

      if ("Edit successful".equals(message))
      {
        Stage stage = (Stage) viewHandler.getStage().getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Rental successfully edited");
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("Click ok to return to manage account.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
          editRentalViewModel.updateRental();
          viewHandler.setView(viewHandler.menu(), viewHandler.manageAccount());
        }
      }
      else
      {
        notifications.owner(parent).text(message).showError();
      }

    }
  }
  /**
   * Handles adding different picture. It allows the member to browse available
   * pictures from their computer by using a GUI windows browser, implemented with the
   * help of JFileChooser.
   */
  public void editPictureButton()
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE",
        "jpg", "gif", "png");
    fileChooser.addChoosableFileFilter(filter);
    int result = fileChooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = fileChooser.getSelectedFile();
      String path = selectedFile.getPath();
      imageView.setFitHeight(220);
      imageView.setFitWidth(170);
      imageView.setPreserveRatio(false);
      imageView.setImage(new Image("file:///" + path));
    }
  }

  public void onGoBack()
  {
    viewHandler.setView(viewHandler.menu(), viewHandler.manageRentals());
  }
  /**
   * Validates data
   * @param message name of field in string in case of invalid data
   * @param nameOfField name of field
   * @return returns true if data is valid, false if not
   */
  private boolean checkField(String message, TextField nameOfField)
  {
    if (nameOfField.textProperty().getValue() == null || nameOfField
        .textProperty().getValue().isBlank())
    {
      notifications.owner(parent).text(message + " cannot be empty")
          .showError();
      return false;
    }
    return true;
  }
  /**
   * Checks if picture was added
   * @param imageView picture
   * @return returns true if picture was added, false if not
   */
  private boolean checkPicture(ImageView imageView)
  {
    if (imageView.getImage() == null)
    {
      notifications.owner(parent).text("Picture has to be added").showError();
      return false;
    }
    return true;
  }
  /**
   * Checks which categories are selected for the rental
   */
  private void checkCategories()
  {
    ArrayList<String> checkedCategories = editRentalViewModel
        .getCheckedCategories();
    for (int i = 0; i < checkedCategories.size(); i++)
    {
      for (int j = 0; j < categoryBox.getItems().size(); j++)
      {
        if (checkedCategories.get(i).equals(categoryBox.getItems().get(j)))
        {
          categoryBox.getCheckModel().check(j);
        }
      }
    }

  }
}
