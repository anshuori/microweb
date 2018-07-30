package com.anshu.microweb.container.configuration;

import java.util.List;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.filters.RemoteIpFilter;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.anshu.microweb.authentication.domain.Customer;
import com.anshu.microweb.authentication.domain.CustomerDTO;
import com.anshu.microweb.authentication.util.CurrencyConvertor;
import com.anshu.microweb.container.filter.CustomeLogInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer  {

	@Bean 
	public RemoteIpFilter remoteIpFilter() {//premade servlet filter
		return new RemoteIpFilter();
	}    

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		return new LocaleChangeInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) { //call back 
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(customeLogInterceptor());//.addPathPatterns("/**");


	}

	@Bean
	public CustomeLogInterceptor customeLogInterceptor(){		
		return new CustomeLogInterceptor();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) { //callback
		//converters.add(new ByteArrayHttpMessageConverter());

		WebMvcConfigurer.super.configureMessageConverters(converters);
	}




	//	@Override
	//	public void
	//	extendMessageConverters(List<HttpMessageConverter<?>>
	//	converters) {
	//	converters.clear();
	//	converters.add(new ByteArrayHttpMessageConverter());
	//	}


	@Bean
	public ModelMapper modelmapper(){		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		mapper.getConfiguration().setDeepCopyEnabled(true);
		mapper.getConfiguration().setMethodAccessLevel(AccessLevel.PRIVATE);
		mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE);
		mapper.addMappings( 

				new PropertyMap<CustomerDTO, Customer>() {					
					protected void configure(){
						map().setCustomerName(source.getFirstName());
						map(source.getUserNm(), destination.getCredentials().getUserNm());

					}					
				}				

				);

		return mapper ;
	}



	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new CurrencyConvertor());
	}




	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);// dont want to use .* suffix so as to strip the trailing characters after the last dot.
		configurer.setUseTrailingSlashMatch(true);	    
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/internal/**").addResourceLocations("classpath:/");
	}


	//this redirect http to https 

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServer(){

		return  new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {

			@Override
			public void customize(TomcatServletWebServerFactory factory) {

				TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
					@Override
					protected void postProcessContext(Context context) {
						SecurityConstraint securityConstraint = new SecurityConstraint();
						securityConstraint.setUserConstraint("CONFIDENTIAL");
						SecurityCollection collection = new SecurityCollection();
						collection.addPattern("/*");
						securityConstraint.addCollection(collection);
						context.addConstraint(securityConstraint);
					}


				};
				tomcat.addAdditionalTomcatConnectors(redirectConnector());

			}

			private Connector redirectConnector() {
				Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
				connector.setScheme("http");
				connector.setPort(8080);
				connector.setSecure(false);
				connector.setRedirectPort(8444);
				return connector;
			}

		};	
	};
}



