package com.example.hackathon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.text.Editable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.ActionBar.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {
    EditText str;
    TextView t1;
    Button btn;
    //public int j=0, i = 0;
    Timer timer;
    String flink = "";
    String slink = "https://submit.jotform.me/submit/";
    ArrayList<String> ip, label;
    String[] fnum = new String[4];
    final StringBuilder builder = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int count = 50;

        final StringBuilder builder = new StringBuilder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1 = (TextView) findViewById(R.id.result);
        ip = getIntent().getStringArrayListExtra("arlist");
        label = getIntent().getStringArrayListExtra("label");
        flink = getIntent().getStringExtra("link");
        fnum = flink.split("/");
        slink += fnum[3];
        slink += "?";
        final RelativeLayout lm = (RelativeLayout) findViewById(R.id.activity_main);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for(int i=0;i<ip.size();i++) {
            builder.append(ip.get(i)+": \n");
           /* RelativeLayout ll = new RelativeLayout(this);
            TextView product = new TextView(this);
            product.setText(ip.get(i));
            product.layout();
            ll.addView(product);
            lm.addView(ll);*/
        }
        t1.setText(builder.toString());

    }
    public void getSpeechInput(View view) {

        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        //timer = new Timer();
        //timer.schedule(new TimerTask() {
          //  @Override
          //  public void run() {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
            //}
        //}, 10000);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int j = 0;
        int i = 0, flag = 0;
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String str = "";
                    ArrayList<String> resp = new ArrayList<>(ip.size());
                    String[] s = (result.get(0)).split(" ");
                    System.out.println(slink);
                    //System.out.println(result.get(0).split(" "));

                    for(int k = 0;k < s.length;k++)
                        System.out.println(s[k]+ "--" + k);

      /*              while(i < s.length) {
                        try {
                            if(i % 2 == 0) {
                                str = str + s[i] + ": ";
                                slink = slink + label.get(j) + "=";
                                j = j + 1;
                                i = i + 1;
                            }
                            else {
                                str = str + s[i] + "\n";
                                slink = slink + s[i];
                                if(j != (s.length - 1))
                                    slink = slink + "&";
                                i = i + 1;
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println(e.toString());
                        }
                    }
*/
                    while(i < s.length)
                    {
                        if((s[i].equalsIgnoreCase(ip.get(j)) && (flag == 0)))
                        {
                        str = str + ip.get(j) + ": ";
                            slink = slink + ip.get(j) + "=";
                            i = i + 1;
                            j = j + 1;
                            flag = 1;
                        }

                    }
                    t1.setText(str);
                    str = "";
                    }
                    break;
        }
        System.out.println(slink);
        Intent submit_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(slink));
        startActivity(submit_intent);
    }

}
/*https://submit.jotform.me/submit/93325521458458?q6_name=rushabhpatel&q4_university4=panditdeendayalpetroleumuniversity&q5_branch=computer*/