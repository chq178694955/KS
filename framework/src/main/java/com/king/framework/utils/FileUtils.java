package com.king.framework.utils;

import java.io.File;
import java.io.IOException;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public class FileUtils {

    public static File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    public static File createDir(String dirPath) {
        File path = new File(dirPath);
        if (!path.exists()) {
            path.mkdirs();
        }

        return path;
    }

}
