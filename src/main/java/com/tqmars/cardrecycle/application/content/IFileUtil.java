package com.tqmars.cardrecycle.application.content;

/**
 * Created by jjh on 2/8/17.
 */
public interface IFileUtil {
    String readFileContent(String path,String filename);
    void writeFileContent(String path, String filename, String content);
}
