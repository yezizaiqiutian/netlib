# netlib

[![](https://jitpack.io/v/yezizaiqiutian/netlib.svg)](https://jitpack.io/#yezizaiqiutian/netlib)

> 极其简单的网络框架,封装简单,调用简单,有一定的可扩展性

### 使用方式

引用
```
    implementation 'com.github.yezizaiqiutian:netlib:0.0.1'
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