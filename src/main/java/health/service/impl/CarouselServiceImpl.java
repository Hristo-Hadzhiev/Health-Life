package health.service.impl;

import health.service.CarouselService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    private Logger LOGGER = LoggerFactory.getLogger(CarouselService.class);

    private List<String> images = new ArrayList<>();

    public CarouselServiceImpl(@Value("${carousel.images}") List<String> images) {
        this.images.addAll(images);
    }

    @PostConstruct
    public void afterInitialize(){
        if(images.size() < 3){
            throw new IllegalArgumentException("Sorry, but image must be at lest 3");
        }
    }

    @Override
    public String image() {
        return images.get(0);
    }

    @Scheduled(cron = "${carousel.refresh-cron}")
    public void refresh(){
        LOGGER.info("Shuffling images...");
        Collections.shuffle(images);
    }
}
