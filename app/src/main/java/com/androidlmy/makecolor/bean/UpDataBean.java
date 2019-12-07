package com.androidlmy.makecolor.bean;

import java.io.Serializable;

/**
 * author: Liming
 * Date: 2019/8/13 10:14
 * Created by Android Studio.
 */
public class UpDataBean implements Serializable {


    /**
     * code : 200
     * data : {"versionNo":"2","filepath":"http://47.100.250.181:8080/images/app_storck.apk","version":"1.1","content":"1.修复已知bug。\\n2.优化界面显示。"}
     * message : 查询成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * versionNo : 2
         * filepath : http://47.100.250.181:8080/images/app_storck.apk
         * version : 1.1
         * content : 1.修复已知bug。\n2.优化界面显示。
         */

        private String versionNo;
        private String filepath;
        private String version;
        private String content;

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
