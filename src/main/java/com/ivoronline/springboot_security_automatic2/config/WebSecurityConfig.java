package com.ivoronline.springboot_security_automatic2.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  //=================================================================
  // CONFIGURE (AuthenticationManagerBuilder auth)
  //=================================================================
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("myadmin").password("myadminpassword").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("myuser" ).password("myuserpassword1" ).roles("USER" );
  }

  //=======================================================================
  // PASSWORD ENCODER
  //=======================================================================
  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  //=======================================================================
  // CONFIGURE
  //=======================================================================
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().antMatchers("/Authenticate").permitAll(); //ANONYMOUS ACCESS
    httpSecurity.formLogin();                                                  //DEFAULT LOGIN FORM
  }

}
