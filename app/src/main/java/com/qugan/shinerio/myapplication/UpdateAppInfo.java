package com.qugan.shinerio.myapplication;

/**
 * Created by jstxzhangrui on 2017/5/20.
 */
public class UpdateAppInfo {
    public UpdateInfo data;  //信息
    public Integer error_code; //错误代码
    public String error_msg; //错误信息

    public UpdateInfo getData() {
        return data;
    }

    public void setData(UpdateInfo data) {
        this.data = data;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static class UpdateInfo{
        public String appName;
        public String serverVersion;
        public String serverFlag;
        public String lastForce;
        public String updateURL;
        public String updateInfo;

        public String getUpdateURL() {
            return updateURL;
        }

        public void setUpdateURL(String updateURL) {
            this.updateURL = updateURL;
        }

        public String getUpdateInfo() {
            return updateInfo;
        }

        public void setUpdateInfo(String updateInfo) {
            this.updateInfo = updateInfo;
        }

        public String getServerFlag() {
            return serverFlag;
        }

        public void setServerFlag(String serverFlag) {
            this.serverFlag = serverFlag;
        }

        public String getLastForce() {
            return lastForce;
        }

        public void setLastForce(String lastForce) {
            this.lastForce = lastForce;
        }

        public String getServerVersion() {
            return serverVersion;
        }

        public void setServerVersion(String serverVersion) {
            this.serverVersion = serverVersion;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }
    }
}
