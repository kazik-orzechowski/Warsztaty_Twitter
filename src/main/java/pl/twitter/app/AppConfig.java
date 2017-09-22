package pl.twitter.app;

import java.lang.annotation.Annotation;


import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
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

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "pl.twitter.controller", "pl.twitter.entity", "pl.twitter.dao",
		"pl.twitter.converter" })
@EnableTransactionManagement
@EnableJpaRepositories({"pl.twitter.repository"})
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("twitterPersistenceUnit");
		return emfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(getCommentConverter());
		registry.addConverter(getTweetConverter());
		registry.addConverter(getContactConverter());
		registry.addConverter(getUserConverter());
		
	}

	@Bean
	public CommentConverter getCommentConverter() {
		return new CommentConverter ();
	}

	@Bean
	public TweetConverter getTweetConverter() {
		return new TweetConverter();
	}


	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}


	@Bean
	public ContactConverter getContactConverter() {
		return new ContactConverter();
	}

	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
}

