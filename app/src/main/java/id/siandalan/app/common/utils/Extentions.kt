package id.siandalan.app.common.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import java.io.File
import java.text.NumberFormat
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

fun String.dateFormatCompleteWithSecond(
    sourcePattern: String = "yyyy-MM-dd HH:mm:ss",
    targetPattern: String = "dd MMM yyyy HH:mm:ss",
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

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelableArrayList(key: String): ArrayList<T>? {
    return when {
        Build.VERSION.SDK_INT >= 33 -> getParcelableArrayList(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableArrayList(key)
    }
}

fun Context.openDownloadedPDF(fileName: String) {
    val file =
        File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
    val path: Uri =
        FileProvider.getUriForFile(
            this.applicationContext,
            this.applicationContext.packageName + ".provider",
            file
        )
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(path, "application/pdf")
    intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_GRANT_READ_URI_PERMISSION
    val chooserIntent = Intent.createChooser(intent, "Open with")
    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        this.startActivity(chooserIntent)
    } catch (e: ActivityNotFoundException) {
        Log.e("TAG", "Failed to open PDF  ${e.localizedMessage}")
    }
}

fun String?.checkEmpty(): String {
    return if (this.isNullOrEmpty()) "-" else this
}

fun Double.formatCurrency(): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    return currencyFormat.format(this)
}