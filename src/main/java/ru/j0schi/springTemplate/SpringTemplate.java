package ru.j0schi.springTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.j0schi.springTemplate.view.EnterMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.j0schi.springTemplate.view.UserMenu;
import ru.j0schi.springTemplate.view.Logo;

import javax.sql.DataSource;

@RequiredArgsConstructor
@EnableWebSecurity
@SpringBootApplication
public class SpringTemplate extends WebSecurityConfigurerAdapter implements CommandLineRunner {
    private static long idUser = 0;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(" select   " +
                        "email as username, " +
                        "password, " +
                        "true as enabled " +
                        "from all_users " +
                        "where email = ?")
                .authoritiesByUsernameQuery("select " +
                        "u.email as username, " +
                        "ur.role_name as authority " +
                        "from all_users as u " +
                        "inner join users_role as ur on ur.id_role = u.role " +
                        "where u.email = ?").passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/accounts"
                        , "/accounts/create_new_account"
                        , "/transaction"
                        , "/transaction/account_id"
                        , "/create_transaction").authenticated()
                .antMatchers("/registration").anonymous()
                .and()
                .httpBasic();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringTemplate.class, args);
    }


    private final EnterMenu enterMenu;
    private final UserMenu userMenu;

    @Override
    public void run(String... args) throws Exception {
        Logo.myLogo();
        char exit = 'o';
        System.out.println("Привет!");
        while (idUser == 0) {
            idUser = enterMenu.helloFirst();
        }
        while (exit != 'q') {
            exit = userMenu.accountMenu(idUser);
        }
    }

}

