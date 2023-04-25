package security;

import java.util.Set;

import javax.security.enterprise.credential.Credential;

public class JWTCredential implements Credential{
	private final String principal;
	private final Set<String> authorities;
	
	public JWTCredential(String principal,Set<String> authorities) {
		this.principal=principal;
		this.authorities=authorities;
	}

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @return the authorities
	 */
	public Set<String> getAuthorities() {
		return authorities;
	}
	

}
