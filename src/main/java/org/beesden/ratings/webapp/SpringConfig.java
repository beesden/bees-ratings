package org.beesden.ratings.webapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "org.beesden.ratings" })
public class SpringConfig extends WebMvcConfigurerAdapter {

	@Bean
	public RequestMappingHandlerAdapter annotationMethodHandlerAdapter() {
		final RequestMappingHandlerAdapter annotationMethodHandlerAdapter = new RequestMappingHandlerAdapter();
		final MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();

		List<HttpMessageConverter<?>> httpMessageConverter = new ArrayList<HttpMessageConverter<?>>();
		httpMessageConverter.add(mappingJacksonHttpMessageConverter);

		String[] supportedHttpMethods = { "POST", "GET", "HEAD" };

		annotationMethodHandlerAdapter.setMessageConverters(httpMessageConverter);
		annotationMethodHandlerAdapter.setSupportedMethods(supportedHttpMethods);

		return annotationMethodHandlerAdapter;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}