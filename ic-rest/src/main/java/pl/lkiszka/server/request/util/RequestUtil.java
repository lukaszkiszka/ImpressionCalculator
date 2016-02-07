package pl.lkiszka.server.request.util;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class RequestUtil {

	private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

	public static Long genereateUniqueNumber() {
		return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1);

	}

	public static URI createUri(UriInfo uriInfo, String requestId) {

		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		return ub.path(requestId).build();

	}

}
