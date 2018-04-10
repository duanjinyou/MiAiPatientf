package com.jiakang.miai.advertisement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar pg1;

    private Button btnout, btngoback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        init();
        onClick();
        webView.loadUrl("http://www.hao123.com");
    }


    private void init() {
        // TODO 自动生成的方法存根
        webView = (WebView) findViewById(R.id.webview1);
        pg1 = (ProgressBar) findViewById(R.id.progressBar1);
        btnout = (Button) findViewById(R.id.btnout);
        btngoback = (Button) findViewById(R.id.btngoback);
        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings seting = webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if (newProgress == 100) {
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                    if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                        btnout.setVisibility(View.VISIBLE);
                    } else {//当webview处于第一页面时,直接退出程序
                        btnout.setVisibility(View.GONE);
                    }

                } else {
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg1.setProgress(newProgress);//设置进度值
                }

            }
        });

    }

    private void onClick() {
        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btngoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    webView.goBack();

                } else {//当webview处于第一页面时,直接退出程序
                    finish();
                }
            }
        });
    }

}
