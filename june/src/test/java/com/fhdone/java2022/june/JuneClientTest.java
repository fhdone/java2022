package com.fhdone.java2022.june;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JuneClientTest extends BaseTest{

    @Autowired
    private JuneClient juneClient;

    @Test
    public void july(){
        log.info(juneClient.july());
    }

}
