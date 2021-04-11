package health.web;

import health.service.CarouselService;
import health.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {

    private final CarouselService carouselService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping("/home")
    public String getHome(Locale locale, Model model){

        logger.info("Welcome home! The client locale is {}.", locale);
        //adding some time lag to check interceptor execution
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate );
        logger.info("Before returning view page");

        model.addAttribute("firstImg", carouselService.firstImage());
        model.addAttribute("secondImg", carouselService.secondImage());
        model.addAttribute("thirdImg", carouselService.thirdImage());
        return "home";
    }

    @GetMapping("/")
    public String getIndex(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "index";
        }
        return "redirect:/home";

    }
}


