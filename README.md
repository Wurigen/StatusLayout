### 介绍
    这是一个状态页面的集合，方面在不同页面进行调用，View 完全支持自定义
    
    !()[demodata/example1.gif]

### 特色
    > 1. 支持全局设置各个页面
      1. 也可以单独在每个 Activity/Fragment 里进行单独设置
      
    > 1. 可以嵌套使用
      1. 也可以已覆盖方式使用
      
### 使用方式
Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
	dependencies {
	        compile 'com.github.QuincySx:ListDataStatusLayout:1.0.4'
	}
```
