package cl.awakelab.SprintModulo6.web.security;

import cl.awakelab.SprintModulo6.model.persistence.repository.LoginRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    LoginRepository repository;
    public SecurityConfig(LoginRepository repository){
        this.repository = repository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/js/**", "/css/**").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers(request -> true).hasAuthority("SUPER")
                .requestMatchers("/contacto").hasAuthority("CLIENTE")
                .requestMatchers("/capacitacion").hasAuthority("CLIENTE")
                .requestMatchers("/capacitacion/**").hasAuthority("CLIENTE")
                .requestMatchers("/capacitacionList").hasAuthority("CLIENTE")
                .requestMatchers("/usuarioList").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/pagoList").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/profesionalList").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/clienteList").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/administrativoList").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/usuario").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/usuario/**").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/cliente").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/cliente/**").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/administrativo").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/administrativo/**").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/profesional").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/profesional/**").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/pago").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/pago/**").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/checklist").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/checklist/**").hasAuthority("ADMINISTRATIVO")
                .requestMatchers("/visitasList").hasAuthority("PROFESIONAL")
                .requestMatchers("/checklist").hasAuthority("PROFESIONAL")
                .requestMatchers("/checklist/**").hasAuthority("PROFESIONAL")
                .and()
                .httpBasic(Customizer.withDefaults())
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl(repository);
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}





