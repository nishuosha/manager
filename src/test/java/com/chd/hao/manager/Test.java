package com.chd.hao.manager;

import com.chd.hao.manager.model.AdminModel;
import com.chd.hao.manager.model.UserModel;

/**
 * Created by zhanghao68 on 2018/4/28
 */
public class Test {

    public static void main(String[] args) {

        Object o1 = new AdminModel();
        Object o2 = new UserModel();

        if(o1 instanceof UserModel) {
            System.out.println("user");
        } else {
            System.out.println("admin");
        }

    }

}
