package edu.ftn.isa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.ftn.isa.payload.BasicAuthEntryPoint;
import edu.ftn.isa.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private BasicAuthEntryPoint authenticationEntryPoint;
	
	@Autowired
	@Qualifier("datasource")
	private DataSource dataSource;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
        auth.jdbcAuthentication()
        	.usersByUsernameQuery("select username, password, enabled from users where username=?")
        	.passwordEncoder(passwordEncoder());
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.authorizeRequests()
        		.antMatchers("/auth/**", "/app/**", "/partials/**", "/images/**", "resources/**", "/#!/home", "/**").permitAll()
        		.antMatchers("/admin/**").hasAuthority("ROLE_SysAdmin")
        		.antMatchers("/avioadmin/**").hasAuthority("ROLE_AvioAdmin")
        	.anyRequest().authenticated().and()
        		.httpBasic()
        		.authenticationEntryPoint(authenticationEntryPoint);
        
    }
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        web.ignoring().antMatchers("/resources/**");
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
