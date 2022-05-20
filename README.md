# RefInvokeUtil
该库是对于反射操作进行封装的库,由于Android 9开始对反射有限制,所以该库继承了FreeReflection库绕过这个限制.
FreeReflection库地址:https://github.com/tiann/FreeReflection
##使用
###1.在Application的attach机如下配置
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
###2.依赖配置
	        implementation 'com.github.FengLiuXt:RefInvokeUtil:1.0'

##3.代码配置,在Application中的attach回调加如下配置
@Override
protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    Reflection.unseal(base);
}
