package com.example.bliapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long): String {
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
    return formatter.format(date)
}