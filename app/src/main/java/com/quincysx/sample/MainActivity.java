package com.quincysx.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.quincysx.sample.view.EmptyView;
import com.quincysx.sample.view.ErrorView;
import com.quincysx.statuslayout.StatusLayout;
import com.quincysx.statuslayout.StatusView;

/**
 * Copyright 2017 QuincySx
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    private StatusLayout mListDataStatusLayout;
    private Handler mHandler = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListDataStatusLayout = findViewById(R.id.listdatastatus);

        mListDataStatusLayout.setCallback(new StatusView.Callback() {
            @Override
            public void onHandle(StatusView view) {
                Log.e("我是点击", "=============" + "");
            }
        });

        mHandler.sendEmptyMessageDelayed(0, 0);
        mHandler.sendEmptyMessageDelayed(1, 5000);
        mHandler.sendEmptyMessageDelayed(2, 10000);
        mHandler.sendEmptyMessageDelayed(3, 15000);
        mHandler.sendEmptyMessageDelayed(4, 20000);
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                mListDataStatusLayout.showAction(EmptyView.class);
                break;
            case 1:
                mListDataStatusLayout.showAction(ErrorView.class);
                break;
            case 2:
                mListDataStatusLayout.showSuccess();
                break;
            case 3:
                mListDataStatusLayout.showAction(ErrorView.class);
                break;
            case 4:
                mListDataStatusLayout.showSuccess();
                break;
        }
        return true;
    }
}
