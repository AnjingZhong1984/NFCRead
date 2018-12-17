package com.fafa.pos.nfcread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.chariotsolutions.nfc.plugin.NfcPlugin;
import com.fafa.pos.nfcliberay.ui.RunAppActivity;

public class MainActivity extends AppCompatActivity {

    private Context contex;

    private NfcPlugin nfcPlugin;

    public static  TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        nfcPlugin = new NfcPlugin();
//        nfcPlugin.initActivity(this);
//        nfcPlugin.registerNdef(null);


        Activity me = this;
        Button button = findViewById(R.id.button2);
        tv = findViewById(R.id.hello);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                me.startActivity(new Intent(me, RunAppActivity.class));
            }
        });
    }

    public String nfcMessage() {
        return nfcPlugin.connect(Ndef.class.getName(), 5000, NfcPlugin.defCall);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println("======================");
    }

    public static void setTextValue(){
        tv.setText(RunAppActivity.value);
        RunAppActivity.cleanValue();
    }
}
