package com.start.stockdata.config;

import com.start.stockdata.config.jwt.JWTConfigurer;
import com.start.stockdata.config.jwt.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /*  @Qualifier("userDetailsService")
    private final UserDetailsService userDetailsService;*/

    private final JwtAuthenticationEntryPoint unauthorizedHandler;


    public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

  /*    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }*/

 /*   @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter(userDetailsService,jwtTokenUtil);
    }*/



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                    .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/validation-results").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                //swagger configuration
                //.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .anyRequest()
                .authenticated()

                    .and()
                .apply(securityConfigurerAdapter())

                    .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                //.accessDeniedHandler(accessDeniedHandler())

                    .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        //JWTConfigurer used instead
       /* http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);*/
    }

    //swagger configuration
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // Not Work
  /*  @Bean
    public MyAccessDeniedHandler accessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }*/

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(/*userDetailsService,*/);
    }


}
