/**
 * 
 */
package model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author quangdaongoc
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
	
	private String username;
	private String password;
}
