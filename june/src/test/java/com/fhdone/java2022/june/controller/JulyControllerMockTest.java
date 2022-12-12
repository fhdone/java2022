package com.fhdone.java2022.june.controller;

import com.alibaba.fastjson2.JSON;
import com.fhdone.java2022.june.BaseTest;
import com.fhdone.java2022.june.JulyClient;
import com.fhdone.java2022.march.dto.ResultInfo;
import com.fhdone.java2022.march.dto.demo.Contract;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class JulyControllerMockTest extends BaseTest{

    @Mock
    private JulyClient julyClient;

    @InjectMocks
    private JulyController julyController;

    @Test
    public void july() throws Exception {

        when(julyClient.july()).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                TimeUnit.SECONDS.sleep(1);
                return "mock july";
            }
        });

        ResultInfo resultInfo = julyController.july();
        log.info(JSON.toJSONString(resultInfo));
        Assert.assertTrue("mock july".equals(resultInfo.getData()));
    }



    @Test
    public void queryContact() throws Exception {

        when(julyClient.queryContact()).thenAnswer(new Answer<List<Contract>>() {
            @Override
            public List<Contract> answer(InvocationOnMock invocationOnMock) throws Throwable {
                TimeUnit.SECONDS.sleep(2);

                List<Contract> list = new ArrayList<>();
                return list;
            }
        });

        ResultInfo resultInfo = julyController.queryContact();
        log.info(JSON.toJSONString(resultInfo));
        //Assert.assertTrue("mock july".equals(resultInfo.getData()));
    }

} 
