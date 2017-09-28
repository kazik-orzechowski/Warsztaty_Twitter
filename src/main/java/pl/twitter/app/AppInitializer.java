package pl.twitter.app;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	@Override
	protected Filter[] getServletFilters() {
				CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
				characterEncodingFilter.setEncoding("UTF-8");
				return new Filter[] { characterEncodingFilter };
			}

}




//package pl.twitter.app;
//
//import javax.servlet.Filter;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
///**
// * Twitter application initialization class
// * @author kaz
// *
// */
//
//public class AppInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//	/**
//	 * Twitter startup method configuring web application context. 
//	 */
//	public void onStartup(ServletContext container) throws ServletException {
//		
//		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//		context.register(AppConfig.class);
//		context.setServletContext(container);
//		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(context));
//		servlet.setLoadOnStartup(1);
//		servlet.addMapping("/");
//	}
//	
//	/**
//	 * Method defining root configuration classes
//	 */
//	
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return null;
//	}
//
//	/**
//	 * Method defining AppConfig class as servlet config class 
//	 */
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new	Class[]{AppConfig.class};
//	}
//
//	/**
//	 * Method defining servlet mapping 
//	 */
//	
//	@Override
//	protected String[] getServletMappings() {
//		return	new	String[]{"/"};
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		return new Filter[] { characterEncodingFilter };
//	}
//	
//}
