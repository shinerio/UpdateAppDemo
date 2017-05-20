package com.qugan.shinerio.myapplication;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jstxzhangrui on 2017/5/20.
 */
public class CheckUpdateUtils {
    @SuppressWarnings("unused")
    public static void checkUpdate(String appCode, String curVersion,final CheckCallBack updateCallback) {
        ApiService apiService=   ServiceFactory.createServiceFrom(ApiService.class);
        apiService.getUpdateInfo() //测试使用
                //   .apiService.getUpdateInfo(appCode, curVersion)//开发过程中可能使用的
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateAppInfo>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(UpdateAppInfo updateAppInfo) {
                        if (updateAppInfo.error_code == 0 || updateAppInfo.data == null ||
                                updateAppInfo.data.updateURL == null) {
                            updateCallback.onError(); // 失败
                        } else {
                            updateCallback.onSuccess(updateAppInfo);
                        }
                    }
                });
    }
    public interface CheckCallBack{//检测成功或者失败的相关接口
        void onSuccess(UpdateAppInfo updateInfo);
        void onError();
    }
}
