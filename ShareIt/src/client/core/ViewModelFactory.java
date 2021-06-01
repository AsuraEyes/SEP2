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
import client.viewmodel.search_for_member.SearchForMemberViewModel;
import client.viewmodel.search_for_rental.SearchForRentalViewModel;
import client.viewmodel.send_warning.SendWarningViewModel;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import client.viewmodel.view_rating.ViewRatingViewModel;
import client.viewmodel.view_rental.ViewRentalViewModel;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import client.viewmodel.welcome_page.WelcomePageViewModel;

import java.io.IOException;

/**
 * A class that creates a ViewModel object without having to specify the exact class of the object,.
 */
public class ViewModelFactory {
  private ModelFactory modelFactory;

  private LogInViewModel logInViewModel;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;
  private ChatWriteMessageViewModel chatWriteMessageViewModel;
  private AddRentalViewModel addRentalViewModel;
  private CreateAccountViewModel createAccountViewModel;
  private SearchForRentalViewModel searchForRentalViewModel;
  private SearchForMemberViewModel searchForMemberViewModel;
  private ViewRatingViewModel viewRatingViewModel;
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
  private SendWarningViewModel sendWarningViewModel;

  /**
   * Instantiates a new View model factory.
   *
   * @param modelFactory the model factory
   * @throws IOException
   */
  public ViewModelFactory(ModelFactory modelFactory) throws IOException {
    this.modelFactory = modelFactory;
    logInViewModel = new LogInViewModel(modelFactory.getShareItModel());
    chatReceivedMessagesViewModel = new ChatReceivedMessagesViewModel(
        modelFactory.getShareItModel());
    chatWriteMessageViewModel = new ChatWriteMessageViewModel(
        modelFactory.getShareItModel());
  }

  /**
   * Gets LogInViewModel when it needs to be accessed..
   *
   * @return returns the LogInViewModel
   * @throws IOException
   */
  public LogInViewModel getLogInViewModel() throws IOException {
    if(logInViewModel == null) {
      logInViewModel = new LogInViewModel(modelFactory.getShareItModel());
    }
    return logInViewModel;
  }

  /**
   * Gets ChatReceivedMessagesViewModel when it needs to be accessed..
   *
   * @return returns the ChatReceivedMessagesViewModel
   * @throws IOException
   */
  public ChatReceivedMessagesViewModel getChatReceivedMessagesViewModel() throws IOException {
    if(chatReceivedMessagesViewModel == null)
    {
      chatReceivedMessagesViewModel = new ChatReceivedMessagesViewModel(
          modelFactory.getShareItModel());
    }
    return chatReceivedMessagesViewModel;
  }

  /**
   * Gets ChatWriteMessagesViewModel when it needs to be accessed.
   *
   * @return returns the ChatWriteMessagesViewModel
   * @throws IOException
   */
  public ChatWriteMessageViewModel getChatWriteMessagesViewModel() throws IOException {
    if(chatWriteMessageViewModel == null)
    {
      chatWriteMessageViewModel = new ChatWriteMessageViewModel(
          modelFactory.getShareItModel());
    }
    return chatWriteMessageViewModel;
  }

  /**
   * Gets AddRentalViewModel when it needs to be accessed.
   *
   * @return returns the AddRentalViewModel
   * @throws IOException
   */
  public AddRentalViewModel getAddRentalViewModel() throws IOException {
    if (addRentalViewModel == null){
      addRentalViewModel = new AddRentalViewModel(modelFactory.getShareItModel());
    }
    return addRentalViewModel;
  }

  /**
   * Gets WelcomePageViewModel when it needs to be accessed..
   *
   * @return returns the WelcomePageViewModel
   * @throws IOException
   */
  public WelcomePageViewModel getWelcomePageViewModel() throws IOException {
    if (welcomePageViewModel == null){
      welcomePageViewModel = new WelcomePageViewModel(modelFactory.getShareItModel());
    }
    return welcomePageViewModel;
  }

  /**
   * Gets CreateAccountViewModel when it needs to be accessed.
   *
   * @return returns the CreateAccountViewModel
   * @throws IOException
   */
  public CreateAccountViewModel getCreateAccountViewModel() throws IOException {
    if (createAccountViewModel == null){
      createAccountViewModel = new CreateAccountViewModel(modelFactory.getShareItModel());
    }
    return createAccountViewModel;
  }

  /**
   * Gets SearchForRentalViewModel
   *
   * @return returns the SearchForRentalViewModel
   * @throws IOException
   */
  public SearchForRentalViewModel getSearchForRentalViewModel() throws IOException {
    if (searchForRentalViewModel == null){
      searchForRentalViewModel = new SearchForRentalViewModel(modelFactory.getShareItModel());
    }
    return searchForRentalViewModel;
  }

  /**
   * Gets SearchForMemberViewModel when it needs to be accessed.
   *
   * @return returns the SearchForMemberViewModel
   * @throws IOException
   */
  public SearchForMemberViewModel getSearchForMemberViewModel() throws IOException {
    if (searchForMemberViewModel == null){
      searchForMemberViewModel = new SearchForMemberViewModel(modelFactory.getShareItModel());
    }
    return searchForMemberViewModel;
  }

  /**
   * Gets ViewRatingViewModel when it needs to be accessed.
   *
   * @return returns the ViewRatingViewModel
   * @throws IOException
   */
  public ViewRatingViewModel getViewRatingViewModel() throws IOException {
    if (viewRatingViewModel == null){
      viewRatingViewModel = new ViewRatingViewModel(modelFactory.getShareItModel());
    }
    return viewRatingViewModel;
  }

  /**
   * Gets ViewReportedMemberViewModel when it needs to be accessed.
   *
   * @return  returns the ViewReportedMemberViewModel
   * @throws IOException
   */
  public ViewReportedMemberViewModel getViewReportedMemberViewModel() throws IOException {
    if (viewReportedMemberViewModel == null){
      viewReportedMemberViewModel = new ViewReportedMemberViewModel(modelFactory.getShareItModel());
    }
    return viewReportedMemberViewModel;
  }

  /**
   * Gets ViewReportedMemberListViewModel when it needs to be accessed.
   *
   * @return returns the ViewReportedMemberListViewModel
   * @throws IOException
   */
  public ViewReportedMemberListViewModel getViewReportedMemberListViewModel() throws IOException {
    if (viewReportedMemberListViewModel == null){
      viewReportedMemberListViewModel = new ViewReportedMemberListViewModel(modelFactory.getShareItModel());
    }
    return viewReportedMemberListViewModel;
  }

  /**
   * Gets MenuViewModel when it needs to be accessed.
   *
   * @return returns the MenuViewModel
   * @throws IOException
   */
  public MenuViewModel getMenuViewModel() throws IOException {
    if(menuViewModel == null) {
      menuViewModel = new MenuViewModel(modelFactory.getShareItModel());
    }
    return menuViewModel;
  }

  /**
   * Gets ViewMemberProfileViewModel when it needs to be accessed.
   *
   * @return returns the ViewMemberProfileViewModel
   * @throws IOException
   */
  public ViewMemberProfileViewModel getViewMemberProfileViewModel() throws IOException {
    if(viewMemberProfileViewModel == null) {
      viewMemberProfileViewModel = new ViewMemberProfileViewModel(modelFactory.getShareItModel());
    }
    return viewMemberProfileViewModel;
  }

  /**
   * Gets ViewRentalViewModel when it needs to be accessed.
   *
   * @return returns the ViewRentalViewModel
   * @throws IOException
   */
  public ViewRentalViewModel getViewRentalViewModel() throws IOException {
    if(viewRentalViewModel == null) {
      viewRentalViewModel = new ViewRentalViewModel(modelFactory.getShareItModel());
    }
    return viewRentalViewModel;
  }

  /**
   * Gets ReportMemberViewModel when it needs to be accessed.
   *
   * @return returns the ReportMemberViewModel
   * @throws IOException
   */
  public ReportMemberViewModel getReportMemberViewModel() throws IOException {
    if(reportMemberViewModel == null) {
      reportMemberViewModel = new ReportMemberViewModel(modelFactory.getShareItModel());
    }
    return reportMemberViewModel;
  }

  /**
   * Gets RateFeedbackViewModel when it needs to be accessed..
   *
   * @return returns the RateFeedbackViewModel
   * @throws IOException
   */
  public RateFeedbackViewModel getRateFeedbackViewModel() throws  IOException {
    if (rateFeedbackViewModel == null) {
      rateFeedbackViewModel = new RateFeedbackViewModel(modelFactory.getShareItModel());
    }
    return rateFeedbackViewModel;
  }

  /**
   * Gets EditRentalViewModel when it needs to be accessed.
   *
   * @return returns the EditRentalViewModel
   * @throws IOException
   */
  public EditRentalViewModel getEditRentalViewModel() throws IOException {
    if (editRentalViewModel == null){
      editRentalViewModel = new EditRentalViewModel(modelFactory.getShareItModel());
    }
    return editRentalViewModel;
  }

  /**
   * Gets ManageRentalsViewModel when it needs to be accessed..
   *
   * @return returns the ManageRentalsViewModel
   * @throws IOException
   */
  public ManageRentalsViewModel getManageRentalsViewModel() throws IOException {
    if (manageRentalsViewModel == null){
      manageRentalsViewModel = new ManageRentalsViewModel(modelFactory.getShareItModel());
    }
    return manageRentalsViewModel;
  }

  /**
   * Gets EditAccountViewModel when it needs to be accessed.
   *
   * @return returns the EditAccountViewModel
   * @throws IOException
   */
  public EditAccountViewModel getEditAccountViewModel() throws IOException {
    if (editAccountViewModel == null){
      editAccountViewModel = new EditAccountViewModel(modelFactory.getShareItModel());
    }
    return editAccountViewModel;
  }

  /**
   * Gets ManageAccountViewModel when it needs to be accessed..
   *
   * @return returns the ManageAccountViewModel
   * @throws IOException
   */
  public ManageAccountViewModel getManageAccountViewModel() throws IOException {
    if (manageAccountViewModel == null){
      manageAccountViewModel = new ManageAccountViewModel(modelFactory.getShareItModel());
    }
    return manageAccountViewModel;
  }

  /**
   * Gets SendWarningViewModel when it needs to be accessed.
   *
   * @return returns the SendWarningViewModel
   * @throws IOException
   */
  public SendWarningViewModel getSendWarningViewModel() throws IOException {
    if (sendWarningViewModel == null){
      sendWarningViewModel = new SendWarningViewModel(modelFactory.getShareItModel());
    }
    return sendWarningViewModel;
  }
}
