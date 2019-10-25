package com.gf.company.security.phonepwd;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/7/27
 * 成熟的产品用户的密码存在数据库是加密的，这里就是将用户密码从数据库中取出来然后解密再和用户输入的密码进行对比
 */
@Component
public class EurekaUserPasswordEncoder  implements PasswordEncoder {

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }
}