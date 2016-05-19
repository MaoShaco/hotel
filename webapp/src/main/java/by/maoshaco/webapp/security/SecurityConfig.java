package by.maoshaco.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	UserDetailsService customUserDetailsService;

	public static PasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/images/**")
		.antMatchers("/js/**")
		.antMatchers("/css/**");
	}

	protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/signedin")
//                .failureUrl("/user/{username}")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/");

        http
                .authorizeRequests();
    }
}