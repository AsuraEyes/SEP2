package client.views.add_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.add_rental.AddRentalViewModel;
import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.decoration.Decoration;
import org.controlsfx.control.decoration.Decorator;
import org.controlsfx.control.decoration.GraphicDecoration;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

public class AddRentalController {
    @FXML
    private TextField searchField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea otherInfoField;
    @FXML
    private AnchorPane parent;
    private Notifications notifications;

    private ValidationSupport validationSupport;
    private AddRentalViewModel addRentalViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        addRentalViewModel = viewModelFactory.getAddRentalViewModel();
        this.viewHandler = viewHandler;

        validationSupport = new ValidationSupport();
        validationSupport.registerValidator(nameField, Validator.createEmptyValidator("Text is required"));


        notifications =  Notifications.create()
            .title("ASDAASDASDQweqe")
            .graphic(new Rectangle(300, 300, Color.RED)) // sets node to display
            .hideAfter(Duration.seconds(1));


        /*addRentalViewModel = viewModelFactory.getAddRentalViewModel();
        searchField.textProperty().bindBidirectional(addRentalViewModel.getSearchField());
        nameField.textProperty().bindBidirectional(addRentalViewModel.getNameField());
        descriptionField.textProperty().bind(addRentalViewModel.getDescriptionField());
        stateField.textProperty().bind(addRentalViewModel.getStateField());
        priceField.textProperty().bindBidirectional(addRentalViewModel.getPriceField());
        otherInfoField.textProperty().bind(addRentalViewModel.getOtherInfoField());*/
    }

    public void searchButton(ActionEvent actionEvent) {
        notifications.owner(parent).text("Search field cannot be empty").showError();
    }

    public void addButton(ActionEvent actionEvent) {

    }
}
