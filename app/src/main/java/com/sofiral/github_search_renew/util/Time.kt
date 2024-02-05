package com.sofiral.github_search_renew.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TimeUtil @Inject constructor() {
    fun getDateFromFormat(
        time: String,
        inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
        outputFormatter: String = "dd-MM-yyyy"
    ): String {
        val inputFormatter = DateTimeFormatter.ofPattern(inputFormat)
        val outputFormatter = DateTimeFormatter.ofPattern(outputFormatter)
        val date = LocalDate.parse(time, inputFormatter)
        return outputFormatter.format(date)
    }
}