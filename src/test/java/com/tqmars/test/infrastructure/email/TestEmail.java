package com.tqmars.test.infrastructure.email;

import com.tqmars.cardrecycle.application.content.FileUtil;
import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/15.
 */
public class TestEmail {
    @Test
    public void testSendEmail() throws Exception{
//        TqEmail.send("这是测试邮件的主题","<i>这是测试邮件的内容</i>");
//        TqEmail.sendWithHtml("测试邮件的主题","<input type='text'></input>");
//        String path = PropertiesFileTool.readByKey("emailTemplatePath");
//        String c = FileUtil.getInstance().readFileContent(path,"email-template.html");
//        System.out.println(c);

        try {
            int a = 1/0;
        }catch (Exception e){
//            System.out.println("msg : "+e.getLocalizedMessage());
//            System.out.println("local msg : "+e.getLocalizedMessage());
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement ste : e.getStackTrace()) {
//                if(ste.getClassName().contains("com.tqmars")){
//                    System.out.println(ste.getClassName());
//                    System.out.println(ste.getFileName());
//                    System.out.println(ste.getLineNumber());
//                    System.out.println(ste.getMethodName());
//                    System.out.println(e.getMessage());
//                }
System.out.println(ste);

            }
        }
//        String path = PropertiesFileTool.readByKey("emailTemplatePath");
//        String c = FileUtil.getInstance().readFileContent(path,"email-template.html");
//
//        System.out.println(c.replace("<ul>","哈哈哈"));

    }
}
