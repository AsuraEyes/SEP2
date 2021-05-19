package client.core;

import client.viewmodel.add_rental.AddRentalViewModel;
import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import client.viewmodel.chat_write_message.ChatWriteMessageViewModel;
import client.viewmodel.create_account.CreateAccountViewModel;
import client.viewmodel.log_in.LogInViewModel;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.rate_feedback.RateFeedbackViewModel;
import client.viewmodel.seatch_for_rental.SearchForRentalViewModel;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import client.viewmodel.view_rating.ViewRatingViewModel;
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
  private ViewRatingViewModel viewRatingViewModel;
  private ViewRatingFullViewModel viewRatingFullViewModel;
  private ViewReportedMemberViewModel viewReportedMemberViewModel;
  private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
  private WelcomePageViewModel welcomePageViewModel;
  private MenuViewModel menuViewModel;
  private ViewMemberProfileViewModel viewMemberProfileViewModel;
  private ViewRentalViewModel viewRentalViewModel;
  private RateFeedbackViewModel rateFeedbackViewModel;

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

  public AddRentalViewModel getAddRentalViewModel() throws SQLException, IOException {
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

  public ViewRatingViewModel getViewRatingViewModel(){
    if (viewRatingViewModel == null){
      viewRatingViewModel = new ViewRatingViewModel();
    }
    return viewRatingViewModel;
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
  public RateFeedbackViewModel getRateFeedbackViewModel() throws SQLException, IOException
  {
    if (rateFeedbackViewModel == null)
    {
      rateFeedbackViewModel = new RateFeedbackViewModel(modelFactory.getShareItModel());
    }
    return rateFeedbackViewModel;
  }
}
