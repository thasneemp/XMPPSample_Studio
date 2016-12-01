package com.xmpp.chat.data.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

import java.util.HashMap;

import okhttp3.OkHttpClient;

public class LiveApp extends Application {
    private static LiveApp instance;
    public boolean contactListChanged = false;

    private HashMap<String, IncomingFileTransfer> fileTransfers = new HashMap<String, IncomingFileTransfer>();
    private HashMap<String, OutgoingFileTransfer> fileTransfersOut = new HashMap<String, OutgoingFileTransfer>();

    public static LiveApp get() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    public void putFileTransfer(String path, IncomingFileTransfer fileTransfer) {
        fileTransfers.put(path, fileTransfer);
    }

    public IncomingFileTransfer getFileTransfer(String path) {
        return fileTransfers.get(path);
    }

    public void putFileTransferOut(String path, OutgoingFileTransfer fileTransfer) {
        fileTransfersOut.put(path, fileTransfer);
    }

    public OutgoingFileTransfer getFileTransferOut(String path) {
        return fileTransfersOut.get(path);
    }

}
