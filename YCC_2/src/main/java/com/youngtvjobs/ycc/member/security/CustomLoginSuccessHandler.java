package com.youngtvjobs.ycc.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.extern.log4j.Log4j;

@Log4j

public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	private final RequestCache requestCache = new HttpSessionRequestCache();
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


	
	//	로그인 실패 후 성공 시 남아있는 에러 세션 제거 메서드 
	private void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		}
	}

	//세션에 저장한 이전 페이지 uri를 사용하여 로그인 성공 시 해당 uri로 redirect
	@Override
	// 로그인 성공
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		clearSession(request);
		// 사용자가 권한이 없는 페이지에 접근하였을 때 시큐리티가 인터셉트하여 uri를 SaveRequest에 저장
		// ex) 사용자가 로그인 하지 않은 상태에서 권한이 없는 페이지로 이동했을 때 저장됨 
		// ex) 사용자가 직접 로그인 버튼을 눌렀을 때는 저장되지 않음 (savedRequest = null) -> 시큐리티가 해당 작업을 인터셉트하지 않았음
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		
		if (prevPage != null) {
			request.getSession().removeAttribute("prevPage");
		}

		String uri = "/";
		
		//savedRequest의 존재여부에 따라 redirect 할 uri를 결정
		if (savedRequest != null) {
			uri = savedRequest.getRedirectUrl();
		} else if (prevPage != null) {
			if (prevPage.contains("ycc/login")) {
				uri = "/";

			} else {
				uri = prevPage;
			}
		}

		redirectStrategy.sendRedirect(request, response, uri);
	}

}
