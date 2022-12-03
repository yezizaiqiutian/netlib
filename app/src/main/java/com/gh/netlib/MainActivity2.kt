package com.gh.netlib

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gh.net_lib.BaseRepository
import com.gh.netlib.net2.IUIView
import com.gh.netlib.net2.RetrofitClient
import com.gh.netlib.net2.launchWithLoadingAndCollect

class MainActivity2 : AppCompatActivity(), IUIView {

    private lateinit var tv_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_getnet_1 = findViewById<View>(R.id.btn_getnet_1)
        val btn_getnet_2 = findViewById<View>(R.id.btn_getnet_2)
        tv_text = findViewById(R.id.tv_text)

        btn_getnet_1.setOnClickListener {
            login()
        }

        btn_getnet_2.setOnClickListener {
        }

    }


    /**
     * 链式调用，返回结果的处理都在一起，viewmodel中不需要创建一个livedata对象
     * 适用于不需要监听数据变化的场景
     * 屏幕旋转，Activity销毁重建，数据会消失
     */
    private fun login() {
        launchWithLoadingAndCollect({
            BaseRepository().executeHttp {
                RetrofitClient.service.login("FastJetpack", "FastJetpack")
            }
        }) {
            onSuccess = {
                tv_text.text = it.toString()
            }
            onFailed = { errorCode, errorMsg ->
                tv_text.text = "errorCode: $errorCode   errorMsg: $errorMsg"
            }
            onError = { e ->
                tv_text.text = e.toString()
                Log.d("ggggg", e.toString())
            }
        }
    }

    override fun showLoading() {
        Toast.makeText(this, "加载中...", Toast.LENGTH_SHORT).show()
    }

    override fun dismissLoading() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show()
    }


}