package client.viewmodel.edit_rental;

import client.model.member.MemberModel;
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
import java.util.ArrayList;
/**
 * A class that holds and manages data from the EditRental view.
 */
public class EditRentalViewModel
{
  private StringProperty nameField;
  private StringProperty descriptionField;
  private StringProperty priceField;
  private StringProperty otherInfoField;
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private ObservableList<String> statesList;
  private ObservableList<String> categoriesList;
  private ObjectProperty<Image> imageProperty;

    /**
     * Instantiates StringProperties used for binding with the fields in the controller
     *
     * @param rentalModel The model that this ViewModel uses
     * @param memberModel The model that this ViewModel uses
     */
  public EditRentalViewModel(RentalModel rentalModel, MemberModel memberModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;

    nameField = new SimpleStringProperty();
    descriptionField = new SimpleStringProperty();
    priceField = new SimpleStringProperty();
    otherInfoField = new SimpleStringProperty();
    imageProperty = new SimpleObjectProperty<>();
    rentalModel.addListener("selectedRental", this::selectedRental);
  }
    /**
     * Rental related data is sent to the controller through binding.
     * @param evt
     */
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

        if (rental.getOtherInformation() != null)
        {
          otherInfoField.setValue(rental.getOtherInformation());
        }
      }
    });
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
     * After EditRental button have been pressed this method sends data to the model
     * and receives a validation message if data is missing, invalid or the rental
     * was successfully edited.
     * @param selectedState    The selected state
     * @param selectedCategory The selected category
     * @return Rental object with edited data
     */
  public String onEditRentalButtonPressed(Object selectedState,
      ObservableList<String> selectedCategory)
  {
    ArrayList<String> selectedCategoriesList = new ArrayList<>(
        selectedCategory);
    String path = imageProperty.get().getUrl();
    path = path.replaceAll("file:", "");

    return rentalModel.updateCheckRentalData(nameField.getValue(), path,
        descriptionField.getValue(), priceField.getValue(),
        otherInfoField.getValue(), (String) selectedState,
        selectedCategoriesList);
  }
    /**
     * Gets all the states in a list from the database and places them in a Observable
     * list to be bound to a listing element in controller.
     *
     * @return a list of states
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
     * Gets all the categories in a list from the database and places them in a Observable
     * list to be bound to a listing element in controller.
     *
     * @return a list of categories
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
    /**
     * Gets State that was selected while editing Rental.
     *
     * @return the state that was selected
     */
  public String getSelectedState()
  {
    return rentalModel.getSelectedRental().getStateName();
  }
    /**
     * Gets Categories that were selected while editing Rental.
     *
     * @return the list of selected categories
     */
  public ArrayList<String> getCheckedCategories()
  {
    return rentalModel.getSelectedRental().getSelectedCategories();
  }

  public void updateRental()
  {
    rentalModel.setAllMemberRentals(memberModel.getLoggedInUsername());
  }
}
