package cn.itcast.travel.test;

import cn.itcast.travel.util.EmailController;
import org.junit.Test;

public class EmailTest {

    @Test
    public void Test1(){
        EmailController email = new EmailController();
        email.setMessage("hello");
        email.setSendto("3218874425@qq.com");
        email.setSubject("nihao");
        email.sendEmail();

    }
}
