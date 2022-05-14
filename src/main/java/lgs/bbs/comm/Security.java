package lgs.bbs.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // Spring Security를 활성화한다는 의미의 어노테이션
@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    // WebSecurityConfigurerAdapter를 상속받으면 오버라이드할 수 있습니다.
    // 인증을 무시할 경로들을 설정해놓을 수 있습니다.
    // static 하위 폴더 (css, js, img)는 무조건 접근이 가능해야하기 때문에 인증을 무시해야합니다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    }

}
