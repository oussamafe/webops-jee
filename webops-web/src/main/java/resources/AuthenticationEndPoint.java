package resources;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Candidate;
import entities.Employe;
import entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import services.LoginServiceImpl;

@Path("login")
public class AuthenticationEndPoint {

	@Inject
	LoginServiceImpl loginService;

	final ObjectMapper mapper = new ObjectMapper();

	@Context
	private UriInfo uriInfo;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {

		try {
			User u = authenticate(username, password);
			if (u != null) {
				if (!u.isActive())
					return Response.status(Response.Status.NOT_ACCEPTABLE)
							.entity("Your account is inactive , Please check your Email and confirm your Account !")
							.build();
				else {
					String role = null;
					if (u instanceof Candidate) {
						role = "Candidate";
					} else if (u instanceof Employe) {
						Employe emp = (Employe) u;
						role = emp.getRole().toString();
						System.out.println(role);
					}
					String token = issueToken(Integer.toString(u.getId()), role);
					String refresh_token = issueRefreshToken(Integer.toString(u.getId()), role);
					loginService.setRefreshToken(u.getId(), refresh_token);
					Map<String,String> tokens = new HashMap<String, String>();
					tokens.put("access_token", token);
					tokens.put("refresh_token",refresh_token);
					return Response.ok(tokens).build();
				}

			}
			return Response.status(Response.Status.FORBIDDEN).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
		}
	}

	private User authenticate(String username, String password) {
		User u = loginService.login(username, password);
		return u;
	}

	private String issueToken(String username, String role) {
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		String jwtToken = Jwts.builder().setSubject(username).claim("Role", role)
				.setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(60L))).signWith(SignatureAlgorithm.HS512, key)
				.compact();
		return jwtToken;
	}

	private String issueRefreshToken(String username, String role) {
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		String jwtToken = Jwts.builder().setSubject(username).claim("Role", role)
				.setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMonths(2L))).signWith(SignatureAlgorithm.HS512, key)
				.compact();
		return jwtToken;
	}

	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public Claims decodeToken(String token, Key key) {
		try {
			Claims jwsMap = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			return mapper.convertValue(jwsMap, Claims.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@POST
	@Path("refresh")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response refreshToken(@FormParam("refresh_token") String token)
	{
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		Claims claim = decodeToken(token,key);
		String role = claim.get("Role", String.class);
		int idU = Integer.valueOf(claim.getSubject());
		if(loginService.checkRefreshToken(idU, token))
			
			return Response.ok(issueToken(String.valueOf(idU),role)).build();
					
		return Response.status(Response.Status.FORBIDDEN).build();
	}
}
