package client.viewmodel.search_for_rental;

import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Rental;

import java.util.ArrayList;
import java.util.List;
/**
 * A class that holds and manages data from the SearchForRental view.
 */
public class SearchForRentalViewModel
{
  private RentalModel rentalModel;
  private MessageModel messageModel;

  private StringProperty searchField;
  private StringProperty locationLabel;
  private ObservableList<String> locationsList;
  private ObservableList<String> categoriesList;
    /**
     * Instantiates StringProperties used for binding with the fields in the controller
     *
     * @param rentalModel The model that this ViewModel uses
     * @param messageModel The model that this ViewModel uses
     */
  public SearchForRentalViewModel(RentalModel rentalModel,
      MessageModel messageModel)
  {
    this.rentalModel = rentalModel;
    this.messageModel = messageModel;

    searchField = new SimpleStringProperty();
    locationLabel = new SimpleStringProperty();
  }

  public StringProperty getSearchField()
  {
    return searchField;
  }

  public StringProperty getLocationLabel()
  {
    return locationLabel;
  }
    /**
     * On mouse click, the clicked object is retrieved and analyzed. The purpose
     * is to get the rental's id stored in the ImageView id field. The id is
     * used to retrieve the selected rental from the  list of rentals, and
     * send it further.
     *
     * @param object
     */
  public void getRental(Object object)
  {
    if (object instanceof StackPane)
    {
      StackPane stackPane = (StackPane) object;
      if (stackPane.getChildren().get(0) instanceof InfoOverlay)
      {
        InfoOverlay infoOverlay = (InfoOverlay) stackPane.getChildren().get(0);
        if (infoOverlay.getContent() instanceof ImageView)
        {
          ImageView imageView = (ImageView) infoOverlay.getContent();
          for (int i = 0; i < getRentalsList().size(); i++)
          {
            if (imageView.getId()
                .equals(String.valueOf(getRentalsList().get(i).getId())))
            {
              rentalModel.sendSelectedRental(getRentalsList().get(i));
            }
          }
        }
      }
    }
  }
    /**
     * Gets cities in a list from the database and places them in a Observable
     * list to be bound to a listing element in controller.
     * @return a list of cities
     */
  public ObservableList<String> getLocations()
  {
    ArrayList<City> cityList = rentalModel.getCityList();
    ArrayList<String> cityListString = new ArrayList<>();
    for (int i = 0; i < cityList.size(); i++)
    {
      cityListString.add(cityList.get(i).toString());
    }
    locationsList = FXCollections.observableArrayList(cityListString);
    return locationsList;
  }
    /**
     * Get all the categories in a list from the database and places them in a Observable
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
     * After Search button have been pressed this method sends data to the model.
     *
     * @return a list of Rentals dependable on input
     */
  public List<Rental> onSearchButtonPressed()
  {
    return rentalModel.checkSearch(searchField.getValue());
  }
    /**
     * After Filter button have been pressed this method sends data to the model.
     *
     * @param selectedCity     Selected city
     * @param selectedCategory Selected category
     * @return a list of Rentals dependable on input and selected city, category
     */
  public List<Rental> onFilterButtonPressed(String selectedCity,
      ObservableList<String> selectedCategory)
  {
    ArrayList<String> selectedCategoriesList = new ArrayList<>(
        selectedCategory);
    return rentalModel
        .checkSearchWithFilter(searchField.getValue(), selectedCity,
            selectedCategoriesList);
  }

  public ArrayList<Rental> getRentalsList()
  {
    return rentalModel.getRentalsList();
  }

  public void setSearchField()
  {
    searchField.setValue(messageModel.getSearchText());
  }

}
