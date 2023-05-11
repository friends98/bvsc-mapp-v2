import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import security.CORSFilter;
import service.AccountService;
import service.AuthService;
import service.ElectionService;
import service.MeetingService;
import service.ShareHolderService;
import service.VotingService;
@ApplicationPath("api/v1")
public class MeetingApplication extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> setUrl = new HashSet<>();
		setUrl.add(VotingService.class);
		setUrl.add(ElectionService.class);
		setUrl.add(AccountService.class);
		setUrl.add(AuthService.class);
		setUrl.add(CORSFilter.class);
		setUrl.add(ShareHolderService.class);
		setUrl.add(MeetingService.class);

		return setUrl;
	}
}
