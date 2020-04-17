package br.com.ErrorCenter.config;

import br.com.ErrorCenter.converters.CreatedAtParamConverter;
import br.com.ErrorCenter.converters.LevelParamConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LevelParamConverter());
        registry.addConverter(new CreatedAtParamConverter());
    }

}
