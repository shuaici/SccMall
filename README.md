>作者：帅次
>
>作者简介：CSDN博客专家、华为云享专家认证
>
>公众号「帅次」：分享 Android 相关知识·面试题库。
# 🔥 一、组件化

作为一个单工程撸到底的开发人员，想试着将项目进行组件化改造，说动就动。毕竟技术都是写出来的，看看感觉懂了，但是实际开发中还是能遇到各种各样的问题，开始搞起来。

## 💥 1.1 为什么使用组件化

一直使用单工程操作，项目越来越大导致出现了不少的问题：

- **查找问题慢**：定位问题，需要在多个代码混合的模块中寻找和跳转。

- **开发维护成本增加**：避免代码的改动影响其它业务的功能，导致开发和维护成本不断增加。
- **编译时间长**：项目工程越大，编译完整代码所花费的时间越长。
- **开发效率低**：多人协作开发时，开发风格不一，又很难将业务完全分割，大家互相影响，导致开发效率低下。
- **代码复用性差**：写过的代码很难抽离出来再次利用。

## 💥 1.2 模块化与组件化

### 🌀 1.2.1 模块

将**一个程序按照其功能做拆分，分成相互独立的模块**，以便于每个模块只包含与其功能相关的内容，比如**登录模块**、**首页模块**等等。

### 🌀 1.2.2 组件

组件指的是单一的功能组件，如**登录组件**、**视频组件**、**支付组件** 等，每个组件都可以以一个单独的 module 开发，并且可以单独抽出来作为 SDK 对外发布使用。可以说往往一个模块包含了一个或多个组件。

## 💥 1.3 组件化的优势

组件化基于可重用的目的，将应用拆分成多个独立组件，以**减少耦合**:
- **加快编译速度**：每个业务功能都是一个单独的工程，可独立编译运行，拆分后代码量较少，编译自然变快。

- **解耦**：通过关注点分离的形式，将App分离成多个模块，每个模块都是一个组件。
- **提高开发效率**：多人开发中，每个组件模块由单人负责，降低了开发之间沟通的成本，减少因代码风格不一而产生的相互影响。
- **代码复用**：类似我们引用的第三方库，可以将基础组件或功能组件剥离。在新项目微调或直接使用。

## 💥 1.4 组件化需要解决的问题

- 组件分层：怎么将一个项目分成多个组件、组件间的依赖关系是怎么样的?

- 组件单独运行和集成调试：组件是如何独立运行和集成调试的？
- 组件间通信:主项目与组件、组件与组件之间如何通信就变成关键?

# 🔥 二、组件分层

组件依赖关系是上层依赖下层，修改频率是上层高于下层。先上一张图：

![](https://files.mdnice.com/user/16804/cedf8ea5-7f77-4509-8034-2ea0254c1907.png)

## 💥 2.1 基础组件

基础公共模块，最底层的库：

- 封装公用的基础组件;
- 网络访问框架、图片加载框架等主流的第三方库;
- 各种第三方SDK。

## 💥 2.2 common组件(lib_common)

- 支撑业务组件、功能组件的基础（BaseActivity/BaseFragment等基础能力;
- 依赖基础组件层;
- 业务组件、功能组件所需的基础能力只需要依赖common组件即可获得。

## 💥 2.3 功能组件

- 依赖基础组件层;
- 对一些公用的功能业务进行封装与实现;
- 业务组件可以在library和application之间切换，但是最后打包时必须是library ;

## 💥 2.4 业务组件

- 可直接依赖基础组件层;同时也能依赖公用的一些功能组件;
- 各组件之间不存在依赖关系，通过路由进行通信;
- 业务组件可以在library和application之间切换，但是最后打包时必须是library ;

## 💥 2.5 主工程(app)

- 只依赖各业务组件;

- 除了一些全局的配置和主Activity之外，不包含任何业务代码，是应用的入口;

## 💥 完成后项目

![](https://files.mdnice.com/user/16804/1ca04dcb-1b97-4e50-916c-30d5687dd0a9.png)

这只是个大概，并不是说必须这样，可以按照自己的方式来。比如：你觉得基础组件比较多导致project里面的项目太多，那么你可以创建一个lib_base，然在lib_base里面再创建其他基础组件即可。

# 🔥 三、组件单独调试

## 💥 3.1 创建组件(收藏)

![](https://files.mdnice.com/user/16804/f000d1a7-e4ca-4d22-83bd-ab511ef1c12e.png)

- library和application之间切换：选择第一项。

- 始终是library：选择第二项

这样尽可能的减少变动项，当然这仅仅是个建议，看个人习惯吧。

因为咱们创建的是一个module，所以在AndridManifest中添加android:exported="true"属性可直接构建一个APK。下面咱们看看如何生成不同的工程类型。

## 💥 3.2 动态配置组件的工程类型

在 AndroidStudio 开发 Android 项目时，使用的是 Gradle 来构建，具体来说使用的是 Android Gradle 插件来构建，Android Gradle 中提供了三种插件，在开发中可以通过配置不同的插件来构建不同的工程。

### 🌀 3.2.1 build.gradle(module)
```java
//构建后输出一个 APK 安装包
apply plugin: 'com.android.application'
//构建后输出 ARR 包
apply plugin: 'com.android.library'
//配置一个 Android Test 工程
apply plugin: 'com.android.test'
```

**独立调试**：设置为 Application 插件。

**集成调试**：设置为 Library 插件。

### 🌀 3.2.2 设置gradle.properties

![](https://files.mdnice.com/user/16804/7f7f5575-2413-4b41-a7f0-0f060c2bb204.png)

isDebug = true 独立调试

### 🌀 3.2.3 动态配制插件(build.gradle)

```java
//注意gradle.properties中的数据类型都是String类型，使用其他数据类型需要自行转换
if(isDebug.toBoolean()){
    //构建后输出一个 APK 安装包
    apply plugin: 'com.android.application'
}else{
    //构建后输出 ARR 包
    apply plugin: 'com.android.library'
}
```
## 💥 3.3 动态配置组件的 ApplicationId 和 AndroidManifest 文件

- 一个 APP 是只有一个 **ApplicationId** ，所以在**单独调试**和**集成调试**组件的 ApplicationId 应该是不同的。

- **单独调试**时也是需要有一个启动页，当集成调试时主工程和组件的AndroidManifest文件合并会产生多个启动页。

根据上面动态配制插件的经验，我们也需要在build.gradle中动态配制ApplicationId 和 AndroidManifest 文件。

### 🌀 3.3.1 准备两个不同路径的 AndroidManifest 文件

![](https://files.mdnice.com/user/16804/d3349f72-93c8-4ad3-9405-7d8d59a3ee97.png)

有什么不同？咱们一起看看具体内容。

### 🌀 3.3.2 src/main/debug/AndroidManifest
```html
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.scc.module.collect">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.SccMall">
        <activity android:name=".CollectActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
### 🌀 3.3.3 src/main/debug/AndroidManifest
```html
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.scc.module.collect">
    <application
        android:allowBackup="true"
        android:supportsRtl="true"
        >
        <activity android:name=".CollectActivity"/>
    </application>

</manifest>
```
### 🌀 3.3.4 动态配制(build.gradle)

```java
    defaultConfig {
        if(isDebug.toBoolean()){
            //独立调试的时候才能设置applicationId
            applicationId "com.scc.module.collect"
        }
    }
    sourceSets {
        main {
            if (isDebug.toBoolean()) {
                //独立调试
                manifest.srcFile 'src/main/debug/AndroidManifest.xml'
            } else {
                //集成调试
                manifest.srcFile 'src/main/AndroidManifest.xml'
            }
        }
    }
```

## 💥 3.4 实现效果

### 🌀 3.4.1 独立调试

isDebug = true

![](https://files.mdnice.com/user/16804/6d6b4c7a-a2d3-4022-b4ed-847c9436cb28.png)

### 🌀 3.4.2 集成调试

isDebug = false

![](https://files.mdnice.com/user/16804/21c5e08b-72d7-4c79-b01e-3da1034a5c72.png)

# 🔥 四、Gradle配置统一管理

## 💥 4.1 config.gradle
当我们需要进行插件版本、依赖库版本升级时，项目多的话改起来很麻烦，这时就需要我们对Gradle配置统一管理。如下：

![](https://files.mdnice.com/user/16804/77598325-1567-41d7-931e-51d465fb486c.png)

具体内容

```java
ext{
    //组件独立调试开关, 每次更改值后要同步工程
    isDebug = true
    android = [
            // 编译 SDK 版本
            compileSdkVersion: 31,
            // 最低兼容 Android 版本
            minSdkVersion    : 21,
            // 最高兼容 Android 版本
            targetSdkVersion : 31,
            // 当前版本编号
            versionCode      : 1,
            // 当前版本信息
            versionName      : "1.0.0"
    ]
    applicationid = [
            app:"com.scc.sccmall",
            main:"com.scc.module.main",
            webview:"com.scc.module.webview",
            login:"com.scc.module.login",
            collect:"com.scc.module.collect"
    ]
    dependencies = [
            "appcompat"         :'androidx.appcompat:appcompat:1.2.0',
            "material"          :'com.google.android.material:material:1.3.0',
            "constraintlayout"  :'androidx.constraintlayout:constraintlayout:2.0.1',
            "livedata"          :'androidx.lifecycle:lifecycle-livedata:2.4.0',
            "viewmodel"         :'androidx.lifecycle:lifecycle-viewmodel:2.4.0',
            "legacyv4"          :'androidx.legacy:legacy-support-v4:1.0.0',
            "splashscreen"      :'androidx.core:core-splashscreen:1.0.0-alpha01'
    ]
    libARouter= 'com.alibaba:arouter-api:1.5.2'
    libARouterCompiler = 'com.alibaba:arouter-compiler:1.5.2'
    libGson = 'com.google.code.gson:gson:2.8.9'
}

```

## 💥 4.2 添加配制文件build.gradle(project)
```java
apply from:"config.gradle"
```
## 💥 4.3 其他组件使用
```java
//build.gradle
//注意gradle.properties中的数据类型都是String类型，使用其他数据类型需要自行转换
if(isDebug.toBoolean()){
    //构建后输出一个 APK 安装包
    apply plugin: 'com.android.application'
}else{
    //构建后输出 ARR 包
    apply plugin: 'com.android.library'
}
android {
    compileSdkVersion 31

    defaultConfig {
        if(isDebug.toBoolean()){
            //独立调试的时候才能设置applicationId
            applicationId "com.scc.module.collect"
        }
        minSdkVersion 21
        targetSdkVersion 31
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets {
        main {
            if (isDebug.toBoolean()) {
                //独立调试
                manifest.srcFile 'src/main/debug/AndroidManifest.xml'
            } else {
                //集成调试
                manifest.srcFile 'src/main/AndroidManifest.xml'
            }
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
//    implementation root.dependencies.appcompat
//    implementation root.dependencies.material
//    implementation root.dependencies.constraintlayout
//    implementation root.dependencies.livedata
//    implementation root.dependencies.viewmodel
//    implementation root.dependencies.legacyv4
//    implementation root.dependencies.splashscreen
//    implementation root.libARouter
    //上面内容在lib_common中已经添加咱们直接依赖lib_common
    implementation project(':lib_common')

    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
}
```

# 🔥 五、组件间界面跳转(ARouter)

## 💥 5.1 介绍
Android 中的界面跳转那是相当简单，但是在组件化开发中，由于不同组件式没有相互依赖的，所以不可以直接访问彼此的类，这时候就没办法通过显式的方式实现了。

所以在这里咱们采取更加灵活的一种方式，使用 Alibaba 开源的 [ARouter](https://github.com/alibaba/ARouter) 来实现。

>一个用于帮助 Android App 进行组件化改造的框架 —— 支持模块间的路由、通信、解耦

文档介绍的蛮详细的，感兴趣的可以自己实践一下。这里做个简单的使用。

## 💥 5.2 使用

### 🌀 5.2.1 添加依赖

先在统一的**config.gradl**e添加版本等信息
```java
ext{
    ...
    libARouter= 'com.alibaba:arouter-api:1.5.2'
    libARouterCompiler = 'com.alibaba:arouter-compiler:1.5.2'
}
```

因为所有的功能组件和业务组件都依赖lib_common，那么咱们先从lib_common开始配制

**lib_common**
```java
dependencies {
    api root.libARouter
    ...
}
```

**其他组件(如collect)**

```java
android {
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
                //如果项目内有多个annotationProcessor，则修改为以下设置
                //arguments += [AROUTER_MODULE_NAME: project.getName()]
            }
        }
    }
}

dependencies {
    //arouter-compiler的注解依赖需要所有使用 ARouter 的 module 都添加依赖
    annotationProcessor root.libARouterCompiler
    ...
}
```

### 🌀 5.2.2 添加注解

你要跳转的**Activity**

```java
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/collect/CollectActivity")
public class CollectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
    }
}
```

### 🌀 5.2.3 初始化SDK（主项目Application）

```java
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
    private boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
```
## 💥 5.3 发起路由操作

### 🌀 5.3.1 应用内简单的跳转

```java
ARouter.getInstance().build("/collect/CollectActivity").navigation();
```

这里是用**module_main**的HomeFragment跳转至**module_collect**的CollectActivity界面，两个module中不存在依赖关系。`"/collect/CollectActivity"`在上面已注册就不多描述了。

效果如下：

![](https://files.mdnice.com/user/16804/7264f49e-b1c7-4311-991b-b194106204db.gif)

### 🌀 5.3.2 跳转并携带参数

这里是用**module_main**的MineFragment的Adapter跳转至**module_webview**的WebViewActivity界面，两个module中同样不存在依赖关系。

**启动方**

```java
        ARouter.getInstance().build("/webview/WebViewActivity")
                .withString("url", bean.getUrl())
                .withString("content",bean.getName())
                .navigation();
```
这里传了两个参数**url**和**name**到WebViewActivity，下面咱们看看WebViewActivity怎么接收。

**接收方**

```java
//为每一个参数声明一个字段，并使用 @Autowired 标注
//URL中不能传递Parcelable类型数据，通过ARouter api可以传递Parcelable对象
//添加注解(必选)
@Route(path = "/webview/WebViewActivity")
public class WebViewActivity extends BaseActivity<ActivityWebviewBinding, WebViewViewModel> {
    //发送方和接收方定义的key名称相同则无需处理
    @Autowired
    public String url;
    //通过name来映射URL中的不同参数
    //发送方定义key为content，我们用title来接收
    @Autowired(name = "content")
    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注入参数和服务(这里用到@Autowired所以要设置)
        //不使用自动注入,可不写，如CollectActivity没接收参数就没有设置
        ARouter.getInstance().inject(this);
        binding.btnBoom.setText(String.format("%s,你来啦", title));
        //加载链接
        initWebView(binding.wbAbout, url);
    }
}
```
上效果图：

![](https://files.mdnice.com/user/16804/8bd28101-7c93-49ba-9930-856e238ad674.gif)

搞定，更多高级玩法可自行探索。

### 🌀 5.3.3 小记(ARouter目标不存在)

**W/ARouter::: ARouter::There is no route match the path**

这里出现个小问题，配置注释都好好的，但是发送发无论如何都找不到设置好的Activity。尝试方案：
- Clean Project
- Rebuild Project
- 在下图也能找到ARouter内容。

后来修改Activity名称好了。

![](https://files.mdnice.com/user/16804/2b8340a2-2bd0-4ca3-bfd5-285619635eda.png)

# 🔥 六、组件间通信(数据传递)

界面跳转搞定了，那么数据传递怎么办，我在module_main中使用悬浮窗，但是需要判断这个用户是否已登录，再执行后续逻辑，这个要怎么办？这里我们可以采用 **接口 + ARouter** 的方式来解决。

在这里可以添加一个 componentbase 模块，**这个模块被所有的组件依赖**。

这里我们通过 **module_main组件** 中调用 **module_login组件** 中的方法来获取登录状态这个场景来演示。

## 💥 6.1 通过依赖注入解耦:服务管理(一) 暴露服务

### 🌀 6.1.1 创建 componentbase 模块(lib)

![](https://files.mdnice.com/user/16804/9683da26-7bca-494e-812d-9b34996992e9.png)

### 🌀 6.1.2 创建接口并继承IProvider

>注意:接口必须继承IProvider，是为了使用ARouter的实现注入。

![](https://files.mdnice.com/user/16804/7ed57485-102e-4abe-ad82-ac65b9449ddb.png)

### 🌀 6.1.3 在module_login组件中实现接口

**lib_common**

所有业务组件和功能组件都依赖lib_common，所以咱们直接在lib_common添加依赖即可
```java
dependencies {
    ...
    api project(":lib_componentbase")
}
```

**module_login**

```java
dependencies {
    ...
    implementation project(':lib_common')
}
```
**实现接口**
```java
//实现接口
@Route(path = "/login/AccountServiceImpl")
public class AccountServiceImpl implements IAccountService {
    @Override
    public boolean isLogin() {
        MLog.e("AccountServiceImpl.isLogin");
        return true;
    }

    @Override
    public String getAccountId() {
        MLog.e("AccountServiceImpl.getAccountId");
        return "1000";
    }

    @Override
    public void init(Context context) {

    }
}
```

![](https://files.mdnice.com/user/16804/97f5a2a6-0617-42cd-8087-cc9baf5ba684.png)

## 💥 6.2 通过依赖注入解耦:服务管理(二) 发现服务

### 🌀 6.2.1 在module_main中调用调用是否已登入
```java
public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    @Autowired
    IAccountService accountService;
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ARouter.getInstance().inject(this);
        binding.frgmentHomeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MLog.e("Login:"+accountService.isLogin());
                MLog.e("AccountId:"+accountService.getAccountId());

            }
        });
    }
}
```

![](https://files.mdnice.com/user/16804/0be2d490-de0e-4046-9db5-7a5e64c300b7.png)
运行结果：
```java
E/-SCC-: AccountServiceImpl.isLogin
E/-SCC-: Login:true
E/-SCC-: AccountServiceImpl.getAccountId
E/-SCC-: AccountId:1000
```

# 🔥 七、总结

本文介绍了组件化、组件分层、解决了组件的独立调试、集成调试、页面跳转、组件通信等。

其实会了这些后你基本可以搭建自己的组件化项目了。其实最大的问题还是分组分层、组件划分。这个就需要根据你的实际情况来设置。

本项目比较糙，后面会慢慢完善。比如添加Gilde、添加MMVK、添加Room等。

[项目传送门](https://github.com/shuaici/SccMall)

## 💥 相关推荐

[Android OkHttp+Retrofit+Rxjava+Hilt实现网络请求框架](https://juejin.cn/post/7031479214683455502)

## 💥 参考与感谢

[“终于懂了” 系列：Android组件化，全面掌握！](https://juejin.cn/post/6881116198889586701#heading-26)

[Android 组件化最佳实践](https://juejin.cn/post/6844903649102004231#heading-19)

[手把手带你搭建一个优秀的Android项目架构](https://juejin.cn/post/7023377961503948808#heading-0)
