package com.fhdone.java2022.july.service;


import com.fhdone.java2022.march.dto.demo.Contract;
import com.github.pagehelper.Page;

import java.util.List;

public interface ContactService {

    public List<Contract> queryContact() ;
    
    public List<Contract> queryContact(int pageNum, int pageSize);
    
    public Page<Contract> queryContactPage(int pageNum, int pageSize);
    
    public Long insertContact(Contract contract) ;

    public Long insertContact(List<Contract> list);
    
}
