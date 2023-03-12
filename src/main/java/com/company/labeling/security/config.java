package com.company.labeling.security;

import com.company.labeling.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class config {
    private final JwtFilter jwtFilter;
    private final UserService userService;


    // @lazy to allow circle dependency
    public config(@Lazy JwtFilter jwtFilter, @Lazy UserService userService) {
        this.jwtFilter = jwtFilter;
        this.userService = userService;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                        .loginPage("http://localhost:4200/")
                        .loginProcessingUrl("http://localhost:4200/api/v1/auth/pageData")
                        .successForwardUrl("http://localhost:4200/api/v1/auth/pageData")
                        .defaultSuccessUrl("http://localhost:4200/api/v1/auth/pageData", true)
                        .permitAll().and()
                .logout().deleteCookies("remove").invalidateHttpSession(false)
                .logoutUrl("http://localhost:4200").logoutSuccessUrl("http://localhost:4200").permitAll();

        return  http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            //here we will connected it with postgres records
            @Override
            public UserDetails loadUserByUsername(String username)
                    throws UsernameNotFoundException {
                return userService.loadUserByUsername(username);
            }
        };
    }
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("http://localhost:4200").setViewName("login");
//    }
}
