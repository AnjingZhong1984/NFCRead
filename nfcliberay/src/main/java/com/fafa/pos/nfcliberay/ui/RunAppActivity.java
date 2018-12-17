/*
 * Copyright (c) 2016-2018 fafa.com.cn. All Rights Reserved.
 */
package com.fafa.pos.nfcliberay.ui;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.fafa.pos.nfcliberay.R;
import com.fafa.pos.nfcliberay.base.BaseNfcActivity;

/**
 * 自动运行程序
 */
public class RunAppActivity extends BaseNfcActivity {

    public static String value = "";

    public static String getValue() {
        return value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_run_app);
        System.out.println("-----on create-----");
        cleanValue();
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onNewIntent(Intent intent) {
        //1.获取Tag对象
        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String s = bytesToHexString(detectedTag.getId());
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        value = s;
        //Send the Order ???
        getIntent().putExtra("tagId",s);
        finish();
    }

    @Override
    protected void onDestroy() {
        System.out.println("--on destry----");
        super.onDestroy();
    }

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (byte b : src) {
            buffer[0] = Character.forDigit((b >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(b & 0x0F, 16);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }

    public static void cleanValue() {
        System.out.println("--------------clear value---------");
        value = "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
