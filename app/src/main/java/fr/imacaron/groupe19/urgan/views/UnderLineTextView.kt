package fr.imacaron.groupe19.urgan.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class UnderLineTextView(context: Context, attrs: AttributeSet? = null): AppCompatTextView(context, attrs) {
    init {
        this.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
}
