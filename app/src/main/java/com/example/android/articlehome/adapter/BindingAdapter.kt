package com.example.android.articlehome.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setDate")
fun setDate(textView: TextView, date: Date) {
    val outputFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
    val formattedDate: String = outputFormat.format(date)
     textView.text=formattedDate
}