package com.project.scheduledelevopproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter{

    private static final String [] whiteList = {"/login","/signup"};

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 ServletRequest,Response -> HttpServletRequest,Response 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 요청 URI
        String requestURI = httpRequest.getRequestURI();

        // 로그인 (인증)이 필요한지 체크
        if(!isWhiteList(requestURI)){
            // getSession(false) : 세션이 존재하지 않을 경우 ->  null 반환 -> 로그인이 되지 않았다는 경우
            HttpSession session = httpRequest.getSession(false);

            if(session == null){
                throw new RuntimeException("로그인 해주세요");
            }
        }

        filterChain.doFilter(httpRequest,httpResponse);


    }

    private Boolean isWhiteList(String requestURI){
        // PatternMatchUtils.simpleMatch(리스트, 문자열) -> 리스트안에 문자열이 존재하는지 확인 -> 존재 시 true
        return PatternMatchUtils.simpleMatch(whiteList,requestURI);
    }

}
