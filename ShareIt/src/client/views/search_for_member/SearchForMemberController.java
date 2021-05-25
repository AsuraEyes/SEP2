package client.views.search_for_member;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.search_for_member.SearchForMemberViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class SearchForMemberController
{
  @FXML private TextField searchField;
  @FXML private VBox vBox;

  private SearchForMemberViewModel searchForMemberViewModel;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
          throws IOException
  {
    this.viewHandler = viewHandler;
    searchForMemberViewModel = viewModelFactory.getSearchForMemberViewModel();
    searchField.textProperty()
            .bindBidirectional(searchForMemberViewModel.getSearchField());

    searchForMemberViewModel.setSearchField();

    System.out.println("Controller: " + searchField.textProperty().getValue());
    if(searchField.textProperty().getValue() != null)
    {
      displayMembers(searchForMemberViewModel.onSearchButtonPressed());
    }
    else
    {
      displayMembers(searchForMemberViewModel.getMembersList());
    }
  }

  public void searchButton(ActionEvent actionEvent)
  {
    List<Member> members = searchForMemberViewModel.onSearchButtonPressed();
    vBox.getChildren().clear();
    displayMembers(members);
  }

  public void displayMembers(List<Member> members)
  {
    if (members != null && !members.isEmpty())
    {
      for (int i = 0; i < members.size(); i++)
      {
        VBox ratingBox = new VBox();
        Label ratingLabel = new Label("Username: " + members.get(i).getUsername());
        ratingLabel.setFont(Font.font ("Californian FB", 24));
        ratingLabel.setTextFill(Color.WHITE);
        Text rating = new Text("Average rating: " + members.get(i).getAverageReview());
        rating.setFill(Color.WHITE);
        ratingBox.getChildren().addAll(ratingLabel,rating);
        ratingBox.setSpacing(10);
        ratingBox.setPadding(new Insets(20,160,20,160));
        ratingBox.setStyle("-fx-background-color: #FF8C64;");
        vBox.getChildren().add(ratingBox);
        vBox.getChildren().get(i)
                .addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                  try
                  {
                    searchForMemberViewModel.setMemberUsername(event.getSource());
                    viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
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
