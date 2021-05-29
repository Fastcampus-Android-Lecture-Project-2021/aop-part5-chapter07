package fastcampus.aop.part5.chapter07.extension

import android.view.View
import androidx.annotation.Px
import fastcampus.aop.part5.chapter07.extension.dip

@Px
fun View.dip(dipValue: Float) = context.dip(dipValue)

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}
