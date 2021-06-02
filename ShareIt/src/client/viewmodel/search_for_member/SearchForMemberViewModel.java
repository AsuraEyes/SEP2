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
  private StringProperty searchField;
  private RentalModel rentalModel;
  private MemberModel memberModel;
  private MessageModel messageModel;
  /**
   * Instantiates StringProperties used for binding with the fields in the controller
   *
   * @param rentalModel The model that this ViewModel uses
   * @param messageModel The model that this ViewModel uses
   */
  public SearchForMemberViewModel(RentalModel rentalModel,
      MemberModel memberModel, MessageModel messageModel)
  {
    this.rentalModel = rentalModel;
    this.memberModel = memberModel;
    this.messageModel = messageModel;

    searchField = new SimpleStringProperty();
  }

  public StringProperty getSearchField()
  {
    return searchField;
  }

  public void setSearchField()
  {
    searchField.setValue(messageModel.getSearchText());
  }

  public List<Member> onSearchButtonPressed()
  {
    return memberModel.checkSearchForMember(searchField.getValue());
  }

  public List<Member> getMembersList()
  {
    return memberModel.getMembersList();
  }
    /**
     * On mouse click, the clicked object is retrieved and analyzed. The purpose
     * is to get the username that is being hold by a label and store it in the
     * MemberModelManager and sets allMemberRentals in RentalModel.
     * @param source
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
