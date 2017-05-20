package com.qugan.shinerio.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testUpdate();
    }

    private void checkVersion(){
        //网络检查版本是否需要更新
        CheckUpdateUtils.checkUpdate("apk", "1.0.0", new CheckUpdateUtils.CheckCallBack() {
            @Override
            public void onSuccess(UpdateAppInfo updateInfo) {
                String isForce=updateInfo.data.getLastForce();//是否需要强制更新
                String downUrl= updateInfo.data.getUpdateURL();//apk下载地址
                String updateinfo = updateInfo.data.getUpdateInfo();//apk更新详情
                String appName = updateInfo.data.getAppName();
                if(isForce.equals("1")&& !TextUtils.isEmpty(updateinfo)){//强制更新
                    forceUpdate(MainActivity.this,appName,downUrl,updateinfo);
                }else{//非强制更新
                    //正常升级
                  //  normalUpdate(MainActivity.this,appName,downUrl,updateinfo);
                }
            }
            @Override
            public void onError() {
               // noneUpdate(MainActivity.this);
            }
        });
    }

    private void testUpdate(){
        UpdateAppInfo.UpdateInfo  info =new UpdateAppInfo.UpdateInfo();
        info.setLastForce("0");
        info.setAppName("测试app");
        info.setUpdateInfo("重要版本更新");
        info.setUpdateURL("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk");
        if(info.getLastForce().equals("1")){//强制更新
            forceUpdate(MainActivity.this,info.getAppName(),info.getUpdateURL(),info.getUpdateInfo());
        }else{//非强制更新,正常升级
            normalUpdate(MainActivity.this,info.getAppName(),info.getUpdateURL(),info.getUpdateInfo());
        }
    }

    private void forceUpdate(final Context context, final String appName, final String downUrl, final String updateinfo) {
        AlertDialog.Builder  mDialog = new AlertDialog.Builder(context);
        mDialog.setTitle(appName+"又更新咯！");
        mDialog.setMessage(updateinfo);
        mDialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //                if (!canDownloadState()) {
                //                    showDownloadSetting();
                //                    return;
                //                }
                DownLoadApk.download(MainActivity.this,downUrl,updateinfo,appName);
               // AppInnerDownLoder.downLoadApk(MainActivity.this,downUrl,appName);
            }
        }).setCancelable(false).create().show();
    }

    private void normalUpdate(final Context context, final String appName, final String downUrl, final String updateinfo) {
        AlertDialog.Builder  mDialog = new AlertDialog.Builder(context);
        mDialog.setTitle(appName+"又更新咯！");
        mDialog.setMessage(updateinfo);
        mDialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DownLoadApk.download(MainActivity.this,downUrl,updateinfo,appName);
            }
        }).setCancelable(true).create().show();
    }
}
