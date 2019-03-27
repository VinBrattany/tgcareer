package com.main.tgcareer.modules.job.service;

import com.main.tgcareer.modules.job.dao.JobDao;
import com.main.tgcareer.modules.job.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class JobService {
    @Autowired
    JobDao jobDao;

    public Job getJob(String id){
        return jobDao.getJob(id);
    }

    @Transactional
    public void saveJob(Job job){
        job.setId(UUID.randomUUID().toString().replaceAll("-",""));
        jobDao.saveJob(job);
    }

    @Transactional
    public void updateJob(Job job){
        jobDao.updateJob(job);
    }

    @Transactional
    public void deleteJob(String id){
        jobDao.deleteJob(id);
    }
}
