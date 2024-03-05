package com.fhdone.java2022.july.service.impl;

import com.fhdone.java2022.july.mapper.demo.ContactMapper;
import com.fhdone.java2022.july.service.ContactService;
import com.fhdone.java2022.march.dto.demo.Contract;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Contract> queryContactPage(int pageNum, int pageSize){
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(()->
                contactMapper.queryContact());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertContact(Contract contract) {
        return contactMapper.insertContract(contract);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertContact(List<Contract> list) {
        Long count = 0L;
        try (SqlSession sqlSession = sqlSessionFactoryDbDemo.openSession(ExecutorType.BATCH)) {
            for (Contract contract : list) {
                contactMapper.insertContract(contract);
                count++;
            }
            sqlSession.commit();
        }
        return count;
    }

}
