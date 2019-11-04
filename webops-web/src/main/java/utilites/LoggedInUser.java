package utilites;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class LoggedInUser {

	@Context
	private HttpHeaders headers;
	
	//static ContainerRequestContext requestContext;
	
	public int getLoggedinUser(String authorizationHeader)
	{
		
		//String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		//String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		String token = authorizationHeader.substring("Bearer".length()).trim();
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		int idU = Integer.valueOf(claims.getSubject());
		if(idU !=0)
			return idU;
		return 0;
	}
	
	public LoggedInUser(){}
	
}
