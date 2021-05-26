package client.viewmodel.view_member_profile;

import client.model.ShareItModel;
import client.model.state.StateManager;
import client.model.state.VisitorState;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ViewMemberProfileViewModel
{
  private ShareItModel model;
  private final SimpleStringProperty searchField;
  private final SimpleStringProperty usernameLabel;
  private final SimpleStringProperty locationLabel;
  private final SimpleStringProperty ratingLabel;
  private final SimpleStringProperty addressLabel;
  private final SimpleStringProperty contactLabel;
  private final SimpleStringProperty otherInformationLabel;

  public ViewMemberProfileViewModel(ShareItModel model)
  {
    this.model = model;
    searchField = new SimpleStringProperty();
    usernameLabel = new SimpleStringProperty();
    locationLabel = new SimpleStringProperty();
    ratingLabel = new SimpleStringProperty();
    addressLabel = new SimpleStringProperty();
    contactLabel = new SimpleStringProperty();
    otherInformationLabel = new SimpleStringProperty();
    model.addListener("getMember", this::getMember);
  }

  private void getMember(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if(evt.getNewValue() instanceof Member)
      {
        Member member = (Member) evt.getNewValue();
        //usernameLabel.setValue(member.getUsername());
//        System.out.println("after: "+usernameLabel.getValue());
//        locationLabel.setValue(member.getAddressCity());
//        ratingLabel.setValue(String.valueOf(member.getAverageReview()));
//        addressLabel.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
//        contactLabel.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
//        otherInformationLabel.setValue(member.getOtherInformation());
      }
      });
  }

  public StringProperty getSearchField()
  {
    return searchField;
  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }

  public StringProperty getLocationLabel()
  {
    return locationLabel;
  }

  public StringProperty getRatingLabel()
  {
    return ratingLabel;
  }

  public StringProperty getAddressLabel()
  {
    return addressLabel;
  }

  public StringProperty getContactLabel()
  {
    return contactLabel;
  }

  public StringProperty getOtherInformationLabel()
  {
    return otherInformationLabel;
  }

  public String checkUserType(){
    return model.checkUserType();
  }

  public ArrayList<Rental> getRentalsOfMemberList(String username) throws RemoteException
  {
    System.out.println(usernameLabel.getValue());
    return model.getRentalsOfMemberList(username);
  }

  public void getRental(Object object) throws RemoteException
  {
    if(object instanceof StackPane){
      StackPane stackPane = (StackPane) object;
      if(stackPane.getChildren().get(0) instanceof InfoOverlay)
      {
        InfoOverlay infoOverlay = (InfoOverlay) stackPane.getChildren().get(0);
        if(infoOverlay.getContent() instanceof ImageView)
        {
          ImageView imageView = (ImageView) infoOverlay.getContent();
          for (int i = 0; i < getRentalsOfMemberList(usernameLabel.getValue()).size(); i++)
          {
            if(imageView.getId().equals(String.valueOf(getRentalsOfMemberList(usernameLabel.getValue()).get(i).getId())))
            {
              model.sendSelectedRental(getRentalsOfMemberList(usernameLabel.getValue()).get(i));
              break;
            }
          }
        }
      }
    }
  }

  public String getMemberUsername(){
    usernameLabel.setValue(model.getMemberUsername());
    System.out.println("View member profile username: "+usernameLabel.getValue());
    Member member = model.getMemberByUsername(model.getMemberUsername());
    locationLabel.setValue(member.getAddressCity());
    ratingLabel.setValue(String.valueOf(member.getAverageReview()));
    addressLabel.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
    contactLabel.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
    otherInformationLabel.setValue(member.getOtherInformation());
    return model.getMemberUsername();
  }

  public void setMemberUsername() {
    model.setMemberUsername(usernameLabel.getValue());
  }

  public boolean deleteAccount(){
    Member member = model.getMemberByUsername(model.getMemberUsername());
    return model.deleteMember(member);
  }
}
