package utilites;

import java.io.IOException;
import java.security.Key;

import javax.annotation.Priority;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@SecuredProjectManager
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthProjectManagerFilter implements ContainerRequestFilter {

	final ObjectMapper mapper = new ObjectMapper();

	private static final String AUTHENTICATION_SCHEME = "Bearer";
	ContainerRequestContext requestContext;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}

		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
		try {

			validateToken(token);
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {

		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME).build());
	}

	private void validateToken(String token) {

		try {

			String keyString = "simplekey";
			Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
			String role = decodeToken(token, key).get("Role", String.class);
			if (!role.equals("Project_Manager"))
				(this.requestContext).abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		} catch (Exception e) {
			System.out.println("#### invalid token : " + token);
			(this.requestContext).abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
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
}
