package client.viewmodel.report_member;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReportMemberViewModel
{ private ShareItModel model;
  private final StringProperty usernameLabel;
  private final StringProperty commentaryArea;


  public ReportMemberViewModel(ShareItModel model)
  {
    this.model = model;
    usernameLabel = new SimpleStringProperty();
    commentaryArea = new SimpleStringProperty();

  }

  public StringProperty getUsernameLabel()
  {
    return usernameLabel;
  }



  public StringProperty getCommentaryArea()
  {
    return commentaryArea;
  }


}
