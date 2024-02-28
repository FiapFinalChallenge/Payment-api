package payment.infra.external.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        var currentToken = getCurrentUserToken();

        if (currentToken != null) {
            template.header("Authorization", currentToken);
        }
    }

    private String getCurrentUserToken() {
        var requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            var request = servletRequestAttributes.getRequest();
            return request.getHeader("Authorization");
        }

        return null;
    }
}
