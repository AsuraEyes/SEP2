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

public class SearchForRentalViewModel
{
  private RentalModel rentalModel;
  private MessageModel messageModel;

  private StringProperty searchField;
  private StringProperty locationLabel;
  private ObservableList<String> locationsList;
  private ObservableList<String> categoriesList;

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

  public List<Rental> onSearchButtonPressed()
  {
    return rentalModel.checkSearch(searchField.getValue());
  }

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
