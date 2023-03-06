package inmyword.server.config.security;

import static org.springframework.security.config.Customizer.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import inmyword.server.config.security.handler.UserAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final ObjectMapper objectMapper;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.headers().frameOptions().sameOrigin()

			.and()
			.csrf().disable()
			.cors(withDefaults())
			.formLogin()
			.loginProcessingUrl("/users/login")
			.loginPage("http://localhost:8080/login-form")
			.failureUrl("http://localhost:9090/login-form")
			.successHandler(new UserAuthSuccessHandler(objectMapper))

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
