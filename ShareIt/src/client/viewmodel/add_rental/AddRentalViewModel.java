package client.viewmodel.add_rental;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.City;
import shared.transferobjects.State;

import java.util.ArrayList;

public class AddRentalViewModel {
    private ShareItModel model;
    private final StringProperty searchField;
    private final StringProperty nameField;
    private final StringProperty descriptionField;
    private final ObservableValue stateBox;
    private ObservableList<String> locationsList;
    private final StringProperty priceField;
    private final StringProperty otherInfoField;

    public AddRentalViewModel(ShareItModel model){
        this.model = model;
        searchField = new SimpleStringProperty("Search");
        nameField = new SimpleStringProperty("Name");
        descriptionField = new SimpleStringProperty("Description");
        stateBox = new SimpleStringProperty("State");
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
    public ObservableValue getStateBox(){
        return stateBox;
    }
    public StringProperty getPriceField(){
        return priceField;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }

    public boolean validate(){
        if (searchField.getValue().isEmpty()){
            return false;
        }
        return true;
    }

    public ObservableList<String> getStates(){
        ArrayList<State> stateList = model.getStateList();
        ArrayList<String> stateListString = new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            stateListString.add(stateList.get(i).toString());
        }
        locationsList = FXCollections.observableArrayList(stateListString);
        return locationsList;
    }
}
