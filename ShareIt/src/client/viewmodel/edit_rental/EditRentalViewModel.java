package client.viewmodel.edit_rental;


import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import shared.transferobjects.Category;
import shared.transferobjects.Rental;
import shared.transferobjects.State;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;

public class EditRentalViewModel {
    private RentalModel rentalModel;
    private MemberModel memberModel;
    private MessageModel messageModel;

    private final StringProperty nameField;
    private final StringProperty descriptionField;
    private ObservableList<String> statesList;
    private final StringProperty priceField;
    private final StringProperty otherInfoField;
    private ObservableList<String> categoriesList;
    private ObjectProperty<Image> imageProperty;


    public EditRentalViewModel(RentalModel rentalModel, MemberModel memberModel, MessageModel messageModel) {
        this.rentalModel = rentalModel;
        this.memberModel = memberModel;
        this.messageModel = messageModel;

        nameField = new SimpleStringProperty();
        descriptionField = new SimpleStringProperty();
        priceField = new SimpleStringProperty();
        otherInfoField = new SimpleStringProperty();
        imageProperty = new SimpleObjectProperty<>();
        rentalModel.addListener("selectedRental",this::selectedRental);
    }


    private void selectedRental(PropertyChangeEvent evt)
    {
        Platform.runLater(() -> {
            if (evt.getNewValue() instanceof Rental)
            {
                Rental rental = (Rental) evt.getNewValue();
                nameField.setValue(rental.getName());
                descriptionField.setValue(rental.getDescription());
                priceField.setValue(String.valueOf(rental.getPrice()));
                imageProperty.setValue(new Image(rental.getPictureLink()));

                if(rental.getOtherInformation() != null) {
                    otherInfoField.setValue(rental.getOtherInformation());
                }
            }
        });
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
    public ObjectProperty<Image> imagePropertyProperty()
    {
        return imageProperty;
    }
    public StringProperty getOtherInfoField(){
        return otherInfoField;
    }


    public String onEditRentalButtonPressed(Object selectedState, ObservableList<String> selectedCategory) throws IOException {
        ArrayList<String> selectedCategoriesList = new ArrayList<>(selectedCategory);
        String path = imageProperty.get().getUrl();
        path = path.replaceAll("file:","");

        return rentalModel.updateCheckRentalData(nameField.getValue(), path, descriptionField.getValue(), priceField.getValue(), otherInfoField.getValue(), (String) selectedState
            , selectedCategoriesList);
    }

    public ObservableList<String> getStates(){
        ArrayList<State> stateList = rentalModel.getStateList();
        ArrayList<String> stateListString = new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            stateListString.add(stateList.get(i).toString());
        }
        statesList = FXCollections.observableArrayList(stateListString);
        return statesList;
    }

    public ObservableList<String> getCategories(){
        ArrayList<Category> categoryList = rentalModel.getCategoryList();
        ArrayList<String> categoryListString = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryListString.add(categoryList.get(i).toString());
        }
        categoriesList = FXCollections.observableArrayList(categoryListString);
        return categoriesList;
    }
    public String getSelectedState(){
        return rentalModel.getSelectedRental().getStateName();
    }
    public ArrayList<String> getCheckedCategories(){
        return rentalModel.getSelectedRental().getSelectedCategories();
    }
}
