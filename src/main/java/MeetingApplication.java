import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import security.CORSFilter;
import service.AccountService;
import service.AuthService;
import service.CandidateService;
import service.CompanyService;
import service.ElectionService;
import service.FileService;
import service.MeetingResultService;
import service.MeetingService;
import service.SessionService;
import service.ShareHolderService;
import service.TransactionService;
import service.VotingService;
@ApplicationPath("api/v1")
public class MeetingApplication extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> setUrl = new HashSet<>();
		setUrl.add(VotingService.class);
		setUrl.add(CandidateService.class);
		setUrl.add(CompanyService.class);
		setUrl.add(ElectionService.class);
		setUrl.add(AccountService.class);
		setUrl.add(AuthService.class);
		setUrl.add(CORSFilter.class);
		setUrl.add(ShareHolderService.class);
		setUrl.add(MeetingService.class);
		setUrl.add(MeetingResultService.class);
		setUrl.add(TransactionService.class);
		setUrl.add(SessionService.class);
		setUrl.add(FileService.class);
		return setUrl;
	}
}
