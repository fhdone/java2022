package com.fhdone.java2022.august.controller;


import com.fhdone.java2022.august.mapper.demo.ContactMapper;
import com.fhdone.java2022.august.utils.DemoMethod;
import com.fhdone.java2022.march.dto.demo.Contract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Tag(name = "ContractController", description = "ContractController desc")
@RestController
@RequestMapping(path="/contract", produces="application/json")
@AllArgsConstructor
public class ContractController {

    private ContactMapper contactMapper;
    
    @GetMapping("/queryContact")
    public List<Contract> queryContact() throws Exception {
        
        List<Contract> list = contactMapper.queryContact();
        return list;
    }
    
    @Operation(summary = "queryContactByCondition",description = "queryContactByCondition desc")
    @PostMapping("/queryContactByCondition")
    public List<Contract> queryContactByCondition(@RequestBody Map<String, Object> requestMap,
                                                  @DemoMethod String demoStr,
                                                  HttpServletRequest request) throws Exception {
        
        List<Contract> list = contactMapper.queryContactByCondition(requestMap);
        return list;
    }

}