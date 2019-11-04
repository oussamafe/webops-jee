package interfaces;

import javax.ejb.Remote;


@Remote
public interface PaymentServiceRemote { 
	//STRIPE API
public void createCustomer();
public void addCard();
public void makePayment();

}
