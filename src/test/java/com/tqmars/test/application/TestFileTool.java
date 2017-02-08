package com.tqmars.test.application;

import com.tqmars.cardrecycle.application.content.FileUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperties;

/**
 * Created by jjh on 2/8/17.
 */
public class TestFileTool {
    @Test
    public void testFileUtil() throws IOException {
////        File file1 = new File("./content/");
////        File file = new File(file1, "a.txt");
////        file1.mkdir();
////        file.createNewFile();
//        FileWriter w = new FileWriter("./content/a.txt");
//
//        IOUtils.write("123"+System.getProperty("line.separator")+"1123", w);
//        w.flush();
////        System.out.println(IOUtils.readLines(new FileReader("./content/a.txt")));
//
//        Properties ps = System.getProperties();
//        ps.entrySet().forEach(e->System.out.println(e.getKey()+"="+e.getValue()));
//        FileUtil.getInstance().writeceFileContent("./content","a.txt","asdadgb135");
//        System.out.println(FileUtil.getInstance().readFileContent("./content","a.txt"));

    }
}
