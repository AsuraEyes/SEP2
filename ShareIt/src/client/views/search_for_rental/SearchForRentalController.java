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

import java.io.IOException;
import java.util.List;

/**
 * A class that manages an interface and handle interactions in SearchForRentals view
 */
public class SearchForRentalController
{
  @FXML private CheckComboBox<String> categoryCheckComboBox;
  @FXML private ChoiceBox<String> locationBox;
  @FXML private FlowPane flowPane;
  @FXML private TextField searchField;

  private SearchForRentalViewModel searchForRentalViewModel;
  private ViewHandler viewHandler;

  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   * @throws IOException the io exception
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException
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

    if(searchField.textProperty().getValue() != null)
    {
      displayRentals(searchForRentalViewModel.onSearchButtonPressed());
    }
    else
    {
      displayRentals(searchForRentalViewModel.getRentalsList());
    }
  }

  /**
   * Runs method from viewModel after button was pressed.
   *
   * @throws IOException
   */
  public void searchButton() throws IOException
  {
    List<Rental> rentals = searchForRentalViewModel.onSearchButtonPressed();
    flowPane.getChildren().clear();
    displayRentals(rentals);
  }

  /**
   * Runs method from viewModel after button was pressed.
   *
   * @throws IOException the io exception
   */
  public void filterButton() throws IOException {
    List<Rental> rentals = searchForRentalViewModel
        .onFilterButtonPressed(locationBox.getValue(),
            categoryCheckComboBox.getCheckModel().getCheckedItems());
    flowPane.getChildren().clear();
    displayRentals(rentals);
  }

  /**
   * Displays rentals.
   *
   * @param rentals Rentals that matched with input
   */
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
          flowPane.getChildren().add(new StackPane(new InfoOverlay(imageView, rentals.get(i).toString())));
          flowPane.getChildren().get(i)
              .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                try
                {
                  viewHandler.setView(viewHandler.menu(), viewHandler.viewRental());
                  searchForRentalViewModel.getRental(event.getSource());
                }
                catch (IOException e)
                {
                  e.printStackTrace();
                }
              });
        }
      }
  }
}
