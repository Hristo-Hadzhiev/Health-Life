package health.service.impl;

import java.util.Collections;
import java.util.List;

import health.service.ImageShuffler;
import org.springframework.stereotype.Component;

@Component
public class ImageShufflerImpl implements ImageShuffler {
    @Override
    public void shuffle(List<String> images) {
        Collections.shuffle(images);
    }
}