package inmyword.server.auth;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.headers().frameOptions().sameOrigin()

			.and()
			.csrf().disable()
			.cors(withDefaults())
			.formLogin()
			.loginPage("/login-form")
			.loginProcessingUrl("/login")
			.failureUrl("/users/login?error")
			.defaultSuccessUrl("/")

			.and()
			.logout()
			.logoutSuccessUrl("/")

			.and()
			.exceptionHandling().accessDeniedPage("/access-denied")

			.and()
			.authorizeHttpRequests()
			.anyRequest()
			.permitAll();

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
