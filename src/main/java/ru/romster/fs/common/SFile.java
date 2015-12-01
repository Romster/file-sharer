package ru.romster.fs.common;

/**
 * Created by r0m5t3r on 11/8/15.
 */
public class SFile {
    String fileName;
    String downloadUrl;

    public SFile(String fileName, String downloadUrl) {
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SFile SFile = (SFile) o;

        if (!fileName.equals(SFile.fileName)) return false;
        return downloadUrl.equals(SFile.downloadUrl);

    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + downloadUrl.hashCode();
        return result;
    }
}
