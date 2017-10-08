### 介绍
这是一个状态页面的集合，方面在不同页面进行调用，View 完全支持自定义
    
![](demodata/example1.gif)

### 特色
> 1. 继承 StatusView 的页面就可以显示
    
### 使用方式
1. 在你的项目的 build.gradle 添加以下代码
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2. 在你的 Library 中添加如下代码
```
dependencies {
	...
	compile 'com.github.QuincySx:ListDataStatusLayout:1.1.0'
}
```

3. 书写 Xml

```
 <com.quincysx.statuslayout.StatusLayout
        android:id="@+id/listdatastatus"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center"
            android:text="加載成功"/>
    </com.quincysx.statuslayout.StatusLayout>
```

4. 继承 StatusView 实现 initContentView() 方法 创建 View

示例：
```
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
        //点击事件的回调
    }

}
```
5. Activity/Fragment 写法

```
mListDataStatusLayout = (ListDataStatusLayout) findViewById(R.id.listdatastatus);

mListDataStatusLayout.setCallback(new StatusView.Callback() {
     @Override
     public void onHandle(StatusView view) {
         //点击任意页面的回调 view 是被点击的页面
          Log.e("我是点击", "=============" + "");
     }
});
//传入自己继承的 StatusView 的 class 就会相应的显示页面
mListDataStatusLayout.showAction(EmptyView.class);
//显示你自己的页面
mListDataStatusLayout.showSuccess();
```
