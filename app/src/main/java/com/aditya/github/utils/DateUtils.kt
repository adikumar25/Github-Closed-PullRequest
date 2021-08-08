package com.aditya.github.utils

class DateUtils {
    companion object {

        @JvmStatic
        fun getFormattedDate(dateString: String): String {
            return dateString.split("T")[0]
        }
    }
}
