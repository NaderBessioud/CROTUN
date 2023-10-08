package tn.esprit.CROTUN.Audit;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import tn.esprit.CROTUN.Entities.UserActivity;
import tn.esprit.CROTUN.Services.ActivityService;
import tn.esprit.CROTUN.Utils.HttpRequestResponseUtils;

public class AcitivityLogger implements HandlerInterceptor {
	
	@Autowired
	private ActivityService activityService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final String ip = HttpRequestResponseUtils.getClientIpAddress();
		final String url = HttpRequestResponseUtils.getRequestUrl();
		final String page = HttpRequestResponseUtils.getRequestUri();
		final String refererPage = HttpRequestResponseUtils.getRefererPage();
		final String queryString = HttpRequestResponseUtils.getPageQueryString();
		final String userAgent = HttpRequestResponseUtils.getUserAgent();
		final String requestMethod = HttpRequestResponseUtils.getRequestMethod();
		final LocalDateTime timestamp = LocalDateTime.now();

		UserActivity visitor = new UserActivity();
		visitor.setUser(HttpRequestResponseUtils.getLoggedInUser());
		visitor.setIp(ip);
		visitor.setMethod(requestMethod);
		visitor.setUrl(url);
		visitor.setPage(page);
		if(url.contains("/confirmAgentDevice") || url.contains("confirmCustomerDevice") || url.contains("confirmInvestorDevice")
				|| url.contains("confirmManagerDevice")){
			System.out.print("hana houni");
			visitor.setQueryString("");
		}
		else {
			visitor.setQueryString(queryString);
		}
		
		visitor.setRefererPage(refererPage);
		visitor.setUserAgent(userAgent);
		visitor.setLoggedTime(timestamp);
		visitor.setUniqueVisit(true);
		

		activityService.saveVisitorInfo(visitor);

		return true;
	}
	
	

}
