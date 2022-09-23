package com.fhdone.java2022.august.service.impl;

import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.august.mapper.demo.ContactMapper;
import com.fhdone.java2022.august.service.ContactService;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    
    @Resource(name="sqlSessionFactoryDbDemo")
    private SqlSessionFactory sqlSessionFactoryDbDemo;
    
    private ContactMapper contactMapper;

    @Override
    public List<Contract> queryContact() {
        return contactMapper.queryContact();
    }

    @Override
    public List<Contract> queryContact(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return contactMapper.queryContact();
    }
    
    @Override
    @Transactional 
    public Long insertContact(Contract contract) {
        long c = contactMapper.insertContract(contract);
        return c;
    }

    @Override
    @Transactional
    public Long insertContact(List<Contract> list) {
        Long count = 0L;
        SqlSession sqlSession = sqlSessionFactoryDbDemo.openSession(ExecutorType.BATCH);
        try {
            for (Contract contract : list) {
                contactMapper.insertContract(contract);
                count++;
            }
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return count;
    }

}
