package com.fhdone.java2022.july.controller;


import com.fhdone.java2022.july.mapper.demo.ContactMapper;
import com.fhdone.java2022.july.service.ContactService;
import com.fhdone.java2022.march.dto.demo.Contract;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/contract", produces="application/json")
@AllArgsConstructor
@Slf4j
public class ContractController {

    private ContactMapper contactMapper;

    private ContactService contactService;

    @GetMapping("/queryContact")
    public List<Contract> queryContact() throws Exception {
        
        List<Contract> list = contactMapper.queryContact();
        return list;
    }

    @PostMapping("/queryContactPage")
    public List<Contract> queryContactPage(@RequestBody Map<String, Object> requestMap,
                                                  HttpServletRequest request) throws Exception {
        //log.info(JSON.toJSONString(requestMap));
        int pageNum = (Integer)requestMap.get("pageNum");
        int pageSize = (Integer) requestMap.get("pageSize");
        List<Contract> list = contactService.queryContact( pageNum, pageSize);
        return list;
    }

}