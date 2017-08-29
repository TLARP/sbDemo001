package com.devi.test.dds.manager;

import com.devi.test.dds.dao.RealtorAdminMapper;
import com.devi.test.dds.domain.RealtorAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author lv bin
 *
 * @date 2016/11/4 13:58
 * version V1.0.0
 */
//@Service
public class RealtorAdminManager {

    @Autowired
    private RealtorAdminMapper realtorAdminMapper;


    public void save(RealtorAdmin admin) {
        realtorAdminMapper.insert(admin);
    }

    @Transactional
    public void add(RealtorAdmin admin) {
        realtorAdminMapper.insert(admin);
    }

    public RealtorAdmin get(Long id) {
        return realtorAdminMapper.selectByPrimaryKey(id);
    }
}
