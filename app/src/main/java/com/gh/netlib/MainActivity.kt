package com.gh.netlib

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gh.netlib.entity.ItemBean
import com.gh.netlib.net.HttpOnNextListener
import com.gh.netlib.net.HttpService
import com.gh.netlib.net.NetUtils
import io.reactivex.Flowable

class MainActivity : AppCompatActivity() {

    private lateinit var tv_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_getnet_1 = findViewById<View>(R.id.btn_getnet_1)
        val btn_getnet_2 = findViewById<View>(R.id.btn_getnet_2)
        tv_text = findViewById(R.id.tv_text)

        btn_getnet_1.setOnClickListener {
            NetUtils.getNet(this@MainActivity, httpListener)
        }

        btn_getnet_2.setOnClickListener {
            NetUtils.getNetProgress(this@MainActivity, httpListener)
        }

    }

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


}