package client.core;

import client.model.member.MemberModel;
import client.model.member.MemberModelManager;
import client.model.message.MessageModel;
import client.model.message.MessageModelManager;
import client.model.rental.RentalModel;
import client.model.rental.RentalModelManager;
/**
 * A class that creates a Model object, uses factory pattern.
 */
public class ModelFactory
{
  private final ClientFactory clientFactory;

  private RentalModel rentalModel;
  private MessageModel messageModel;
  private MemberModel memberModel;
  /**
   * Instantiates a new Model factory.
   *
   * @param clientFactory
   */
  public ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }
  /**
   * Creates a RentalModel if not created, otherwise returns it.
   *
   * @return the RentalModel
   */
  public RentalModel getRentalModel()
  {
    if (rentalModel == null)
    {
      rentalModel = new RentalModelManager(clientFactory.getClient(), this);
    }
    return rentalModel;
  }
  /**
   * Creates a MemberModel if not created, otherwise returns it.
   *
   * @return the MemberModel
   */
  public MemberModel getMemberModel()
  {
    if (memberModel == null)
    {
      memberModel = new MemberModelManager(clientFactory.getClient(), this);
    }
    return memberModel;
  }
  /**
   * Creates a MessageModel if not created, otherwise returns it.
   *
   * @return the MessageModel
   */
  public MessageModel getMessageModel()
  {
    if (messageModel == null)
    {
      messageModel = new MessageModelManager(clientFactory.getClient());
    }
    return messageModel;
  }
}
