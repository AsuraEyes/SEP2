package client.views.add_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.add_rental.AddRentalViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddRentalController {
    public TextField searchField;
    public TextField nameField;
    public TextField descriptionField;
    public TextField stateField;
    public TextField priceField;
    public TextArea otherInfoField;

    private AddRentalViewModel addRentalViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        addRentalViewModel = viewModelFactory.getAddRentalViewModel();
        searchField.textProperty().bindBidirectional(addRentalViewModel.getSearchField());
        nameField.textProperty().bindBidirectional(addRentalViewModel.getNameField());
        descriptionField.textProperty().bind(addRentalViewModel.getDescriptionField());
        stateField.textProperty().bind(addRentalViewModel.getStateField());
        priceField.textProperty().bindBidirectional(addRentalViewModel.getPriceField());
        otherInfoField.textProperty().bind(addRentalViewModel.getOtherInfoField());
    }

    public void searchButton(ActionEvent actionEvent) {

    }

    public void addButton(ActionEvent actionEvent) {

    }
}
