package ua.com.eco_lab.recycling.view

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ua.com.eco_lab.recycling.helper.DateHelper
import java.util.*


@BindingAdapter("dateMonthYear")
fun TextView.dateMonthYear(date: Date?) {
    text = DateHelper.getMonthYearDisplayDate(date)
}

@BindingAdapter("dateDayMonthYear")
fun TextView.dateDayMonthYear(date: Date?) {
    if (date == null) return
    text = DateHelper.getDisplayDateShort(date)
}

