package com.gf.company.security.compont;


import com.gf.api.AuthResourceException;
import com.gf.api.MicroServiceList;
import com.gf.api.RoleEnums;
import com.gf.api.entity.RoleResourceInfo;
import com.gf.company.security.mapper.UserRoleResourceDao;
import com.gf.company.security.service.ShuleFilterInvocationSecurityMetadataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Slf4j
@Component
public class SecurityStartUp implements CommandLineRunner {

    @Value("${security.initResource}")
    private boolean initResource;

    @Autowired
    private ShuleFilterInvocationSecurityMetadataSource securityMetadataSource;
    //获取所有接口的信息的类
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private UserRoleResourceDao dao;


    //	@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void run(String... args) throws Exception {
        if(initResource) {
            initProjectMethod();
        }
        securityMetadataSource.loadMethodResources();
    }

    private void initProjectMethod() {

//		List<Map<String,ResourceInfo>> list = new ArrayList<>();

        Map<String, RoleResourceInfo> resMap = new HashMap<>();

        //获取所有的接口信息，RequestMappingInfo为请求接口信息，HandlerMethod接口处理方法
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (RequestMappingInfo info : map.keySet()) {

            //获取请求的接口的处理方法
            Method method = map.get(info).getMethod();
            //获取方法上面的注解信息 AuthResource是一个自定义的注解，这里获取的是这个注解的信息
            AuthResource methodResc = method.getAnnotation(AuthResource.class);

            if(methodResc == null) {
                log.info("@@@@@@@@  Controller "+method.getDeclaringClass()+" , mapping "+info+",  method '"+method.getName()+"()' doesn't have annotation.");
//                	throw new AuthResourceException("Controller class "+info+" method '"+method.getName()+"()' doesn't have annotation.");
            }else {
                //将注解的信息进行拆解，然后装进RoleResourceInfo类中再保存数据库
                RoleResourceInfo res = new RoleResourceInfo();
                String resCode = methodResc.resCode().startsWith("RES")?methodResc.resCode():"RES_"+methodResc.resCode();
                res.setRes_code(resCode);
                res.setRes_name(methodResc.resName());
                res.setAuth_code(methodResc.authCode().toString());
                res.setAuth_name(methodResc.authCode().getValue());
                res.setRole_code(getRoleCode(methodResc.roleCode()));
                res.setRole_name(methodResc.roleName());
                res.setApp_type(MicroServiceList.APP_COMPANY);
                //获取接口的信息，数据是[/dish/dt/udt]这样子的类型，所有要进行裁剪然后保存数据库
                String value = info.getPatternsCondition().toString();
                log.debug("@@@@@@@@@     res value "+value);
                res.setRes_value(value.substring(1,value.length()-1));
                //判断resCode是否重复
                if(resMap.containsKey(methodResc.resCode())) {
                    throw new AuthResourceException("存在两个相同的资源code。  查看类 "+map.get(info).getBeanType()+" 的 '"+method.getName()+"()' 方法。");
                }else {
                    resMap.put(resCode, res);
                }

            }
        }
        initResource(resMap);
    }


    private void initResource(Map<String,RoleResourceInfo> map) {
        int i = dao.removeAllResource(MicroServiceList.APP_COMPANY);
        if(i < 0){
            throw new RuntimeException("-----------------remove resource  fail..");
        }
        map.forEach((k,v) -> {
//			RoleResourceInfo res = dao.getRoleResourceInfo(k,MicroServiceList.APP_COMPANY);
//			if(res == null) {
//				新资源，需要加到数据库内
            dao.insertRoleResource(v);
//			}else {
//				if(!v.toString().equals(res.toString())) {
//					dao.updateRoleResource(v);
//				}
//			}
        });
    }

    private String getRoleCode(RoleEnums[] roles){
        String r = RoleEnums.ROLE_COMPANY_ADMIN.toString();
        for(RoleEnums role:roles){
            if(role == RoleEnums.ROLE_WHITE){
                r = role.toString();
                break;
            }
            if(role != RoleEnums.ROLE_COMPANY_ADMIN) {
                r = r + "," + role.toString();
            }
        }
        log.debug("@@@@@@@@@@  get role code -->"+r);
        return r;
    }


}
