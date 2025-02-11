package uz.mobilesoft.cleanarchitecture.presentation.customviews

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener

class PhoneNumberEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private val prefix = "+998 "
    private val maxLength = prefix.length + 12
    private var isEditing = false

    init {
        // setText(prefix)
        // setSelection(text?.length ?: 0)
        filters = arrayOf(InputFilter.LengthFilter(maxLength))

        addTextChangedListener(object : PhoneNumberTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (isEditing || s == null) return

                isEditing = true

                val rawDigits = s.toString().replace("\\D".toRegex(), "").removePrefix("998")

                val formatted = StringBuilder(prefix)

                for (i in rawDigits.indices) {
                    if (i == 2 || i == 5 || i == 7) formatted.append(" ")

                    formatted.append(rawDigits[i])
                }

                setText(formatted)
                setSelection(formatted.length)

                isEditing = false
            }
        })
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        if (text == null || text?.length == 0) return super.onSelectionChanged(selStart, selEnd)

        if (selStart < prefix.length || selEnd < prefix.length) {
            setSelection(prefix.length)
        } else {
            super.onSelectionChanged(selStart, selEnd)
        }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: android.graphics.Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused && text.isNullOrEmpty()) {
            setText(prefix)
            setSelection(prefix.length)
        }
    }
}

abstract class PhoneNumberTextWatcher :TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}