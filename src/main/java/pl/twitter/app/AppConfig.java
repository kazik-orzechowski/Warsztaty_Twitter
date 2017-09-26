package pl.twitter.app;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.twitter.converter.CommentConverter;
import pl.twitter.converter.ContactConverter;
import pl.twitter.converter.TweetConverter;
import pl.twitter.converter.UserConverter;

/**
 * Twitter configuration class
 * @author kaz
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "pl.twitter.controller", "pl.twitter.entity", "pl.twitter.dao",
		"pl.twitter.converter" })
@EnableTransactionManagement
@EnableJpaRepositories({"pl.twitter.repository"})
public class AppConfig extends WebMvcConfigurerAdapter {

	/**
	 * Method setting ViewResolver 
	 * view path for display pages and suffix for .jsp files
	 * @return viewResolver with set values
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Method instantiating bean factory manager object and initialising it with persistance unit name
	 * @return local entity manager factory bean object 
	 */
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("twitterPersistenceUnit");
		return emfb;
	}
	/**
	 * Method instantiating and initializing Jpa transaction manager object 
	 * @param emf
	 * @return transaction manager
	 */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}
	/**
	 * Method adding converters used in Twitter
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(getCommentConverter());
		registry.addConverter(getTweetConverter());
		registry.addConverter(getContactConverter());
		registry.addConverter(getUserConverter());
		
	}

	/**
	 * Method instantiating CommentConverter object
	 * @return CommentConverter object
	 */
	@Bean
	public CommentConverter getCommentConverter() {
		return new CommentConverter ();
	}

	/**
	 * Method instantiating TweetConverter object
	 * @return TweetConverter object
	 */
	@Bean
	public TweetConverter getTweetConverter() {
		return new TweetConverter();
	}

	/**
	 * Method instantiating UserConverter object
	 * @return UserConverter object
	 */

	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}

	/**
	 * Method instantiating ContactConverter object
	 * @return ContactConverter object
	 */
	@Bean
	public ContactConverter getContactConverter() {
		return new ContactConverter();
	}

	/**
	 * Method instantiating Validator object
	 * @return Validator object
	 */
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
}

