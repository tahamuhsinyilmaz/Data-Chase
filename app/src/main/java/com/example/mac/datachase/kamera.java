package com.example.mac.datachase;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 27.02.2018.
 */

public class kamera extends Fragment  {
    private WebView webview;
    View vi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vi=inflater.inflate(R.layout.kamera_layout,container,false);

        webview = vi.findViewById(R.id.webwiew);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://192.168.1.21:8888");

        return vi;
    }

}
