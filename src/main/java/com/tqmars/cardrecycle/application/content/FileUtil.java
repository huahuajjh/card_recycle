package com.tqmars.cardrecycle.application.content;

import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
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
        InputStream in = null;
        try {
            String file = path+FILE_SEPARATOR+filename;
            if(file.indexOf("\\") == 0){
                file.subSequence(1,file.length()-1);
            }

            in = new FileInputStream(file);

            List<String> list = IOUtils.readLines(in,"utf-8");
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
                if(null != in){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void writeFileContent(String path, String filename, String content) {
        OutputStream out = null;
        try {
            String file = path+FILE_SEPARATOR+filename;
            if(file.indexOf("\\") == 0){
                file.subSequence(1,file.length()-1);
            }

            out = new FileOutputStream(file);
            IOUtils.write(content, out,"utf-8");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
