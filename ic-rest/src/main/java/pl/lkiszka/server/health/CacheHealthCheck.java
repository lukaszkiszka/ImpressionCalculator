package pl.lkiszka.server.health;

import com.codahale.metrics.health.HealthCheck;

import pl.lkiszka.server.constants.Constants;
import pl.lkiszka.server.request.util.CacheUtil;

/**
 * Simple HealthCheck to check cache size
 * 
 * @author lukasz
 *
 */
public class CacheHealthCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {

		if (CacheUtil.results.size() < Constants.CACHE_SIZE) {
			return Result.healthy();
		} else {
			return Result.unhealthy("Cache is full");
		}

	}

}
