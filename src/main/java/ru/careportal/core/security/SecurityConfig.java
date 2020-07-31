package ru.careportal.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.careportal.core.db.model.Role;
import ru.careportal.core.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userService;

    @Autowired
    public void setUserService(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/registration", "/login", "/resources/**").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .formLogin().loginPage("/login").usernameParameter("email").successHandler(myAuthenticationSuccessHandler()).permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userService)
                .passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new DependsOnRoleUrlAuthenticationSuccessHandler();
    }
}
