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
/**
 * A class that creates a ViewModel object, uses factory pattern.
 */
public class ViewModelFactory
{
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
   */
  public ViewModelFactory(ModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
  }
  /**
   * Gets LogInViewModel when it needs to be accessed.
   *
   * @return returns the LogInViewModel
   */
  public LogInViewModel getLogInViewModel()
  {
    if (logInViewModel == null)
    {
      logInViewModel = new LogInViewModel(modelFactory.getMemberModel());
    }
    return logInViewModel;
  }
  /**
   * Gets ChatReceivedMessagesViewModel when it needs to be accessed.
   *
   * @return returns the ChatReceivedMessagesViewModel
   */
  public ChatReceivedMessagesViewModel getChatReceivedMessagesViewModel()
  {
    if (chatReceivedMessagesViewModel == null)
    {
      chatReceivedMessagesViewModel = new ChatReceivedMessagesViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return chatReceivedMessagesViewModel;
  }
  /**
   * Gets ChatWriteMessagesViewModel when it needs to be accessed.
   *
   * @return returns the ChatWriteMessagesViewModel
   */
  public ChatWriteMessageViewModel getChatWriteMessagesViewModel()
  {
    if (chatWriteMessageViewModel == null)
    {
      chatWriteMessageViewModel = new ChatWriteMessageViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return chatWriteMessageViewModel;
  }
  /**
   * Gets AddRentalViewModel when it needs to be accessed.
   *
   * @return returns the AddRentalViewModel
   */
  public AddRentalViewModel getAddRentalViewModel()
  {
    if (addRentalViewModel == null)
    {
      addRentalViewModel = new AddRentalViewModel(modelFactory.getRentalModel(),
          modelFactory.getMemberModel());
    }
    return addRentalViewModel;
  }
  /**
   * Gets WelcomePageViewModel when it needs to be accessed.
   *
   * @return returns the WelcomePageViewModel
   */
  public WelcomePageViewModel getWelcomePageViewModel()
  {
    if (welcomePageViewModel == null)
    {
      welcomePageViewModel = new WelcomePageViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return welcomePageViewModel;
  }
  /**
   * Gets CreateAccountViewModel when it needs to be accessed.
   *
   * @return returns the CreateAccountViewModel
   */
  public CreateAccountViewModel getCreateAccountViewModel()
  {
    if (createAccountViewModel == null)
    {
      createAccountViewModel = new CreateAccountViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return createAccountViewModel;
  }
  /**
   * Gets SearchForRentalViewModel when it needs to be accessed.
   *
   * @return returns the SearchForRentalViewModel
   */
  public SearchForRentalViewModel getSearchForRentalViewModel()
  {
    if (searchForRentalViewModel == null)
    {
      searchForRentalViewModel = new SearchForRentalViewModel(
          modelFactory.getRentalModel(), modelFactory.getMessageModel());
    }
    return searchForRentalViewModel;
  }
  /**
   * Gets SearchForMemberViewModel when it needs to be accessed.
   *
   * @return returns the SearchForMemberViewModel
   */
  public SearchForMemberViewModel getSearchForMemberViewModel()
  {
    if (searchForMemberViewModel == null)
    {
      searchForMemberViewModel = new SearchForMemberViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel(),
          modelFactory.getMessageModel());
    }
    return searchForMemberViewModel;
  }
  /**
   * Gets ViewRatingViewModel when it needs to be accessed.
   *
   * @return returns the ViewRatingViewModel
   */
  public ViewRatingViewModel getViewRatingViewModel()
  {
    if (viewRatingViewModel == null)
    {
      viewRatingViewModel = new ViewRatingViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return viewRatingViewModel;
  }
  /**
   * Gets ViewReportedMemberViewModel when it needs to be accessed.
   *
   * @return  returns the ViewReportedMemberViewModel
   */
  public ViewReportedMemberViewModel getViewReportedMemberViewModel()
  {
    if (viewReportedMemberViewModel == null)
    {
      viewReportedMemberViewModel = new ViewReportedMemberViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return viewReportedMemberViewModel;
  }
  /**
   * Gets ViewReportedMemberListViewModel when it needs to be accessed.
   *
   * @return returns the ViewReportedMemberListViewModel
   */
  public ViewReportedMemberListViewModel getViewReportedMemberListViewModel()
  {
    if (viewReportedMemberListViewModel == null)
    {
      viewReportedMemberListViewModel = new ViewReportedMemberListViewModel(
          modelFactory.getMemberModel());
    }
    return viewReportedMemberListViewModel;
  }
  /**
   * Gets MenuViewModel when it needs to be accessed.
   *
   * @return returns the MenuViewModel
   */
  public MenuViewModel getMenuViewModel()
  {
    if (menuViewModel == null)
    {
      menuViewModel = new MenuViewModel(modelFactory.getRentalModel(),
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return menuViewModel;
  }
  /**
   * Gets ViewMemberProfileViewModel when it needs to be accessed.
   *
   * @return returns the ViewMemberProfileViewModel
   */
  public ViewMemberProfileViewModel getViewMemberProfileViewModel()
  {
    if (viewMemberProfileViewModel == null)
    {
      viewMemberProfileViewModel = new ViewMemberProfileViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return viewMemberProfileViewModel;
  }
  /**
   * Gets ViewRentalViewModel when it needs to be accessed.
   *
   * @return returns the ViewRentalViewModel
   */
  public ViewRentalViewModel getViewRentalViewModel()
  {
    if (viewRentalViewModel == null)
    {
      viewRentalViewModel = new ViewRentalViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return viewRentalViewModel;
  }
  /**
   * Gets ReportMemberViewModel when it needs to be accessed.
   *
   * @return returns the ReportMemberViewModel
   */
  public ReportMemberViewModel getReportMemberViewModel()
  {
    if (reportMemberViewModel == null)
    {
      reportMemberViewModel = new ReportMemberViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return reportMemberViewModel;
  }
  /**
   * Gets RateFeedbackViewModel when it needs to be accessed.
   *
   * @return returns the RateFeedbackViewModel
   */
  public RateFeedbackViewModel getRateFeedbackViewModel()
  {
    if (rateFeedbackViewModel == null)
    {
      rateFeedbackViewModel = new RateFeedbackViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return rateFeedbackViewModel;
  }
  /**
   * Gets EditRentalViewModel when it needs to be accessed.
   *
   * @return returns the EditRentalViewModel
   */
  public EditRentalViewModel getEditRentalViewModel()
  {
    if (editRentalViewModel == null)
    {
      editRentalViewModel = new EditRentalViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return editRentalViewModel;
  }
  /**
   * Gets ManageRentalsViewModel when it needs to be accessed.
   *
   * @return returns the ManageRentalsViewModel
   */
  public ManageRentalsViewModel getManageRentalsViewModel()
  {
    if (manageRentalsViewModel == null)
    {
      manageRentalsViewModel = new ManageRentalsViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return manageRentalsViewModel;
  }
  /**
   * Gets EditAccountViewModel when it needs to be accessed.
   *
   * @return returns the EditAccountViewModel
   */
  public EditAccountViewModel getEditAccountViewModel()
  {
    if (editAccountViewModel == null)
    {
      editAccountViewModel = new EditAccountViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return editAccountViewModel;
  }
  /**
   * Gets ManageAccountViewModel when it needs to be accessed..
   *
   * @return returns the ManageAccountViewModel
   */
  public ManageAccountViewModel getManageAccountViewModel()
  {
    if (manageAccountViewModel == null)
    {
      manageAccountViewModel = new ManageAccountViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return manageAccountViewModel;
  }
  /**
   * Gets SendWarningViewModel when it needs to be accessed.
   *
   * @return returns the SendWarningViewModel
   */
  public SendWarningViewModel getSendWarningViewModel()
  {
    if (sendWarningViewModel == null)
    {
      sendWarningViewModel = new SendWarningViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return sendWarningViewModel;
  }
}
