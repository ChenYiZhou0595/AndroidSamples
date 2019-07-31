package com.chen.service;

/**
 * @description:
 * @author: Chenyz
 * @date: 2019/7/31 21:20
 */
public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
