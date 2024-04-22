package com.tinhtx.customapplication.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Utils {

    companion object {
        const val DATE_DEFAULT_FORMAT = "dd/MM/yyyy HH:mm"
        const val DECIMAL_FORMAT = "#,##0.00"
    }
}

fun hideSoftKeyboard(context: Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
}

fun String.convertStringToDate(format: String = Utils.DATE_DEFAULT_FORMAT): Date? {
    if (this.isEmpty())
        return null
    return try {
        SimpleDateFormat(format, Locale.JAPAN).parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.convertDateToString(format: String = Utils.DATE_DEFAULT_FORMAT): String? {
    return try {
        SimpleDateFormat(format, Locale.JAPAN).format(this)
    } catch (e: ParseException) {
        null
    }
}

fun String.convertToMonthFormat(format: String = Utils.DATE_DEFAULT_FORMAT): String? {
    return try {
        this.convertStringToDate()?.convertDateToString()
    } catch (e: ParseException) {
        null
    }
}

fun String.formatValue(): String {
    val formatter = DecimalFormat(Utils.DECIMAL_FORMAT)
    formatter.decimalFormatSymbols = DecimalFormatSymbols(Locale.JAPAN)
    return formatter.format(this.toDouble())
}