import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import service.AccountService;
import service.AuthService;
@ApplicationPath("api/v1")
public class MeetingApplication extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> setUrl=new HashSet<>();
		setUrl.add(AccountService.class);
		setUrl.add(AuthService.class);
		return setUrl;
	}
}
