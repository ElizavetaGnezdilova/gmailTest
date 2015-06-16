package com.epam.atlab.utils;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by Elizaveta_Gnezdilova on 6/12/2015.
 */
public class Utils {
    protected final String FILE_TO_ATTACH_NOT_IMAGE = "AttachmentFileNotImage.txt";
    protected final String FILE_MORE_THAN_25MB = "AttachmentFile26Mb.txt";

    public String createFile(String fileName, long size) {
        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            file.setLength(size);
            file.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        return "C:\\Users\\Elizaveta_Gnezdilova\\gmailTest\\" + fileName;
    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

}
