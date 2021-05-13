package client.viewmodel.add_rental;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddRentalViewModel {
    private final StringProperty searchField;
    private final StringProperty nameField;
    private final StringProperty descriptionField;
    private final StringProperty stateField;
    private final StringProperty priceField;
    private final StringProperty otherInfoField;

    public AddRentalViewModel(){
        searchField = new SimpleStringProperty("Search");
        nameField = new SimpleStringProperty("Name");
        descriptionField = new SimpleStringProperty("Description");
        stateField = new SimpleStringProperty("State");
        priceField = new SimpleStringProperty("Price");
        otherInfoField = new SimpleStringProperty("Other Information");
    }

    public StringProperty getSearchField(){
        return searchField;
    }
    public StringProperty getNameField(){
        return nameField;
    }
    public StringProperty getDescriptionField(){
        return descriptionField;
    }
    public StringProperty getStateField(){
        return stateField;
    }
    public StringProperty getPriceField(){
        return priceField;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }
}
