package client.views.search_for_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.search_for_member.SearchForMemberViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.transferobjects.Member;

import java.util.List;

/**
 * A class that manages an interface and handle interactions in SearchForMember view
 */
public class SearchForMemberController
{
  @FXML private TextField searchField;
  @FXML private VBox vBox;

  private SearchForMemberViewModel searchForMemberViewModel;
  private ViewHandler viewHandler;

  /**
   * Init.
   *
   * @param viewHandler      the view handler
   * @param viewModelFactory the view model factory
   * @throws IOException the io exception
   */
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    searchForMemberViewModel = viewModelFactory.getSearchForMemberViewModel();
    searchField.textProperty()
        .bindBidirectional(searchForMemberViewModel.getSearchField());

    searchForMemberViewModel.setSearchField();

    if (searchField.textProperty().getValue() != null)
    {
      displayMembers(searchForMemberViewModel.onSearchButtonPressed());
    }
    else
    {
      displayMembers(searchForMemberViewModel.getMembersList());
    }
  }

  /**
   * Runs method from viewModel after button was pressed.
   */
  public void searchButton()
  {
    List<Member> members = searchForMemberViewModel.onSearchButtonPressed();
    vBox.getChildren().clear();
    displayMembers(members);
  }

  /**
   * Displays members.
   *
   * @param members Members that matched with input
   */
  public void displayMembers(List<Member> members)
  {
    if (members != null && !members.isEmpty())
    {
      for (int i = 0; i < members.size(); i++)
      {
        VBox ratingBox = new VBox();
        Label ratingLabel = new Label(
            "Username: " + members.get(i).getUsername());
        ratingLabel.getStyleClass().add("label");
        Text rating = new Text(
            "Average rating: " + members.get(i).getAverageReview());
        ratingBox.getChildren().addAll(ratingLabel, rating);
        ratingBox.setSpacing(10);
        ratingBox.setPadding(new Insets(20, 160, 20, 160));
        rating.getStyleClass().add("text");
        ratingBox.getStyleClass().add("vbox");
        vBox.getChildren().add(ratingBox);
        vBox.getChildren().get(i)
            .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
              searchForMemberViewModel.setMemberUsername(event.getSource());
              viewHandler
                  .setView(viewHandler.menu(), viewHandler.viewMemberProfile());
            });
      }
    }
  }
}
