package com.fhdone.java2022.august.service;


import com.fhdone.java2022.march.dto.demo.Contract;

import java.util.List;

public interface ContactService {

    public List<Contract> queryContact() ;
    
    public List<Contract> queryContact(int pageNum, int pageSize);
    
    public Long insertContact(Contract contract) ;

    public Long insertContact(List<Contract> list);
    
}
