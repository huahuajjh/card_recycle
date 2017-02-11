package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.content.FileUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jjh on 2/8/17.
 */
public class TestFileTool {
    @Test
    public void testFileUtilRead() throws IOException {
//        String path = this.getClass().getResource("/content/contact.txt").getFile();
//        System.out.println(path);
//
//        FileReader reader = new FileReader(path);
//        System.out.println(IOUtils.readLines(reader));
        System.out.println(FileUtil.getInstance().readFileContent("content","about.txt"));
    }

    @Test
    public void testFileUtilWrite() throws IOException {
//        String path = this.getClass().getResource("/content/about.txt").getFile();
//        FileWriter writer = new FileWriter(path);
//        IOUtils.write("hi",writer);
//        writer.flush();
        FileUtil.getInstance().writeFileContent("content","about.txt","<p>about us</p>");
    }
}
