package id.siandalan.app.common.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.Locale

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.capitalizeFirstLetter(): String {
    if (isNotEmpty()) {
        return substring(0, 1).uppercase(Locale.ROOT) + substring(1)
    }
    return this
}

fun String.dateFormatComplete(
    sourcePattern: String = "yyyy-MM-dd HH:mm:ss",
    targetPattern: String = "dd MMM yyyy HH:mm",
): String {
    val locale = Locale("in", "ID")
    val date = SimpleDateFormat(sourcePattern, locale).parse(this)!!
    return SimpleDateFormat(targetPattern, Locale.getDefault()).format(date)
}

fun String.dateFormat(
    sourcePattern: String = "yyyy-MM-dd",
    targetPattern: String = "dd MMM yyyy",
): String {
    val locale = Locale("in", "ID")
    val date = SimpleDateFormat(sourcePattern, locale).parse(this)!!
    return SimpleDateFormat(targetPattern, Locale.getDefault()).format(date)
}

fun String.convertToCamelCase(): String {
    val words = this.split("-")
    val camelCaseWords = mutableListOf<String>()

    for (word in words) {
        camelCaseWords.add(word.lowercase(Locale.ROOT)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
    }

    return camelCaseWords.joinToString(" ")
}