package cn.gongyan.learn;


import cn.gongyan.learn.service.impl.CodeService;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ProjectTest {
    @Test
    public void test(){
        ConfigurableApplicationContext run = SpringApplication.run(LearnApplication.class);
        CodeService bean = run.getBean(CodeService.class);
        String s= "public class Solution {\n"
                + "    public static void main(String[] args) {\n"
                + "       System.out.println(\"你好\");\n"
                + "    }\n"
                + "}";
        String execute = bean.execute(s);
        System.out.println(execute);

    }
}
