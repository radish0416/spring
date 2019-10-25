package com.gf.company.security.service;


import com.gf.api.MicroServiceList;
import com.gf.api.entity.*;
import com.gf.company.security.mapper.UserRoleResourceDao;
import com.gf.company.test.dao.CompanyBaseInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/25
 */
@SuppressWarnings("ALL")
@Primary
@Component
public class ShuleUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CompanyBaseInfoMapper baseInfoMapper;
    @Autowired
    private UserRoleResourceDao userRoleResourceDao;

//    public UserDetailsServiceImpl(CompanyBaseInfoMapper baseInfoMapper,UserRoleResourceDao userRoleResourceDao){
//        this.baseInfoMapper = baseInfoMapper;
//        this.userRoleResourceDao = userRoleResourceDao;
//    }


    /**
     * 用户登录成功后，根据用户名到数据库中去查询该用户的权限列表
     * @param username 用户名
     * @return  用户所拥有的权限列表
     * @throws UsernameNotFoundException
     */
    @Override
    public ShuleUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //判断员工表是否有该用户
        CompanyBaseInfo companyBaseInfo = baseInfoMapper.findCompanyByPhone(username);

        //如果员工表中没有这个用户就到企业表中查询，判断企业表是否有该用户
        if (companyBaseInfo == null) {
//            判断是否管理员登录
            CompanyInfo companyInfo = baseInfoMapper.findBigCompanyByPhone(username);
            if (companyInfo == null) {
                throw new UsernameNotFoundException("手机号 " + username + " 没有找到");
            }
            //企业表中有该用户，然后到资源权限表中进行查询资源权限
            List<String> userAuths = userRoleResourceDao.getUserAuths(companyInfo.getId());
            //companyInfo是企业用户信息，userAuths是用户权限列表
            return setShuleUserDetails(companyInfo,userAuths);

        } else {
            //员工表中有该用户，然后到资源权限表中进行查询资源权限
            List<String> userAuths = userRoleResourceDao.getUserAuths(companyBaseInfo.getId());
            //companyBaseInfo是员工用户信息，userAuths是用户权限列表
            return setShuleUserDetails(companyBaseInfo,userAuths);
        }
    }

    //将用户的资源权限列表进行缓存，在访问接口，节点判断权限时使用，也就是ShuleAccessDecisionManager类的decide方法使用
    private ShuleUserDetails setShuleUserDetails(Object source,List<String> userAuths){

        //判断权限列表是否为空
        if(CollectionUtils.isEmpty(userAuths)){
            return null;
        }

        //权限列表是由多个权限组成的字符串，中间用逗号隔开，这里将各个权限进行拆解放到数组中
        List<String> authList = Arrays.asList(String.join(",",userAuths).split(","));
        //根据权限到资源权限表中去查询出所有可以访问的节点，也就是可以访问的接口信息，因为这里是后台管理系统，所以只需要查询类型为后台的资源权限，MicroServiceList.APP_COMPANY
        List<RoleResourceInfo> rrs = userRoleResourceDao.getResourcesByAuthCode(MicroServiceList.APP_COMPANY,authList);

        Set<ShuleGrantedAuthority> collect = null;

        //如果用户的资源权限表不为空就将资源权限表的auth_code字段取出，存入set容器collect中，因为set可以自动去重
        if(!CollectionUtils.isEmpty(rrs)) {
            collect = rrs.stream().map(RoleResourceInfo::getAuth_code).distinct().map(ShuleGrantedAuthority::new).collect(Collectors.<ShuleGrantedAuthority>toSet());
            //AUTH_WHITE白名单权限是不需要登录就可以访问的，AUTH_DEFAULT默认权限是需要登录不需要校验就可以访问的
            ShuleGrantedAuthority grantedAuthority = new ShuleGrantedAuthority("AUTH_WHITE");
            collect.add(grantedAuthority);
            ShuleGrantedAuthority ga = new ShuleGrantedAuthority("AUTH_DEFAULT");
            collect.add(ga);
        }

        //如果用户为企业用户
        if(source instanceof CompanyInfo) {
            CompanyInfo companyInfo = (CompanyInfo) source;
            ShuleUserDetails shuleUserDetails = new ShuleUserDetails();
            BeanUtils.copyProperties(companyInfo, shuleUserDetails, ShuleUserDetails.class);
            shuleUserDetails.setUsername(companyInfo.getMobile_inf());
            shuleUserDetails.setPassword(companyInfo.getPwd());
            shuleUserDetails.setAuthorities(collect);
            shuleUserDetails.setCompany_id(companyInfo.getId());
            shuleUserDetails.setDept_id(0);
            shuleUserDetails.setUserType(0);
            return shuleUserDetails;
        }

        //如果用户为员工用户
        if(source instanceof CompanyBaseInfo){
            CompanyBaseInfo companyBaseInfo = (CompanyBaseInfo) source;
            ShuleUserDetails shuleUserDetails = new ShuleUserDetails();
            BeanUtils.copyProperties(companyBaseInfo, shuleUserDetails, ShuleUserDetails.class);
            shuleUserDetails.setUsername(companyBaseInfo.getMobile_inf());
            shuleUserDetails.setPassword(companyBaseInfo.getPwd());
            shuleUserDetails.setAuthorities(collect);
            shuleUserDetails.setUserType(1);
            return shuleUserDetails;
        }

        return null;
    }

}

