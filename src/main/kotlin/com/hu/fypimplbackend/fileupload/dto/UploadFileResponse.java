package com.hu.fypimplbackend.fileupload.dto;

import java.util.Objects;

public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse() {

    }

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String fileName() {
        return fileName;
    }

    public UploadFileResponse setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String fileDownloadUri() {
        return fileDownloadUri;
    }

    public UploadFileResponse setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
        return this;
    }

    public String fileType() {
        return fileType;
    }

    public UploadFileResponse setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public long size() {
        return size;
    }

    public UploadFileResponse setSize(long size) {
        this.size = size;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UploadFileResponse that = (UploadFileResponse) o;

        if (size != that.size) return false;
        if (!Objects.equals(fileName, that.fileName)) return false;
        if (!Objects.equals(fileDownloadUri, that.fileDownloadUri))
            return false;
        return Objects.equals(fileType, that.fileType);
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (fileDownloadUri != null ? fileDownloadUri.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UploadFileResponse{" +
            "fileName='" + fileName + '\'' +
            ", fileDownloadUri='" + fileDownloadUri + '\'' +
            ", fileType='" + fileType + '\'' +
            ", size=" + size +
            '}';
    }
}