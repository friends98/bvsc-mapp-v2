package security;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;


@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext CReq, ContainerResponseContext CRes) throws IOException {
		CRes.getHeaders().add("Access-Control-Allow-Origin", "*");
		CRes.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		CRes.getHeaders().add("Access-Control-Allow-Credentials", "true");
		CRes.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		CRes.getHeaders().add("Access-Control-Max-Age", "1209600");
	}

}
