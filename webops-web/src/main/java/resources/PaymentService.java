package resources;

 
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped; 
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer; 
import com.stripe.model.Token;
import services.PaymentServiceImpl;

@RequestScoped
@Path("payment")
public class PaymentService {
	PaymentServiceImpl pay;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCustomer() throws StripeException {
		
		Stripe.apiKey = "sk_test_eT94yR5nZz3HL2pMAu1V70SQ00QHcBadXU";
		Map<String, Object> customerParam = new HashMap<String, Object>();
		customerParam.put("email", "firas.gazzeh@gmail.com");
		Customer newCustomer =Customer.create(customerParam);
		return Response.status(Status.CREATED).entity("payment affected Successfully "+newCustomer).build();
	}
	
	@POST
	@Path("addCard")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCreditCard() throws StripeException {
		
		Stripe.apiKey = "sk_test_eT94yR5nZz3HL2pMAu1V70SQ00QHcBadXU";
		Customer customer=Customer.retrieve("cus_G3MX3ORbAykYoQ");
		Map<String, Object> cardParam = new HashMap<String, Object>();
		cardParam.put("number", "4242424242424242");
		cardParam.put("exp_month", "09");
		cardParam.put("exp_year", "2022");
		cardParam.put("cvc", "123");
		
		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);
		Token token=Token.create(tokenParam);
		
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());
		
		customer.getSources().create(source);
		return Response.status(Status.CREATED).entity("credit card added Successfully ").build();
	}
	
	@POST
	@Path("makePay")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePayment() throws StripeException {
		
		Stripe.apiKey = "sk_test_eT94yR5nZz3HL2pMAu1V70SQ00QHcBadXU";
		Customer customer=Customer.retrieve("cus_G3MX3ORbAykYoQ");
		Map<String, Object> paymentParam = new HashMap<String, Object>();
		paymentParam.put("amount", 1000);
		paymentParam.put("currency", "eur");
		paymentParam.put("customer", customer.getId());
		
		Charge.create(paymentParam);
		return Response.status(Status.CREATED).entity("Payment affected Successfully ").build();
	}
}
