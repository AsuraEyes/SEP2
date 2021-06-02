package client.viewmodel.add_rental;

import client.model.member.MemberModel;
import client.model.rental.RentalModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import shared.transferobjects.Category;
import shared.transferobjects.State;

import java.util.ArrayList;

/**
 * A class that holds and manages data from the AddRental view.
 */
public class AddRentalViewModel
{
  private final StringProperty nameField;
  private final StringProperty descriptionField;
  private final StringProperty priceField;
  private final StringProperty otherInfoField;
  private final RentalModel rentalModel;
  private final MemberModel memberModel;
  private ObservableList<String> statesList;
  private ObservableList<String> categoriesList;
  private final ObjectProperty<Image> imageProperty;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param rentalModel The model that this ViewModel uses
   * @param memberModel The model that this ViewModel uses
   */
  public AddRentalViewModel(RentalModel rentalModel, MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    nameField = new SimpleStringProperty();
    descriptionField = new SimpleStringProperty();
    priceField = new SimpleStringProperty();
    otherInfoField = new SimpleStringProperty();
    imageProperty = new SimpleObjectProperty<>();
  }

  public StringProperty getNameField()
  {
    return nameField;
  }

  public StringProperty getDescriptionField()
  {
    return descriptionField;
  }

  public StringProperty getPriceField()
  {
    return priceField;
  }

  public ObjectProperty<Image> imagePropertyProperty()
  {
    return imageProperty;
  }

  public StringProperty getOtherInfoField()
  {
    return otherInfoField;
  }
    /**
     * After AddRental button have been pressed this method sends data to the model.
     *
     * @param selectedState    The selected state
     * @param selectedCategory The selected category
     * @return returns new Rental object
     */
  public String onAddRentalButtonPressed(Object selectedState,
      ObservableList<String> selectedCategory)
  {
    ArrayList<String> selectedCategoriesList = new ArrayList<>(
        selectedCategory);

    String path = imageProperty.get().getUrl();
    path = path.replaceAll("file:", "");

    return rentalModel.checkRentalData(nameField.getValue(), path,
        descriptionField.getValue(), priceField.getValue(),
        otherInfoField.getValue(), (String) selectedState,
        selectedCategoriesList);
  }
    /**
     * Get all the states from the database and places them in a Observable
     * list to be bound to a listing element in controller.
     *
     * @return returns a list of states
     */
  public ObservableList<String> getStates()
  {
    ArrayList<State> stateList = rentalModel.getStateList();
    ArrayList<String> stateListString = new ArrayList<>();
    for (int i = 0; i < stateList.size(); i++)
    {
      stateListString.add(stateList.get(i).toString());
    }
    statesList = FXCollections.observableArrayList(stateListString);
    return statesList;
  }
    /**
     * Get all the categories from the database and places them in a Observable
     * list to be bound to a listing element in controller.
     *
     * @return returns a list of categories
     */
  public ObservableList<String> getCategories()
  {
    ArrayList<Category> categoryList = rentalModel.getCategoryList();
    ArrayList<String> categoryListString = new ArrayList<>();
    for (int i = 0; i < categoryList.size(); i++)
    {
      categoryListString.add(categoryList.get(i).toString());
    }
    categoriesList = FXCollections.observableArrayList(categoryListString);
    return categoriesList;
  }

  public void setAllMemberRentals()
  {
    rentalModel.setAllMemberRentals(memberModel.getLoggedInUsername());
  }
}
