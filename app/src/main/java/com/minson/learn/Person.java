package com.minson.learn;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
/**
 *序列化对象，来进行保存，网络传输，或Intent之间传递信息
 */
public class Person implements Serializable {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
