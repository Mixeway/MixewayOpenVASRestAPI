package pl.orange.bst.mixer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextListener;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile("!noauth")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${allowed.users}")
	private String commonNames;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
        	.authorizeRequests()
        	.anyRequest()
        	.authenticated()
        .and()
        	.x509()
        	.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
        	.userDetailsService(userDetailsService());
	}
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                if (verifyCN(username)) {
                    return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
                } else throw new UsernameNotFoundException("User not found!");
            }
        };
    }
    public boolean verifyCN(String cn){
		List<String> allowedCNs =
				Stream.of(commonNames.split(","))
						.collect(Collectors.toList());
		return allowedCNs.contains(cn);
	}
}
