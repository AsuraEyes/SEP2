package client.viewmodel.add_rental;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Category;
import shared.transferobjects.Member;
import shared.transferobjects.State;

import java.io.IOException;
import java.util.ArrayList;

public class AddRentalViewModel {
    private ShareItModel model;
    private final StringProperty nameField;
    private final StringProperty pictureLinkField;
    private final StringProperty descriptionField;
    private final ObservableValue<String> stateBox;
    private ObservableList<String> statesList;
    private final StringProperty priceField;
    private final StringProperty otherInfoField;
    private final ObservableValue<String> categoryBox;
    private ObservableList<String> categoriesList;

    public AddRentalViewModel(ShareItModel model){
        this.model = model;
        nameField = new SimpleStringProperty();
        pictureLinkField = new SimpleStringProperty();
        descriptionField = new SimpleStringProperty();
        stateBox = new SimpleStringProperty();
        priceField = new SimpleStringProperty();
        otherInfoField = new SimpleStringProperty();
        categoryBox = new SimpleStringProperty();
    }

    public StringProperty getNameField(){
        return nameField;
    }
    public StringProperty getPictureLinkField(){
        return pictureLinkField;
    }
    public StringProperty getDescriptionField(){
        return descriptionField;
    }
    public ObservableValue<String> getStateBox(){
        return stateBox;
    }
    public StringProperty getPriceField(){
        return priceField;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }
    public ObservableValue<String> getCategoryBox() {
        return categoryBox;
    }

    public void onAddRentalButtonPressed(Object selectedState, Object selectedCategory, Member member) throws IOException {
        model.checkRentalData(nameField.getValue(), pictureLinkField.getValue(), descriptionField.getValue(), priceField.getValue(), otherInfoField.getValue(), (String) selectedState, member);
    }
    /*public ObservableList<String> getStates(){
        ArrayList<State> stateList = model.getStateList();
        ArrayList<String> stateListString = new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            stateListString.add(stateList.get(i).toString());
        }
        statesList = FXCollections.observableArrayList(stateListString);
        return statesList;
    }*/

    /*public ObservableList<String> getCategories(){
        ArrayList<Category> categoryList = model.getCategoryList();
        ArrayList<String> categoryListString = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryListString.add(categoryList.get(i).toString());
        }
        categoriesList = FXCollections.observableArrayList(categoryListString);
        return categoriesList;
    }*/
}
