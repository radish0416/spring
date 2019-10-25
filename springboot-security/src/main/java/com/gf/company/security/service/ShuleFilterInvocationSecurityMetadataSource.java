package com.gf.company.security.service;



import com.gf.api.AuthEnums;
import com.gf.api.MicroServiceList;
import com.gf.api.entity.RoleResourceInfo;
import com.gf.company.security.mapper.UserRoleResourceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/27
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class ShuleFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 每一个资源所需要的权限 Collection<ConfigAttribute>决策器会用到
     */
    private static Map<String, Collection<ConfigAttribute>> methodMap = null;


    @Resource
    private UserRoleResourceDao dao;

    /**
     * 返回请求的资源需要的权限
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {


        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        //获取请求地址
        String requestUrl = request.getRequestURI();
        log.debug("@@@@@@@@@@@@@@  MetadataSource requestUrl -->"+requestUrl);
        if(methodMap == null){
            loadMethodResources();
        }
        Iterator ite = methodMap.entrySet().iterator();

        while(ite.hasNext()) {
            Map.Entry entry = (Map.Entry) ite.next();
            if((new AntPathRequestMatcher(entry.getKey().toString()).matches(request))) {
                log.debug("@@@@@@@@@@@@ entry.getkey "+entry.getKey().toString());
                log.debug("@@@@@@@@@@@@ entry.getvalue "+entry.getValue().toString());
                return (Collection) entry.getValue();
            }
        }
        Collection<ConfigAttribute> attr = new ArrayList<>();
        ConfigAttribute cf =  new SecurityConfig("AUTH_NEED");
        attr.add(cf);
        return attr;
//        return null;
    }

    //从资源权限map中得到所有的权限列表
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        Set<ConfigAttribute> allAttributes = new HashSet();
        if(methodMap == null){
            loadMethodResources();
        }
        Map<String, Collection<ConfigAttribute>> all = new HashMap<>(this.methodMap);
//	        all.putAll(this.methodMap);
        Iterator var2 = all.entrySet().iterator();
        while (var2.hasNext()) {
            Map.Entry<String, Collection<ConfigAttribute>> entry = (Map.Entry) var2.next();
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return FilterInvocation.class.isAssignableFrom(clazz);
    }


    /**
     * 初始化 所有资源 对应的权限
     */
    public void loadMethodResources() {
//		因为只有权限控制的资源才需要被拦截验证,所以只加载有权限控制的资源
//		 方法资源为key,权限编码为value
        methodMap = new HashMap<>();

        //取出所有的后台管理的资源权限数据
        List<RoleResourceInfo> resCodes = dao.getAllResource(MicroServiceList.APP_COMPANY);

        for (RoleResourceInfo info : resCodes) {
            String path = info.getRes_value();
            ConfigAttribute ca = new SecurityConfig(info.getAuth_code().toUpperCase());
            disposeSth(ca, methodMap, path);
           /* String[] roles = info.getRole_code().split(",");
            for(String role : roles){
                ConfigAttribute ca = new SecurityConfig(role.toUpperCase());
                disposeSth(ca, methodMap, path);
            }*/
        }

        disposeSth(new SecurityConfig(AuthEnums.AUTH_WHITE.toString()),methodMap,"/error");
    }

    /**
     * 生成路径（key）与资源名称（value）  也就是将接口名和权限一一对应存入map中，在决策器那里使用
     * @params [ca, map, path]
     * @return void
     * @version 1.0
     */
    private void disposeSth(ConfigAttribute ca,Map<String, Collection<ConfigAttribute>> map, String path){
        Collection<ConfigAttribute> collection = map.get(path);
        if (collection != null) {
            collection.add(ca);
        } else {
            collection = new ArrayList<>();
            collection.add(ca);
            map.put(path, collection);
        }
    }

}

