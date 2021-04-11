package health.config;

import health.web.interceptors.RequestInitializeInterceptor;
import health.web.interceptors.RequestProcessingTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final RequestProcessingTimeInterceptor requestProcessingTimeInterceptor;
    private final RequestInitializeInterceptor requestInitializeInterceptor;

    public WebMvcConfig(RequestProcessingTimeInterceptor requestProcessingTimeInterceptor, RequestInitializeInterceptor requestInitializeInterceptor) {
        this.requestProcessingTimeInterceptor = requestProcessingTimeInterceptor;
        this.requestInitializeInterceptor = requestInitializeInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestProcessingTimeInterceptor);
        registry.addInterceptor(requestInitializeInterceptor);
    }
}