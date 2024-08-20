package br.com.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("12345"))
				.roles("USUARIO")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("12345"))
				.roles("USUARIO", "ADMINISTRADOR")
				.build();
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	
	@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .requestMatchers(HttpMethod.GET, "/nota-entrada").hasRole("ADMINISTRADOR")
        .requestMatchers(HttpMethod.GET, "/nota-entrada").hasRole("ADMINISTRADOR")
        .requestMatchers(HttpMethod.GET, "/estoque").hasRole("ADMINISTRADOR")
        .anyRequest()
        	.authenticated()
        .and()
        	.formLogin()
        	.loginPage("/login")
        	.permitAll()
        .and()
        	.logout()
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/login");
        return http.build();
    }
	
	
}
