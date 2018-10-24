package cn.merryyou.client.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created on 2018/6/7.
 *
 * @author zlf
 * @since 1.0
 */
@Configuration
@EnableResourceServer
public class MerryyouResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/index","/login").permitAll().antMatchers(HttpMethod.GET, "/api")
                // 拦截用户，必须具有所列权限
                .hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.GET,"/roleAdmin")
                .hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
    }
}