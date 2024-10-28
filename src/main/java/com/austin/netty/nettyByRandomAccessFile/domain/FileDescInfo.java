package com.austin.netty.nettyByRandomAccessFile.domain;

/**
 * @ClassName: FileDescInfo
 * @description:
 * @author: zqz
 * @date: 2024/10/28 23:32
 */

public class FileDescInfo {

    private String fileUrl;
    private String fileName;
    private Long fileSize;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
