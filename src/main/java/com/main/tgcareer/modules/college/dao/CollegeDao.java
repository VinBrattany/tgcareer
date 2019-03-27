package com.main.tgcareer.modules.college.dao;

import com.main.tgcareer.modules.college.entity.College;
import org.springframework.stereotype.Component;
@Component
public interface CollegeDao {
    /*
     * 根据openID返回学校
     * @param id
     * @return College
     */
    public College getCollege(String id);

    /*
     *  新增学校
     *  @param college
     */
    public void saveCollege(College college);

    /**
     * 更新学校信息
     * @param college
     */
    public void updateCollege(College college);

    /**
     * 删除学校信息
     * @param id
     */
    public void deleteCollege(String id);
}
