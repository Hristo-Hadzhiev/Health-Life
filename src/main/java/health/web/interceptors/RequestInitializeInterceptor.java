package health.web.interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInitializeInterceptor implements HandlerInterceptor {

    //    / Obtain a suitable logger.
    private static final Log logger = LogFactory
            .getLog(RequestInitializeInterceptor.class);

    /**
     * In this case intercept the request BEFORE it reaches the controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        try {
            logger.info("Intercepting: " + request.getRequestURI());

            // Do some changes to the incoming request object
            updateRequest(request);

            return true;
        } catch (SystemException e) {
            logger.info("request update failed");
            return false;
        }
    }

    /**
     * The data added to the request would most likely come from a database
     */
    private void updateRequest(HttpServletRequest request) {

        logger.info("Updating request object");
        request.setAttribute("commonData",
                "This string is required in every request");
    }

    /** This could be any exception */
    private static class SystemException extends RuntimeException {

        private static final long serialVersionUID = 1L;
        // Blank
    }

}
