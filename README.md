# netlib

[![](https://jitpack.io/v/yezizaiqiutian/netlib.svg)](https://jitpack.io/#yezizaiqiutian/netlib)

> 极其简单的网络框架,封装简单,调用简单,有一定的可扩展性
> rxjava方式采用灵活的回调使用方式
> 协程方式使用最新的写成调用方式(推荐)



### 使用方式

#### (推荐)retrofit+flow+coroutines

引用
```
    implementation 'com.github.yezizaiqiutian:netlib:0.0.01'

```

使用方法
```
    launchWithLoadingAndCollect({
            BaseRepository().executeHttp {
                RetrofitClient.service.login("FastJetpack", "FastJetpack")
            }
        }) {
            onSuccess = {
                Log.d("ggggg", "请求成功")
            }
            onDataEmpty = {
                Log.d("ggggg", "数据为空")
            }
            onComplete = {
                Log.d("ggggg", "完成")
            }
            onFailed = { errorCode, errorMsg ->
                Log.d("ggggg", "请求失败,服务器返回错误")
            }
            onError = { e ->
                Log.d("ggggg", "请求失败,出现异常")
            }
        }
```

#### retrofit+rxjava

引用
```
    implementation 'com.github.yezizaiqiutian:netlib:0.0.3'
```

方法调用
```
    btn_getnet_2.setOnClickListener {
        NetUtils.getNetProgress(this@MainActivity, httpListener)
    }
```

回调方法
```
    private val httpListener = object : HttpOnNextListener<List<ItemBean?>?>(){
    
        override fun onConnect(service: HttpService): Flowable<*>? {
            tv_text.text = "正在加载数据..."
            return service.getAllVedio(
                true
            )
        }

        override fun onNext(list: List<ItemBean?>?) {
            tv_text.text = list.toString()
        }

        override fun onError(e: Throwable, list: List<ItemBean?>?) {
            tv_text.text = e.toString()
        }

        override fun onCancel() {
            tv_text.text = "请求已取消"
        }
    }
```
