package pl.lkiszka.server.request.util;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import pl.lkiszka.server.constants.Constants;

public class CacheUtil {

	public static Cache<String, CalculationResult> results = CacheBuilder.newBuilder().maximumSize(Constants.CACHE_SIZE)
			.expireAfterWrite(Constants.CACHE_EXPIRE_AFTER_WRITE_MINUTE, TimeUnit.MINUTES).build();

}
