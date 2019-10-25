package com.gf.company.security.config;


import com.gf.company.security.phonecode.PhoneCodeAuthenticationProcessingFilter;
import com.gf.company.security.phonecode.PhoneCodeAuthenticationProvider;
import com.gf.company.security.phonepwd.EurekaUserPasswordEncoder;
import com.gf.company.security.phonepwd.NamePwdAuthenticationProvider;
import com.gf.company.security.phonepwd.PasswordAuthenticationProcessingFilter;
import com.gf.company.security.service.*;
import com.gf.company.security.tokencode.TokenCodeFilter;
import com.gf.company.utils.UtilInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/25
 */
@SuppressWarnings("ALL")
@Slf4j
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
public class ShuleSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //用户登录成功后处理事件
    @Autowired
    private ShuleAuthenticationSuccessHandler shuleAuthenticationSuccessHandler;
    //用户登录成功后加载用户权限列表
    @Autowired
    private ShuleUserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    //解决认证过的用户访问无权限资源时的异常
    @Autowired
    private ShuleAccessDeniedHandler accessDeniedHandler;
    //匿名用户访问无权限资源时的异常
    @Autowired
    private ShuleAuthenticationEntryPoint entryPoint;
    //用户登录失败后处理事件
    @Autowired
    private ShuleAuthenticationFailureHandler failureHandler;
    //全局token验证
    @Autowired
    private TokenCodeFilter tokenCodeFilter;
    //决策器，用户请求接口权限对比判断
    @Autowired
    private ShuleAccessDecisionManager accessDecisionManager;
    //程序启动时取出所有的资源权限列表，供决策器使用
    @Autowired
    private ShuleFilterInvocationSecurityMetadataSource metadataSource;
    @Autowired
    private UtilInfo utilInfo;
    //用户登录，密码加密验证
    @Autowired
    private EurekaUserPasswordEncoder encoder;


    @Bean( name="authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().accessDecisionManager(accessDecisionManager);
        http.addFilterAt(passwordProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(phoneCodeProcessingFilter(),UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(tokenCodeFilter,UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(// 加入数据库用户验证拦截器
                new ProjectFilterSecurityInterceptor(metadataSource,
                        accessDecisionManager, authenticationManager), FilterSecurityInterceptor.class);
        http.logout().logoutSuccessUrl("/apis/company/loginout");
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(entryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(passwordProvider()).authenticationProvider(phoneCodeProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/**/*.css",
                "/**/*.js",
                "/**/*.html",
                "/*.html","/swagger*/**","/webjars/**","/v2/**","/csrf","/","/wsh/**","/app/**","/queue/**","/topic/**",
                "/favicon.ico");
    }
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.addAllowedOrigin("*");// 允许向该服务器提交请求的URI，*表示全部允许。。这里尽量限制来源域，比如http://xxxx:8080 ,以降低安全风险。。
        config.addAllowedHeader("*");// 允许访问的头信息,*表示全部
        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许，也可以单独设置GET、PUT等
    /*    config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");// 允许Get的请求方法
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");*/
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    public PasswordAuthenticationProcessingFilter passwordProcessingFilter() throws Exception{
        PasswordAuthenticationProcessingFilter filter = new PasswordAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(shuleAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

    public PhoneCodeAuthenticationProcessingFilter phoneCodeProcessingFilter() throws Exception{
        PhoneCodeAuthenticationProcessingFilter filter = new PhoneCodeAuthenticationProcessingFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(shuleAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

    public NamePwdAuthenticationProvider passwordProvider(){
        NamePwdAuthenticationProvider provider = new NamePwdAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(encoder);
        return provider;
    }

    public PhoneCodeAuthenticationProvider phoneCodeProvider(){
        PhoneCodeAuthenticationProvider provider = new PhoneCodeAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setUtilInfo(utilInfo);
        return provider;
    }
}

class ProjectFilterSecurityInterceptor extends FilterSecurityInterceptor {
    public ProjectFilterSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource,
                                            AccessDecisionManager accessDecisionManager,
                                            AuthenticationManager authenticationManager) {
        this.setSecurityMetadataSource(securityMetadataSource);
        this.setAccessDecisionManager(accessDecisionManager);
        this.setAuthenticationManager(authenticationManager);
    }
}
