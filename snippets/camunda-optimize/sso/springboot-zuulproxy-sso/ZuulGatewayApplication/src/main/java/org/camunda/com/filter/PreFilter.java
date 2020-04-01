package org.camunda.com.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * Example Pre-filter implementation for adding a header named user and granting authorization within Optimize plugin
 * @author NormanLüring
 *
 */
public class PreFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// Check here if filter should be applied by confirming token header is present
		// i.e check if RequestContext.getCurrentContext().getRequest().getHeader("Authorization") is present
		// forward authorization to run() by returning true.
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		// Determine authorization by reading token and adding headers which will be picked up by Optimize
		// this sample just adds header named "user" and Optimize Plugin will automatically authorize this User.
		// This is not production ready and is just an example. User authorization is mandatory!
		RequestContext context = RequestContext.getCurrentContext();
		context.addZuulRequestHeader("user", "demo");
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
