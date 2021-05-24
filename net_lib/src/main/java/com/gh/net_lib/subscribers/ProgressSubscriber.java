package com.gh.net_lib.subscribers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.gh.net_lib.api.BaseApi;
import com.gh.net_lib.api.BaseResultEntity;
import com.gh.net_lib.exception.HttpTimeException;
import com.gh.net_lib.listener.BaseHttpOnNextListener;

import java.lang.ref.SoftReference;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author: gh
 * @description:
 * @date: 2019-07-13.
 * @from:
 */
public class ProgressSubscriber<T extends BaseResultEntity> extends DisposableSubscriber<T> {

    /*是否弹框*/
    private boolean showPorgress = true;
    /* 软引用回调接口*/
    private SoftReference<BaseHttpOnNextListener> mSubscriberOnNextListener;
    /*软引用反正内存泄露*/
    private SoftReference<Context> contextSoftReference;
    /*加载框可自己定义*/
    private Dialog pd;
    /*请求数据*/
    private BaseApi api;

    /**
     * 构造
     *
     * @param api
     */
    public ProgressSubscriber(BaseApi api) {
        this.api = api;
        this.mSubscriberOnNextListener = api.getListener();
        this.contextSoftReference = new SoftReference<>(api.getContext());
        setShowPorgress(api.isShowProgress());
        if (api.isShowProgress()) {
            initProgressDialog(api.isCancel());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSubscriberOnNextListener.get().onLoading();
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            if (t.isSuccess()) {
                mSubscriberOnNextListener.get().onNext(t.getData());
            } else {
                mSubscriberOnNextListener.get().onError(new HttpTimeException(t.getMsg()), t);
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        mSubscriberOnNextListener.get().onLoadFinish();
        dismissProgressDialog();
        errorDo(t);
    }

    @Override
    public void onComplete() {
        mSubscriberOnNextListener.get().onLoadFinish();
        dismissProgressDialog();
    }

    /**
     * 初始化加载框
     */
    private void initProgressDialog(boolean cancel) {
        Context context = contextSoftReference.get();
        if (pd == null && api != null) {
            pd = api.getProgressDialog();
            pd.setCancelable(cancel);
            if (cancel) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        if (mSubscriberOnNextListener.get() != null) {
                            mSubscriberOnNextListener.get().onCancel();
                        }
                        onCancelProgress();
                    }
                });
            }
        }
    }

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!isShowPorgress()) return;
        Context context = contextSoftReference.get();
        if (pd == null || context == null) return;
        if (!pd.isShowing()) {
            try {
                pd.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!isShowPorgress()) return;
        if (pd != null && pd.isShowing()) {
            try {
                pd.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 错误统一处理
     */
    private void errorDo(Throwable e) {
        Context context = contextSoftReference.get();
        if (context == null) return;
//        if (e instanceof SocketTimeoutException) {
//            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof ConnectException) {
//            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "错误" + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e, null);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    public boolean isShowPorgress() {
        return showPorgress;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showPorgress
     */
    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }

}
