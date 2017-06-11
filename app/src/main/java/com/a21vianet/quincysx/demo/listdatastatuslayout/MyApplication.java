package com.a21vianet.quincysx.demo.listdatastatuslayout;

import android.app.Application;

import com.a21vianet.quincysx.library.listdatastatuslayout.ListDataStatusLayout;
import com.a21vianet.quincysx.library.listdatastatuslayout.viewfactory.impl.DefStateViewFactory;

import static com.a21vianet.quincysx.library.listdatastatuslayout.ListDataStatusLayout.EMPTY;
import static com.a21vianet.quincysx.library.listdatastatuslayout.ListDataStatusLayout.NETERROR;

/**
 * Created by quincysx on 2017/6/11.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DefStateViewFactory defStateViewFactory = new DefStateViewFactory();

        ListDataStatusLayout.Builder builder = ListDataStatusLayout.getBuilder()
                .addStateView(NETERROR, defStateViewFactory.getStatusView(this, NETERROR))
                .addStateView(ListDataStatusLayout.ERROR, defStateViewFactory.getStatusView(this, ListDataStatusLayout.ERROR))
                .addStateView(ListDataStatusLayout.SUCCESS, defStateViewFactory.getStatusView(this, ListDataStatusLayout.SUCCESS))
                .addStateView(EMPTY, defStateViewFactory.getStatusView(this, EMPTY));
    }
}
