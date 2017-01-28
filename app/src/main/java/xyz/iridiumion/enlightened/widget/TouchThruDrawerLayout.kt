package xyz.iridiumion.enlightened.widget

//Code from ShaderEditor
/**
 * Author: markusfisch
 * Author: 0xFireball
 */

import android.content.Context
import android.support.v4.widget.DrawerLayout
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Required to get touches through DrawerLayout
 */
class TouchThruDrawerLayout : DrawerLayout {
    private var touchThru = false

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {}

    fun setTouchThru(touchThru: Boolean) {
        this.touchThru = touchThru
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return !touchThru && super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return !touchThru && super.onTouchEvent(event)
    }
}
