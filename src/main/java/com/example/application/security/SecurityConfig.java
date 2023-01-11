package com.example.application.security;

import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.application.views.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

  public static InMemoryUserDetailsManager userdetails = new InMemoryUserDetailsManager(
          User.withUsername("t")
                  .password("{noop}t")
                  .roles("TUTOR")
                  .build(),
          User.withUsername("s")
                  .password("{noop}s")
                  .roles("STUDENT")
                  .build()
  );
  private static class CrmInMemoryUserDetailsManager extends InMemoryUserDetailsManager {
    public CrmInMemoryUserDetailsManager() {
      createUser(new User("t",
              "{noop}t",
              Collections.singleton(new SimpleGrantedAuthority("ROLE_TUTOR"))));
      createUser(new User("s",
              "{noop}s",
              Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"))));
    }
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Authorize access to /images/ without authentication
    http.authorizeRequests().antMatchers("/images/**").permitAll();
    // Set default security policy that permits Vaadin internal requests and
    // denies all other
    super.configure(http);
    setLoginView(http, LoginView.class, "/logout");
  }

  @Bean
  public UserDetailsService userDetailsServiceBean() throws Exception {
    return userdetails;
  }
  public static void addUser(String name, String pass, String role) {
    System.out.println("added user " + name);
    userdetails.createUser(new User(name,
            "{noop}" + pass,
            Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))));
  }
}
