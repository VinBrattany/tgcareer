package com.main.tgcareer.modules.job.dao;

import com.main.tgcareer.modules.job.entity.Job;
import org.springframework.stereotype.Component;
@Component
public interface JobDao {
    /*
     * 根据openID返回岗位
     * @param id
     * @return Job
     */
    public Job getJob(String id);

    /*
     *  新增岗位
     *  @param job
     */
    public void saveJob(Job job);

    /**
     * 更新岗位信息
     * @param job
     */
    public void updateJob(Job job);

    /**
     * 删除岗位信息
     * @param id
     */
    public void deleteJob(String id);
}
