package com.main.tgcareer.modules.college.service;

import com.main.tgcareer.modules.college.dao.CollegeDao;
import com.main.tgcareer.modules.college.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CollegeService {
    @Autowired
    CollegeDao collegeDao;
    @Transactional
    public College getCollege(String id){
        return collegeDao.getCollege(id);
    }
    @Transactional
    public void updateCollege(College college){
        college.setId(UUID.randomUUID().toString().replaceAll("-",""));
        collegeDao.saveCollege(college);
    }
    @Transactional
    public void deleteCollege(String id){
        collegeDao.deleteCollege(id);
    }
}
