package client.core;

import client.viewmodel.add_rental.AddRentalViewModel;
import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import client.viewmodel.chat_write_message.ChatWriteMessageViewModel;
import client.viewmodel.create_account.CreateAccountViewModel;
import client.viewmodel.edit_account.EditAccountViewModel;
import client.viewmodel.edit_rental.EditRentalViewModel;
import client.viewmodel.log_in.LogInViewModel;
import client.viewmodel.manage_account.ManageAccountViewModel;
import client.viewmodel.manage_rentals.ManageRentalsViewModel;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.rate_feedback.RateFeedbackViewModel;
import client.viewmodel.report_member.ReportMemberViewModel;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import client.viewmodel.view_rating_full.ViewRatingFullViewModel;
import client.viewmodel.view_rental.ViewRentalViewModel;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import client.viewmodel.welcome_page.WelcomePageViewModel;

import java.io.IOException;
import java.sql.SQLException;

public class ViewModelFactory
{
  private ModelFactory modelFactory;

  private LogInViewModel logInViewModel;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;
  private ChatWriteMessageViewModel chatWriteMessageViewModel;
  private AddRentalViewModel addRentalViewModel;
  private CreateAccountViewModel createAccountViewModel;
  private SearchForRentalViewModel searchForRentalViewModel;
  private ViewRatingFullViewModel viewRatingFullViewModel;
  private ViewReportedMemberViewModel viewReportedMemberViewModel;
  private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
  private WelcomePageViewModel welcomePageViewModel;
  private MenuViewModel menuViewModel;
  private ViewMemberProfileViewModel viewMemberProfileViewModel;
  private ViewRentalViewModel viewRentalViewModel;
  private EditRentalViewModel editRentalViewModel;
  private ManageRentalsViewModel manageRentalsViewModel;
  private EditAccountViewModel editAccountViewModel;
  private RateFeedbackViewModel rateFeedbackViewModel;
  private ReportMemberViewModel reportMemberViewModel;
  private ManageAccountViewModel manageAccountViewModel;

  public ViewModelFactory(ModelFactory modelFactory) throws IOException
  {
    this.modelFactory = modelFactory;
    logInViewModel = new LogInViewModel(modelFactory.getShareItModel());
    chatReceivedMessagesViewModel = new ChatReceivedMessagesViewModel();
    chatWriteMessageViewModel = new ChatWriteMessageViewModel();
  }
  public LogInViewModel getLogInViewModel() throws IOException
  {
    if(logInViewModel == null)
    {
      logInViewModel = new LogInViewModel(modelFactory.getShareItModel());
    }
    return logInViewModel;
  }

  public ChatReceivedMessagesViewModel getChatReceivedMessagesViewModel()
  {
    if(chatReceivedMessagesViewModel == null)
    {
      chatReceivedMessagesViewModel = new ChatReceivedMessagesViewModel();
    }
    return chatReceivedMessagesViewModel;
  }


  public ChatWriteMessageViewModel getChatWriteMessagesViewModel()
  {
    if(chatWriteMessageViewModel == null)
    {
      chatWriteMessageViewModel = new ChatWriteMessageViewModel();
    }
    return chatWriteMessageViewModel;
  }

  public AddRentalViewModel getAddRentalViewModel() throws IOException {
    if (addRentalViewModel == null){
      addRentalViewModel = new AddRentalViewModel(modelFactory.getShareItModel());
    }
    return addRentalViewModel;
  }

  public WelcomePageViewModel getWelcomePageViewModel() throws IOException {
    if (welcomePageViewModel == null){
      //System.out.println(modelFactory.getShareItModel());
      welcomePageViewModel = new WelcomePageViewModel(modelFactory.getShareItModel());
    }
    return welcomePageViewModel;
  }

  public CreateAccountViewModel getCreateAccountViewModel() throws IOException {
    if (createAccountViewModel == null){
      createAccountViewModel = new CreateAccountViewModel(modelFactory.getShareItModel());
    }
    return createAccountViewModel;
  }

  public SearchForRentalViewModel getSearchForRentalViewModel() throws IOException {
    if (searchForRentalViewModel == null){
      searchForRentalViewModel = new SearchForRentalViewModel(modelFactory.getShareItModel());
    }
    return searchForRentalViewModel;
  }

  public ViewRatingFullViewModel getViewRatingFullViewModel() {
    if (viewRatingFullViewModel == null){
      viewRatingFullViewModel = new ViewRatingFullViewModel();
    }
    return viewRatingFullViewModel;
  }

  public ViewReportedMemberViewModel getViewReportedMemberViewModel() {
    if (viewReportedMemberViewModel == null){
      viewReportedMemberViewModel = new ViewReportedMemberViewModel();
    }
    return viewReportedMemberViewModel;
  }

  public ViewReportedMemberListViewModel getViewReportedMemberListViewModel() {
    if (viewReportedMemberListViewModel == null){
      viewReportedMemberListViewModel = new ViewReportedMemberListViewModel();
    }
    return viewReportedMemberListViewModel;
  }

  public MenuViewModel getMenuViewModel() throws IOException {
    if(menuViewModel == null)
    {
      menuViewModel = new MenuViewModel(modelFactory.getShareItModel());
    }
    return menuViewModel;
  }

  public ViewMemberProfileViewModel getViewMemberProfileViewModel() throws IOException {
    if(viewMemberProfileViewModel == null)
    {
      viewMemberProfileViewModel = new ViewMemberProfileViewModel(modelFactory.getShareItModel());
    }
    return viewMemberProfileViewModel;
  }

  public ViewRentalViewModel getViewRentalViewModel() throws IOException
  {
    if(viewRentalViewModel == null)
    {
      viewRentalViewModel = new ViewRentalViewModel(modelFactory.getShareItModel());
    }
    return viewRentalViewModel;
  }

  public ReportMemberViewModel getReportMemberViewModel() throws IOException
  {
    if(reportMemberViewModel == null)
    {
      reportMemberViewModel = new ReportMemberViewModel(modelFactory.getShareItModel());
    }
    return reportMemberViewModel;
  }

  public RateFeedbackViewModel getRateFeedbackViewModel() throws SQLException, IOException
  {
    if (rateFeedbackViewModel == null)
    {
      rateFeedbackViewModel = new RateFeedbackViewModel(modelFactory.getShareItModel());
    }
    return rateFeedbackViewModel;
  }

  public EditRentalViewModel getEditRentalViewModel() throws IOException {
    if (editRentalViewModel == null){
      editRentalViewModel = new EditRentalViewModel(modelFactory.getShareItModel());
    }
    return editRentalViewModel;
  }

  public ManageRentalsViewModel getManageRentalsViewModel() throws IOException {
    if (manageRentalsViewModel == null){
      manageRentalsViewModel = new ManageRentalsViewModel(modelFactory.getShareItModel());
    }
    return manageRentalsViewModel;
  }

  public EditAccountViewModel getEditAccountViewModel() throws IOException {
    if (editAccountViewModel == null){
      editAccountViewModel = new EditAccountViewModel(modelFactory.getShareItModel());
    }
    return editAccountViewModel;
  }

  public ManageAccountViewModel getManageAccountViewModel() throws IOException {
    if (manageAccountViewModel == null){
      manageAccountViewModel = new ManageAccountViewModel(modelFactory.getShareItModel());
    }
    return manageAccountViewModel;
  }
}
