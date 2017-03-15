package cn.edu.gdmec.s07150808.webview;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;




public class MainActivity extends AppCompatActivity {

    private WebView web;
    private ProgressDialog bar;
    private AlertDialog dol;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initweb();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       if(keyCode == event.KEYCODE_BACK &&web.canGoBack()){
           web.goBack();
           return true;
       }
        return super.onKeyDown(keyCode, event);
    }

    private void initweb() {
        bar = ProgressDialog.show(MainActivity.this,null,"加载中，请稍等！");

        web = (WebView)this.findViewById(R.id.web);

        web.getSettings().setJavaScriptEnabled(true);



        EditText edit = (EditText) findViewById(R.id.edit);

        web.loadUrl(edit.getText().toString());

        dol = new AlertDialog.Builder(this).create();

        web.setWebViewClient(new MyWebViewClient());

    }

   class MyWebViewClient extends WebViewClient{

       @Override
       public boolean shouldOverrideUrlLoading(WebView view, String url) {
           view.loadUrl(url);
           return super.shouldOverrideUrlLoading(view, url);
       }

       @Override
       public void onPageFinished(WebView view, String url) {
           if(bar.isShowing()){
               bar.dismiss();
           }
           super.onPageFinished(view, url);
       }

     /*  @Override
       public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
           Toast.makeText(MainActivity.this,"网页加载出错！",Toast.LENGTH_SHORT).show();
           dol.setTitle("ERROR");
       *//*    dol.setMessage();*//*

           super.onReceivedError(view, request, error);
       }*/
   }
}
