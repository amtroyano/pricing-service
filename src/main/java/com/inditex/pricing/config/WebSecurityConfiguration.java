package com.inditex.pricing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private Environment environment;

  public WebSecurityConfiguration(Environment environment) {
    this.environment = environment;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // @formatter:off
      httpSecurity
        .headers()
          .xssProtection()
        .and()
          .contentSecurityPolicy("default-src 'self';img-src data: https:;object-src 'none'; script-src https://stackpath.bootstrapcdn.com/ 'self' 'unsafe-inline';style-src https://stackpath.bootstrapcdn.com/ 'self' 'unsafe-inline'; upgrade-insecure-requests;");

      httpSecurity
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
          .csrf()
        .and()
          .cors()
        .and()
          .authorizeRequests()
            .antMatchers("/price").permitAll();
    // @formatter:on
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    String[] activeProfiles = environment.getActiveProfiles();

    // @formatter:off
    web
      .ignoring()
        .antMatchers("/v3/api-docs")
        .antMatchers("/swagger-resources/**")
        .antMatchers("/swagger-ui.html")
        .antMatchers("/webjars/**")
        .antMatchers("/h2-console/**/**")
        .antMatchers("/actuator/**");
 // @formatter:on
  }

}
