package com.fhdone.java2022.august.config;

import com.fhdone.java2022.august.BaseTest;
import com.google.code.kaptcha.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class KaptchaConfigTest extends BaseTest {

    @Autowired
    private Producer kaptchaProducer;

    @Test
    public void kaptcha() throws Exception {
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        
        OutputStream out = new FileOutputStream("./demo_kaptcha.png");
        ImageIO.write(image, "jpeg", out);

    }
}