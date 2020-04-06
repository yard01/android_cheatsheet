package com.github.yard01.androidcheatsheet.ui.richtext

import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan

class RichText {
    companion object {
        fun highlightText (text: String, substring: String, color: Int): SpannableString {
            return highlightText (SpannableString(text), substring, color)
        }

        fun highlightText (spanText: SpannableString, substring: String, color: Int): SpannableString {
            if (substring.length == 0) return spanText
            var from = spanText.indexOf(substring, 0,true)
            while (from >= 0) {
                spanText.setSpan(BackgroundColorSpan(color), from, from + substring.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                from = spanText.indexOf(substring, from + substring.length, true)
            }
            return spanText
        }
    }
}