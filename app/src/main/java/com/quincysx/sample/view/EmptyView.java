package com.quincysx.sample.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quincysx.sample.R;
import com.quincysx.statuslayout.StatusView;

/**
 * Created by quincysx on 2017/10/4.
 */

public class EmptyView extends StatusView {

    @Override
    protected View initContentView() {
        View inflate = View.inflate(getContext(), R.layout.commonlayout, null);
        TextView textView = inflate.findViewById(R.id.common_hint);
        textView.setText("请求数据为Null");
        ImageView imageView = inflate.findViewById(R.id.common_image);
        Glide.with(getContext()).load(R.drawable.ic_data_status_empty).into(imageView);
        return inflate;
    }

    @Override
    protected void onFeedback() {
    }

}
