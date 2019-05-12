# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
####################注释说明（开始）###############################
#忽略警告
#-ignorewarnings 
#抑制错误警告-->找不到com.xx.bbb.**包里面的类的相关引用等等
# -dontwarn
#所有类和所有方法不混淆  																		
# -keep class
#指明lib包的在工程中的路径 																		
# -libraryjars
#是否使用大小写混合 																		
# -dontusemixedcaseclassnames
#指定代码的压缩级别0 ~ 7 														
# -optimizationpasses 5
#是否使用大小写混合  															
# -dontusemixedcaseclassnames
#是否混淆第三方jar 如果应用程序引入的有jar包,并且想混淆jar包里面的class                                                      	
# -dontskipnonpubliclibraryclasses   
#混淆时是否做预校验                                               
# -dontpreverify
#混淆时是否记录日志                                                                  	
# -verbose
#混淆时所采用的算法                                                                       	
# -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*          
####################注释说明（结束）###############################

#忽略警告
-ignorewarnings
#是否使用大小写混合 
-dontusemixedcaseclassnames
#是否混淆第三方jar 如果应用程序引入的有jar包,并且想混淆jar包里面的class（最好不要混淆第三方的jar） 
#-dontskipnonpubliclibraryclasses
#指定代码的压缩级别0 ~ 7
-optimizationpasses 5
#混淆时是否记录日志
-verbose

############################################Android默认混淆配置（开始）#############################################
# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
#是否对类内部代码进行优化，默认优化(如果需要删除日志输出代码则不能配置这个)
# -dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**
############################################Android默认混淆配置（结束）#############################################

#####视图模板类库混淆配置####
-keep public class * implements com.zftlive.android.library.base.templet.IViewTemplet {
    public <methods>;
}
-keep public class * extends com.zftlive.android.library.base.templet.AbsViewTemplet {
    public <methods>;
}
-keep public class * extends com.zftlive.android.library.base.adapter.RecyclerViewTemplet {
    public <methods>;
}

############################################Android删除日志输出代码配置（开始）#############################################
# 日志输出
-assumenosideeffects class android.util.Log {
   public static boolean isLoggable(java.lang.String, int);
   public static int v(...);
   public static int i(...);
   public static int w(...);
   public static int d(...);
   public static int e(...);
}
# 系统打印
-assumenosideeffects class java.io.PrintStream{
    public void print(...);
    public void println(...);
}
############################################Android删除日志输出代码配置（结束）#############################################

############################################Android四大组件/基本类-组件/数据模型混淆配置（开始）#############################################
-keep public class * extends android.app.Activity
-keep public class * extends android.app.SherlockActivity
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
############################################Android四大组件/基本类-组件/数据模型混淆配置（结束）#############################################


############################################Application/R/V4辅助包混淆配置（开始）#############################################
#Application不进行混淆，拷贝时注意记得修改成自己APP的包
-keep public class com.zftlive.android.GlobalApplication

#当前工程的R文件不进行混淆，必须保留
-keep public class com.zftlive.android.R
-keep class com.zftlive.android.R$* {   *;  }
-keepclassmembers class com.zftlive.android.R$* {
    <fields>;
    <methods>;
}

#android辅助包
#-libraryjars libs/android-support-v4.jar
-dontwarn android.support.v4.**
-keep class android.support.v4.**
-keep interface android.support.v4.app.** {*;}
############################################Application/R/V4辅助包混淆配置（结束）#############################################


############################################zftlive-xxx或者Ajava-Android-lib-Vxxx.jar（开始）#############################################
#类库R文件不能混淆，必须保留
-keep public class com.zftlive.android.library.R
-keep class com.zftlive.android.library.R$* {   *;  }
-keepclassmembers class com.zftlive.android.library.R$* {
    <fields>;
    <methods>;
}
#网络检测类（防止找不到构造方法）
-keep public class com.zftlive.android.library.tools.ToolNetwork
-keepclassmembers class com.zftlive.android.library.tools.ToolNetwork{
    public *;
}
#分享工具类（防止找不到构造方法）
-keep public class com.zftlive.android.library.tools.ToolShareSDK
-keepclassmembers class com.zftlive.android.library.tools.ToolShareSDK{
    public *;
}
#数据库操作类（防止找不到构造方法）
-keep public class com.zftlive.android.library.tools.ToolDatabase
-keepclassmembers class com.zftlive.android.library.tools.ToolDatabase{
    public *;
}
#保留所有序列化的实体类名、字段、方法，使用了GSON或者数据库映射
-keep public class * extends com.zftlive.android.library.base.BaseEntity
-keepclasseswithmembers class * extends com.zftlive.android.library.base.BaseEntity{
    <fields>;
    <methods>;
}
#自定义控件保留，防止找不到类（使用包名通配和类继承关系）
-keep public class * extends com.zftlive.android.library.base.BaseView
-keepclasseswithmembers class * extends com.zftlive.android.library.base.BaseView{
    <fields>;
    <methods>;
}
-dontwarn com.zftlive.android.library.widget.**
-keep class com.zftlive.android.library.widget.** {*;}
#集成的第三方开源类库不混淆
-dontwarn com.zftlive.android.library.third.**
-keep class com.zftlive.android.library.third.** {*;}

############################################zftlive-xxx或者Ajava-Android-lib-Vxxx.jar（结束）#############################################


############################################第三方jar混淆保留配置（开始）#############################################
####################信鸽推送（开始）#####################
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.**  {*;}
-keep class com.tencent.mid.**  {*;}
####################信鸽推送（结束）#####################

####################百度地图、定位、LBS相关jar（开始）#####################
-dontwarn com.baidu.**
-keep class com.baidu.** {*;}

-dontwarn vi.com.gdi.bgl.android.java.**
-keep class vi.com.gdi.bgl.android.java.** {*;}
####################百度地图、定位、LBS相关jar（结束）#####################

####################谷歌开源项目相关zxing/gson/volley/RoboGuice2（开始）#####################
#谷歌zxing二维码相关jar
-dontwarn com.google.zxing.**
-keep class com.google.zxing.** {*;}

#谷歌GSON
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

#谷歌Volley
-dontwarn com.android.http.**
-keep class com.android.http.** {*;}
-dontwarn com.android.volley.**
-keep class com.android.volley.** {*;}

#谷歌  RoboGuice2框架相关jar
-dontwarn com.google.inject.**
-keep class com.google.inject.** {*;}
-dontwarn roboguice.**
-keep class roboguice.** {*;}
-dontwarn javax.annotation.**
-keep class javax.annotation.** {*;}
-dontwarn javax.inject.**
-keep class javax.inject.** {*;}
####################谷歌开源项目相关zxing/gson/volley/RoboGuice2（结束）#####################

####################ShareSdk相关jar（开始）###########################
#mob工具类/Log相关
-dontwarn com.mob.**
-keep class com.mob.** {*;}

#sharesdk社会化组件
-dontwarn cn.sharesdk.**
-keep class cn.sharesdk.** {*;}

#发送短信验证码jar
#-libraryjars libs/SMSSDK-1.2.2.jar
-dontwarn cn.smssdk.**
-keep class cn.smssdk.** {*;}

-dontwarn a.a.a.a.**
-keep class a.a.a.a.** {*;}
####################ShareSdk相关jar（结束）###########################

####################环信即时通讯IM相关jar（开始）###########################
-keep class org.xmlpull.** {*;}
-keep class com.baidu.** {*;}
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
-keep class com.squareup.picasso.* {*;}
-keep class com.easemob.* {*;}
-keep class com.easemob.chat.** {*;}
-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils,注意前面的包名，
#不要SmileUtils复制到自己的项目下keep的时候还是写的demo里的包名
-keep class com.easemob.chatuidemo.utils.SmileUtils {*;}
#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}
####################环信即时通讯IM相关jar（结束）###########################

####################SOAP访问第三方jar ksoap2-android.jar（开始）#####################
-dontwarn org.kobjects.**
-keep class org.kobjects.** {*;}
-dontwarn org.ksoap2.**
-keep class org.ksoap2.** {*;}
-dontwarn org.kxml2.**
-keep class org.kxml2.** {*;}
-dontwarn org.xmlpull.**
-keep class org.xmlpull.** {*;}
####################SOAP访问第三方jar ksoap2-android.jar（结束）#####################

####################nineoldandroids（开始）#####################
-dontwarn com.nineoldandroids.**
-keep class com.nineoldandroids.** {*;}
####################nineoldandroids（结束）#####################

####################achartengine（开始）#####################
-dontwarn org.achartengine.**
-keep class org.achartengine.** {*;}
####################achartengine（结束）#####################

####################滑动菜单SlidingMenu相关jar（开始）##################
#-dontwarn com.jeremyfeinstein.slidingmenu.lib.**
#-keep class com.jeremyfeinstein.slidingmenu.lib.** {*;}
####################滑动菜单SlidingMenu相关jar（结束）###################

####################ActionBarSherlock相关jar（开始）####################
#-dontwarn com.actionbarsherlock.**
#-keep class com.actionbarsherlock.** {*;}
####################ActionBarSherlock相关jar（结束）####################

####################图片异步加载universal-image-loader（开始）#####################
#-dontwarn com.nostra13.universalimageloader.**
#-keep class com.nostra13.universalimageloader.** {*;}
####################图片异步加载universal-image-loader（结束）#####################

####################ormlite数据库操作相关jar（开始）#####################
#-libraryjars libs/ormlite-android-4.48.jar
#-libraryjars libs/ormlite-core-4.48.jar
#-dontwarn com.j256.ormlite.**
#-keep class com.j256.ormlite.** {*;}
####################ormlite数据库操作相关jar（结束）#####################

####################网络通信相关jar（开始）###########################
#-libraryjars libs/android-async-http-1.4.5.jar
#-dontwarn com.loopj.android.http.**
#-keep class com.loopj.android.http.** {*;}
####################网络通信相关jar（结束）############################

####################asmack操作相关jar（开始）#####################
#-dontwarn com.kenai.jbosh.**
#-keep class com.kenai.jbosh.** {*;}
#-dontwarn com.novell.sasl.client.**
#-keep class com.novell.sasl.client.** {*;}
#-dontwarn de.measite.smack.**
#-keep class de.measite.smack.** {*;}
#-dontwarn org.xbill.DNS.**
#-keep class org.xbill.DNS.** {*;}
#-dontwarn org.jivesoftware.smack.**
#-keep class org.jivesoftware.smack.** {*;}
#-dontwarn org.jivesoftware.smackx.**
#-keep class org.jivesoftware.smackx.** {*;}
#-dontwarn org.apache.qpid.management.common.sasl.**
#-keep class org.apache.qpid.management.common.sasl.** {*;}
#-dontwarn org.apache.harmony.javax.security.**
#-keep class org.apache.harmony.javax.security.** {*;}
####################asmack操作相关jar（结束）#####################

####################poi辅助包（开始）#####################
#-libraryjars libs/poi-3.9-20121203.jar
#-libraryjars libs/poi-scratchpad-3.9-20121203.jar
#-dontwarn org.apache.poi.**
#-keep class org.apache.poi.** {*;}
####################poi辅助包（结束）#####################

####################表单验证插件 android-validation-komensky0.9.3.jar（开始）#####################
#-dontwarn eu.inmite.android.lib.**
#-keep class eu.inmite.android.lib.** {*;}
####################表单验证插件 android-validation-komensky0.9.3.jar（结束）#####################

####################XStream操作xml与Bean之间转换jar（开始）#####################
#-dontwarn com.thoughtworks.xstream.**
#-keep class com.thoughtworks.xstream.** {*;}
####################XStream操作xml与Bean之间转换jar（结束）#####################

# Bugly混淆配置
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

############################################第三方jar混淆保留配置（结束）#############################################