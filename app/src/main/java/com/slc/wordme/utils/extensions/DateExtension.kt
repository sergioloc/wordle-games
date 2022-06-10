package com.slc.wordme.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.simpleFormat(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}