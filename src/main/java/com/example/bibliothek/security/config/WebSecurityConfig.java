package com.example.bibliothek.security.config;

import com.example.bibliothek.appUser.AppUserService;
import com.example.bibliothek.security.filter.CustomAuthenticationFilter;

import com.example.bibliothek.security.filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;

import java.sql.SQLOutput;
import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return defaultWebSecurityExpressionHandler;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl result = new RoleHierarchyImpl();
        result.setHierarchy("ROLE_ADMIN > ROLE_MANAGER");
        result.setHierarchy("ROLE_MANAGER > ROLE_USER");
        return result;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests().antMatchers("/login", "/api/v1/registration/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/books/**").hasRole("ADMIN");//permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/books/**").hasRole("MANAGER");
        http.authorizeRequests().expressionHandler(webExpressionHandler());
        /*http.authorizeRequests().anyRequest().permitAll();*/

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS", "DELETE", "PUT"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .cors(ServerHttpSecurity.CorsSpec::disable);
        return http.build();
    }*/
   /*@Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .sessionManagement(session -> session
                       .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                       .invalidSessionUrl("/invalidSession.htm")
                       .maximumSessions(1)
                       .maxSessionsPreventsLogin(true)
               );
       http
               .logout(logout -> logout
                       .deleteCookies("JSESSIONID")
               );
       http
               .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

       http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/**")
               .permitAll()
               .anyRequest()
               .authenticated().and()
               .authenticationProvider(daoAuthenticationProvider())
               .userDetailsService(appUserService);

        *//*http
                .formLogin().loginPage("http://localhost:3000")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage.html",true)
                .failureUrl("/index.html?error=true");*//*
       http
               .httpBasic();

       return http.build();
   }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }*/



}