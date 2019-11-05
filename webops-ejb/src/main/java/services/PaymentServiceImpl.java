package services;

 
import java.util.HashMap;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer; 

import interfaces.PaymentServiceRemote;
@Stateless
@LocalBean
public class PaymentServiceImpl implements PaymentServiceRemote {

	
	public void creatCustomer() throws StripeException
	{
		Stripe.apiKey = "sk_test_eT94yR5nZz3HL2pMAu1V70SQ00QHcBadXU";
		Map<String, Object> customerParam = new HashMap<String, Object>();
		customerParam.put("email", "firas.gazzeh@gmail.com");
		@SuppressWarnings("unused")
		Customer newCustomer =Customer.create(customerParam);
	}
	@Override
	public void createCustomer() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addCard() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void makePayment() {
		// TODO Auto-generated method stub
		
	}
}
