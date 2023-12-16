package com.example.note_kotlin.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.StyleRes
import com.example.note_kotlin.R

class ChooseDialog(
    context: Context,
    @StyleRes themeResId: Int,
    private val listener: IChooseListener?
) : Dialog(context, themeResId) {
    private var tv_cancel: TextView? = null
    private var tv_ok: TextView? = null
    private var tv_txt: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND) //添加此行代码保持在弹出dialog时不改变状态栏字体背景色
        window!!.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )
        window!!.setType(WindowManager.LayoutParams.FIRST_SUB_WINDOW) //面板窗口，显示于宿主窗口上层。

        //dialog 沉浸式全屏，
        window!!.setDimAmount(0f) //设置dialog框透明度
        window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window!!.setFlags(
            WindowManager.LayoutParams.FLAG_DIM_BEHIND,
            WindowManager.LayoutParams.FLAG_DIM_BEHIND
        )
        //内容扩展到导航栏
        window!!.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window!!.statusBarColor = Color.TRANSPARENT
            //设置导航栏颜
            window!!.navigationBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.dialog_camera_file)
        tv_cancel = findViewById(R.id.tv_cancel)
        tv_ok = findViewById(R.id.tv_ok)
        tv_txt = findViewById(R.id.tv_txt)
        setCanceledOnTouchOutside(false) // 设置点击对话框外部区域不消失
        setCancelable(false) // 设置对话框不可取消
        adjustHeight()
        tv_txt!!.setOnClickListener(View.OnClickListener { listener?.onCamera() })
        tv_cancel!!.setOnClickListener(View.OnClickListener {
            listener?.onFile()
            dismiss()
        })
        tv_ok!!.setOnClickListener(View.OnClickListener {
            listener?.onFile()
            dismiss()
        })
    }

    // 重写onKeyUp方法
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {  // 判断按键是否为返回键
            isCosumenBackKey // 调用isCosumenBackKey方法处理返回键逻辑
        } else false
        // 返回false，代表继续向下传递back事件，由系统去控制
    }

    private val isCosumenBackKey: Boolean
        // 定义私有方法isCosumenBackKey
        private get() =// 这儿做返回键的控制，如果自己处理返回键逻辑就返回true，如果返回false,代表继续向下传递back事件，由系统去控制
            true // 返回true，表示自己处理返回键逻辑

    private fun adjustHeight() {
        this.window!!.decorView.setPadding(0, 0, 0, 0)
        val layoutParams = this.window!!.attributes
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        window!!.attributes = layoutParams
        this.window!!.setGravity(Gravity.BOTTOM)
    }
}