package my.benjamin.com.nar_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class osis extends AppCompatActivity {
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osis);
        WebView osisVeiw;
        logout=(Button)findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(osis.this, osis.class);
                startActivity(intent1);
            }
        });

        osisVeiw = (WebView) findViewById(R.id.osis);
        osisVeiw.getSettings().getJavaScriptEnabled();
        osisVeiw.setWebViewClient(new WebViewClient());
        osisVeiw.loadUrl("http://sip.ktu.edu.gh/public/login");


    }


}
