package client.viewmodel.edit_rental;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Category;
import shared.transferobjects.State;

import java.io.IOException;
import java.util.ArrayList;

public class EditRentalViewModel {
    private ShareItModel shareItModel;

    private final StringProperty nameField;
    private final StringProperty descriptionField;
    private ObservableList<String> statesList;
    private final StringProperty priceField;
    private final StringProperty otherInfoField;
    private ObservableList<String> categoriesList;
    private String username;

    public EditRentalViewModel(ShareItModel shareItModel) {
        this.shareItModel = shareItModel;
        nameField = new SimpleStringProperty();
        descriptionField = new SimpleStringProperty();
        priceField = new SimpleStringProperty();
        otherInfoField = new SimpleStringProperty();
    }

    public StringProperty getNameField(){
        return nameField;
    }
    public StringProperty getDescriptionField(){
        return descriptionField;
    }
    public StringProperty getPriceField(){
        return priceField;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }

    public String onEditRentalButtonPressed(Object selectedState, ObservableList<String> selectedCategory, String pictureLink) throws IOException {
        ArrayList<String> selectedCategoriesList = new ArrayList<>(selectedCategory);
        return shareItModel.checkRentalData(nameField.getValue(), pictureLink, descriptionField.getValue(), priceField.getValue(), otherInfoField.getValue(), (String) selectedState,username, selectedCategoriesList);
    }

    public ObservableList<String> getStates(){
        ArrayList<State> stateList = shareItModel.getStateList();
        ArrayList<String> stateListString = new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            stateListString.add(stateList.get(i).toString());
        }
        statesList = FXCollections.observableArrayList(stateListString);
        return statesList;
    }

    public ObservableList<String> getCategories(){
        ArrayList<Category> categoryList = shareItModel.getCategoryList();
        ArrayList<String> categoryListString = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryListString.add(categoryList.get(i).toString());
        }
        categoriesList = FXCollections.observableArrayList(categoryListString);
        return categoriesList;
    }
}
