package com.devi.test.manager;

import com.devi.test.domain.RealtorAdmin;
import com.devi.test.JunitBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author lv bin
 * @date 2016/11/4 14:06
 * version V1.0.0
 */
public class RealtorAdminManagerTest extends JunitBaseTest {

    @Autowired
    private RealtorAdminManager realtorAdminManager;

    @Test
    public void testSave(){
        RealtorAdmin admin = new RealtorAdmin();
        admin.setUsername("save001");
        admin.setPassword("111");
        realtorAdminManager.save(admin);
        System.out.println(admin.getId());
    }

    @Test
    public void testAdd(){
        // 带事务插入
        RealtorAdmin admin = new RealtorAdmin();
        admin.setUsername("add001");
        admin.setPassword("111");
        realtorAdminManager.save(admin);
        System.out.println(admin.getId());
    }



    @Test
    public void testGet(){
        System.out.println(realtorAdminManager.get(1L).getUsername());
    }

    @Test
    public void testGetBatch(){
        for (int i = 0; i < 100; i++) {
            System.out.println(realtorAdminManager.get(1L).getUsername());
        }
    }
}
