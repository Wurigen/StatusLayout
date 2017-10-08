package com.quincysx.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quincysx.sample.view.EmptyView;
import com.quincysx.sample.view.ErrorView;
import com.quincysx.statuslayout.StatusLayout;
import com.quincysx.statuslayout.StatusView;

/**
 * Created by quincysx on 2017/10/8.
 */

public class TestFragment extends Fragment implements Handler.Callback {
    private StatusLayout mListDataStatusLayout;
    private Handler mHandler = new Handler(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_main, container, false);
        mListDataStatusLayout = inflate.findViewById(R.id.listdatastatus);

        mListDataStatusLayout.setCallback(new StatusView.Callback() {
            @Override
            public void onHandle(StatusView view) {
                Log.e("我是点击", "=============" + "" + view.getClass());
            }
        });

        mHandler.sendEmptyMessageDelayed(0, 0);
        mHandler.sendEmptyMessageDelayed(1, 5000);
        mHandler.sendEmptyMessageDelayed(2, 10000);
        mHandler.sendEmptyMessageDelayed(3, 15000);
        mHandler.sendEmptyMessageDelayed(4, 20000);
        return inflate;
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
