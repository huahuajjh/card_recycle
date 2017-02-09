package com.tqmars.cardrecycle.application.content;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

/**
 * Created by jjh on 2/8/17.
 */
public class FileUtil implements IFileUtil {
    private static FileUtil INSTANCE = new FileUtil();
    private static String LINE_SEPARATOR = System.getProperty("line.separator");
    private static String FILE_SEPARATOR = System.getProperty("file.separator");

    private FileUtil(){}

    public static FileUtil getInstance(){
        if(null == INSTANCE){
            return new FileUtil();
        }else {
            return INSTANCE;
        }
    }

    static {
        File p = new File("./content");
        if(!(p.isDirectory() || p.exists())){
            p.mkdir();
        }
    }

    @Override
    public String readFileContent(String path, String filename) {
        FileReader fr = null;
        try {
            fr = new FileReader(path+FILE_SEPARATOR+filename);
            try {
                List<String> list = IOUtils.readLines(fr);
                StringBuilder content = new StringBuilder();
                list.forEach(l->{
                    if(content.length() == 0){
                        content.append(l);
                    }else {
                        content.append(LINE_SEPARATOR).append(l);
                    }
                });
                return content.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(null != fr){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    @Override
    public void writeceFileContent(String path, String filename, String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path+FILE_SEPARATOR+filename);
            IOUtils.write(content,fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != fw){
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
