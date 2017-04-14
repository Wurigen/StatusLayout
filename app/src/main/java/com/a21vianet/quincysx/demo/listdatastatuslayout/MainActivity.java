package com.a21vianet.quincysx.demo.listdatastatuslayout;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.a21vianet.quincysx.library.listdatastatuslayout.ListDataStatusLayout;

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

public class MainActivity extends AppCompatActivity {
    private ListDataStatusLayout mListDataStatusLayout;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mListDataStatusLayout.setStatus(msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListDataStatusLayout = (ListDataStatusLayout) findViewById(R.id.listdatastatus);

        mListDataStatusLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("我是点击", "=============" + v.getId() + "");
            }
        });

        mHandler.sendEmptyMessageDelayed(ListDataStatusLayout.EMPTY, 0);
        mHandler.sendEmptyMessageDelayed(ListDataStatusLayout.NETERROR, 5000);
        mHandler.sendEmptyMessageDelayed(ListDataStatusLayout.ERROR, 10000);
        mHandler.sendEmptyMessageDelayed(ListDataStatusLayout.SUCCESS, 15000);
    }
}
