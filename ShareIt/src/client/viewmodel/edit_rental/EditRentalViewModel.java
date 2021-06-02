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
     * Instantiates a new EditRentalViewModel.
     *
     * @param shareItModel The model that this ViewModel uses
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
     * (?)
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
    /**
     * Gets nameField.
     *
     * @return returns nameField input
     */
  public StringProperty getNameField()
  {
    return nameField;
  }
    /**
     * Gets descriptionField.
     *
     * @return returns descriptionField input
     */
  public StringProperty getDescriptionField()
  {
    return descriptionField;
  }
    /**
     * Gets priceField.
     *
     * @return returns priceField input
     */
  public StringProperty getPriceField()
  {
    return priceField;
  }
    /**
     * Image property property object property.
     *
     * @return the object property
     */
  public ObjectProperty<Image> imagePropertyProperty()
  {
    return imageProperty;
  }
    /**
     * Gets otherInformation.
     *
     * @return returns otherInformationField input
     */
  public StringProperty getOtherInfoField()
  {
    return otherInfoField;
  }
    /**
     * After EditRental button have been pressed this method sends data to the model.
     *
     * @param selectedState    The selected state
     * @param selectedCategory The selected category
     * @return returns Rental object with edited data
     * @throws IOException
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
     * Gets all the states in a list.
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
     * Gets all the categories in a list.
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
    /**
     * Gets State that was selected while editing Rental.
     *
     * @return returns the state that was selected
     */
  public String getSelectedState()
  {
    return rentalModel.getSelectedRental().getStateName();
  }
    /**
     * Gets Categories that were selected while editing Rental.
     *
     * @return returns the list of selected categories
     */
  public ArrayList<String> getCheckedCategories()
  {
    return rentalModel.getSelectedRental().getSelectedCategories();
  }

  public void updateRental()
  {
    rentalModel.loadRentals();
    rentalModel.setAllMemberRentals(memberModel.getLoggedInUsername());
  }
}
