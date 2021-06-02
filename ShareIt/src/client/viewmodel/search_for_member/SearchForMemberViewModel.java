package client.viewmodel.search_for_member;

import client.model.member.MemberModel;
import client.model.message.MessageModel;
import client.model.rental.RentalModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import shared.transferobjects.Member;

import java.util.List;
/**
 * A class that holds and manages data from the SearchForMember view.
 */
public class SearchForMemberViewModel
{
  private final StringProperty searchField;
  private final RentalModel rentalModel;
  private final MemberModel memberModel;
  private final MessageModel messageModel;
  /**
   * Instantiates a new SearchForMemberViewModel.
   *
   * @param model The model that this ViewModel uses
   */
  public SearchForMemberViewModel(RentalModel rentalModel,
      MemberModel memberModel, MessageModel messageModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;

    searchField = new SimpleStringProperty();
  }
    /**
     * Gets searchField.
     *
     * @return returns searchField input
     */
  public StringProperty getSearchField()
  {
    return searchField;
  }
    /**
     * Sets search field.
     */
  public void setSearchField()
  {
    searchField.setValue(messageModel.getSearchText());
  }
    /**
     * After Search button have been pressed this method sends data to the model.
     *
     * @return returns a list of Members dependable on input
     */
  public List<Member> onSearchButtonPressed()
  {
    return memberModel.checkSearchForMember(searchField.getValue());
  }
    /**
     * Gets list of Members.
     *
     * @return returns list of members
     */
  public List<Member> getMembersList()
  {
    return memberModel.getMembersList();
  }
    /**
     * Sets member username.
     *
     * @param source (?)
     */
  public void setMemberUsername(Object source)
  {
    if (source instanceof VBox)
    {
      VBox vBox = (VBox) source;
      if (vBox.getChildren().get(0) instanceof Label)
      {
        Label label = (Label) vBox.getChildren().get(0);
        String username = label.getText();
        memberModel.setMemberUsername(username.substring(10));
        rentalModel.setAllMemberRentals(username.substring(10));
      }
    }
  }
}
