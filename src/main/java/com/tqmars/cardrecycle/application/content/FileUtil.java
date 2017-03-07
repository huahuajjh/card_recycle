package com.tqmars.cardrecycle.application.content;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by jjh on 2/8/17.
 */
public class FileUtil implements IFileUtil {
    private static FileUtil INSTANCE = new FileUtil();
    private static String LINE_SEPARATOR = System.getProperty("line.separator");
    private static String FILE_SEPARATOR = "/";//System.getProperty("file.separator");

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        if (null == INSTANCE) {
            return new FileUtil();
        } else {
            return INSTANCE;
        }
    }

    @Override
    public String readFileContent(String path, String filename) {
        FileReader fr = null;
        try {
            System.out.println(FILE_SEPARATOR + path + FILE_SEPARATOR + filename);
            String file = FileUtil.class.getResource(FILE_SEPARATOR + path + FILE_SEPARATOR + filename).getFile();
            System.out.println(file);
            fr = new FileReader(file);
            List<String> list = IOUtils.readLines(fr);
            StringBuilder content = new StringBuilder();
            list.forEach(l -> {
                if (content.length() == 0) {
                    content.append(l);
                } else {
                    content.append(LINE_SEPARATOR).append(l);
                }
            });
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != fr){
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void writeFileContent(String path, String filename, String content) {
        FileWriter fw = null;
        try {
            String file = FileUtil.class.getResource(FILE_SEPARATOR+path + FILE_SEPARATOR + filename).getFile();
            fw = new FileWriter(file);
            IOUtils.write(content, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
