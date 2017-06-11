### 介绍
这是一个状态页面的集合，方面在不同页面进行调用，View 完全支持自定义
    
![](demodata/example1.gif)

### 特色
> 1. 支持全局设置各个页面
> 1. 也可以单独在每个 Activity/Fragment 里进行单独设置
> 1. 可以嵌套使用
> 1. 也可以已覆盖方式使用
      
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
	        compile 'com.github.QuincySx:ListDataStatusLayout:1.0.4'
	}
```

3. 在您的代码的相关地方中添加以下代码

```
	DefStateViewFactory defStateViewFactory = new DefStateViewFactory();

	ListDataStatusLayout.Builder builder = ListDataStatusLayout.getBuilder()
                .addStateView(ListDataStatusLayout.NETERROR, defStateViewFactory.getStatusView(this, ListDataStatusLayout.NETERROR))
                .addStateView(ListDataStatusLayout.ERROR, defStateViewFactory.getStatusView(this, ListDataStatusLayout.ERROR))
                .addStateView(ListDataStatusLayout.SUCCESS, defStateViewFactory.getStatusView(this, ListDataStatusLayout.SUCCESS))
                .addStateView(ListDataStatusLayout.EMPTY, defStateViewFactory.getStatusView(this, ListDataStatusLayout.EMPTY));
```
此段代码采用的 DefStateViewFactory 来创建原生样式的布局 进行添加，您也可以 继承 IStateView 自己自定义这个 View ，然后再添加到 ListDataStatusLayout 里面。

在此处设置 IStateView 会影响全局

4. XML 写法

```
    <com.a21vianet.quincysx.library.listdatastatuslayout.ListDataStatusLayout
        android:id="@+id/listdatastatus"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="match_parent"
            android:text="加載成功"
            android:layout_height="match_parent"/>
    </com.a21vianet.quincysx.library.listdatastatuslayout.ListDataStatusLayout>

```

5. 局部代码

```
	mListDataStatusLayout = (ListDataStatusLayout) findViewById(R.id.listdatastatus);

        mListDataStatusLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("我是点击", "=============" + v.getId() + "");
            }
        });
        mListDataStatusLayout.setStatus(ListDataStatusLayout.EMPTY);
        
        //可以使用此方法临时添加 状态 View ，在此设置 不会影响其他页面的 View
        mListDataStatusLayout.addStateView(int,IStateView);
```
