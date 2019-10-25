package com.gf.company.security.tokencode;


import com.alibaba.fastjson.JSON;
import com.gf.api.entity.ShuleUserDetails;
import com.gf.company.utils.UtilInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/30
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class TokenCodeFilter extends OncePerRequestFilter {

    @Autowired
    private UtilInfo utilInfo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        log.debug("@@@@@@@@@@@@@  once per request filter uri -->"+uri);
        if(antMatch(uri)){
            chain.doFilter(request, response);
        }else {

            MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);
            String token = obtainToken(requestWrapper.getBodyString());
            if (!StringUtils.isEmpty(token)) {
                log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@  once per request filter get token -->"+token);
                Authentication authentication = utilInfo.getUserAuthenticationByToken(token);

                if (authentication != null) {
                    ShuleUserDetails userDetails = (ShuleUserDetails) authentication.getPrincipal();

                    if (userDetails != null && !StringUtils.isEmpty(userDetails.getToken()) && userDetails.getToken().equals(token)) {

                        UsernamePasswordAuthenticationToken userAuthentication =
                                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                        userAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(requestWrapper));
                        //设置为已登录
                        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                    } else {
                        SecurityContextHolder.clearContext();
                    }
                }else {
                    SecurityContextHolder.clearContext();
                }
            }else {
                SecurityContextHolder.clearContext();
            }
            if (requestWrapper == null) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(requestWrapper, response);
            }
        }

    }

    private String obtainToken(String body){
        Map map = JSON.parseObject(body, Map.class);
        String token = (String) map.get("token");
        log.debug("@@@@@@@@@   botain body -->"+body);
        return token;
    }
    /**
     * 不需要过滤的uri判断
     * @params [uri]
     * @return boolean
     * @version 1.0
     */
    private boolean antMatch(String uri){

        String[] uris = new String[]{"/**/*.css",
                "/**/*.js",
                "/**/*.html",
                "/*.html","/swagger*/**","/webjars/**","/v2/**",
                "/favicon.ico"};
        PathMatcher matcher = new AntPathMatcher();
        boolean result = false;
        for(String match:uris){
            if(matcher.match(match,uri)){
                result = true;
                return result;
            }
        }
        return result;
    }
/*    protected String  obtainToken(HttpServletRequest request) {
        InputStreamReader reader = null;
        Map map = null;
        String token = null;
        try {


            reader = new InputStreamReader(request.getInputStream(), "UTF-8");

            char[] buff = new char[2048];
            int length = 0;
            String result = null;
            while ((length = reader.read(buff)) != -1) {
                result = new String(buff, 0, length);
            }

            map = JSON.parseObject(result, Map.class);
            token = (String) map.get("token");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return token;
        }
    }*/
}
