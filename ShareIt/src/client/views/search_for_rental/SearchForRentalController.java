package client.views.search_for_rental;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.search_for_rental.SearchForRentalViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Rental;

import java.util.List;

public class SearchForRentalController
{
  @FXML private CheckComboBox<String> categoryCheckComboBox;
  @FXML private ChoiceBox<String> locationBox;
  @FXML private FlowPane flowPane;
  @FXML private TextField searchField;

  private SearchForRentalViewModel searchForRentalViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    searchForRentalViewModel = viewModelFactory.getSearchForRentalViewModel();
    searchField.textProperty()
        .bindBidirectional(searchForRentalViewModel.getSearchField());
    locationBox.setItems(searchForRentalViewModel.getLocations());
    locationBox.getItems().add("");
    categoryCheckComboBox.getItems()
        .addAll(searchForRentalViewModel.getCategories());
    searchForRentalViewModel.setSearchField();

    if (searchField.textProperty().getValue() != null)
    {
      displayRentals(searchForRentalViewModel.onSearchButtonPressed());
    }
    else
    {
      displayRentals(searchForRentalViewModel.getRentalsList());
    }
  }

  public void searchButton()
  {
    List<Rental> rentals = searchForRentalViewModel.onSearchButtonPressed();
    flowPane.getChildren().clear();
    displayRentals(rentals);
  }

  public void filterButton()
  {
    List<Rental> rentals = searchForRentalViewModel
        .onFilterButtonPressed(locationBox.getValue(),
            categoryCheckComboBox.getCheckModel().getCheckedItems());
    flowPane.getChildren().clear();
    displayRentals(rentals);
  }

  public void displayRentals(List<Rental> rentals)
  {
    if (rentals != null && !rentals.isEmpty())
    {
      for (int i = 0; i < rentals.size(); i++)
      {
        Image image = new Image(rentals.get(i).getPictureLink());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(275);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setId(String.valueOf(rentals.get(i).getId()));
        imageView.getStyleClass().add("image");
        flowPane.getChildren().add(new StackPane(
            new InfoOverlay(imageView, rentals.get(i).toString())));
        flowPane.getChildren().get(i)
            .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
              viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
              searchForRentalViewModel.getRental(event.getSource());
            });
      }
    }
  }
}
