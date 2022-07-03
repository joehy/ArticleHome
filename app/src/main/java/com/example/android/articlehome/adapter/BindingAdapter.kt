package com.example.android.articlehome.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setDate")
fun setDate(textView: TextView, date: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SS'Z'")
    val parsedDate: Date = inputFormat.parse(date)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
    val formattedDate: String = outputFormat.format(parsedDate)
     textView.text=formattedDate
}