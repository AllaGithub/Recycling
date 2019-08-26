package ua.com.eco_lab.recycling.helper

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateHelper {

    const val DAYS_TO_BOOK_CALL = 28

    const val DATE_TIME_UTC_ALT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val DATE_TIME_UTC = "yyyy-MM-dd'T'HH:mm:ssZ"
    const val DATE_UTC = "yyyy-MM-dd"
    const val DATE_NON_UTC = "dd/MM/yyyy"
    const val MONTH_YEAR_DISPLAY = "MMM yyyy"
    const val DISPLAY_DATE_SHORT = "dd/MM/yyyy"
    const val DISPLAY_DATE_TIME = "EE MMM d, h:mm a"
    const val DISPLAY_DATE = "EE MMM d"
    const val DISPLAY_TIME = "h:mm a"
    const val DISPLAY_TIME_24_HOURS = "HH:mm"
    const val PATTERN_MONTH = "MM"
    const val PATTERN_YEAR = "yyyy"
    const val PATTERN_DAY_OF_THE_WEEK = "EEEE"
    const val DAY_DATE_MONTH_DISPLAY = "EEEE dd MMMM"
    const val DATE_MONTH_YEAR_DISPLAY = "dd MMMM yyyy"

    @JvmStatic
    fun getDisplayDateTime(date: Date?): String {
        if (date == null) return ""
        val displayFormat = SimpleDateFormat(DISPLAY_DATE_TIME, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun getDisplayDateShort(date: Date?): String {
        if (date == null) return ""
        val displayFormat = SimpleDateFormat(DISPLAY_DATE_SHORT, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun getDisplayDate(date: Date?): String {
        if (date == null) return ""
        val displayFormat = SimpleDateFormat(DISPLAY_DATE, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun getMonthYearDisplayDate(date: Date?): String {
        if (date == null) return ""
        val displayFormat = SimpleDateFormat(MONTH_YEAR_DISPLAY, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun getDisplayTime(date: Date): String {
        val displayFormat = SimpleDateFormat(DISPLAY_TIME, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun getDisplayTime24Hours(date: Date): String {
        val displayFormat = SimpleDateFormat(DISPLAY_TIME_24_HOURS, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun getDisplayDateUTC(date: Date): String {
        val displayFormat = SimpleDateFormat(DATE_UTC, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun getDisplayDateSlotUTC(date: Date): String {
        val displayFormat = SimpleDateFormat(DATE_TIME_UTC, Locale.getDefault())
        return displayFormat.format(date)
    }

    @JvmStatic
    fun isCardExpDateValid(date: Date): Boolean {
        val todayDate = Date()
        return if (DateFormat.format(PATTERN_YEAR, date).toString().toInt()
                > DateFormat.format(PATTERN_YEAR, todayDate).toString().toInt()) {
            true
        } else if (DateFormat.format(PATTERN_YEAR, date).toString().toInt()
                == DateFormat.format(PATTERN_YEAR, todayDate).toString().toInt()) {
            (DateFormat.format(PATTERN_MONTH, date).toString().toInt()
                    >= DateFormat.format(PATTERN_MONTH, todayDate).toString().toInt())
        } else {
            false
        }
    }

    @JvmStatic
    fun getCardExpDate(date: Date): String {
        val simpleDateFormat = SimpleDateFormat("MMyyyy")
        val cal = Calendar.getInstance()
        cal.time = date
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH))
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR))
        return simpleDateFormat.format(cal.time)
    }

    @JvmStatic
    fun isMonthYearInPastOrCurrent(month: Int, year: Int): Boolean { // compares 0 based month index
        val todayDate = Date()
        return if (year < DateFormat.format(PATTERN_YEAR, todayDate).toString().toInt()) {
            true
        } else if (year == DateFormat.format(PATTERN_YEAR, todayDate).toString().toInt()) {
            (month <= DateFormat.format(PATTERN_MONTH, todayDate).toString().toInt() - 1)
        } else {
            false
        }
    }

    // policyStartDate of "1800-01-01" from Api should be treated as NULL
    @JvmStatic
    fun isDateApiDefinedNull(date: Date?): Boolean {
        // policyStartDate of "1800-01-01" from Api should be treated as NULL
        val format = SimpleDateFormat(("yyyy-MM-dd"), Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        val nullDate: Date? = format.parse("1800-01-01")

        return date?.compareTo(nullDate) == 0
    }

    @JvmStatic
    fun getTomorrow(): Date {
        // a date before tomorrow should be set to tomorrow
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, 24)
        return calendar.time
    }

    @JvmStatic
    fun getDayOfTheWeek(date: Date): String {
        val simpleDateformat = SimpleDateFormat(PATTERN_DAY_OF_THE_WEEK, Locale.getDefault())
        return simpleDateformat.format(date)
    }

    @JvmStatic
    fun getDateMonthYear(date: Date): String {
        val simpleDateformat = SimpleDateFormat(DATE_MONTH_YEAR_DISPLAY, Locale.getDefault())
        return simpleDateformat.format(date)
    }

    @JvmStatic
    fun getNumberOfDaysToBookCall(policyStartDate: Date): Int {

        val millisecInDay = 1000 * 60 * 60 * 24

        val daysBetweenDates: Int
        // if policy start date is earlier than today
        return if (Date().time > policyStartDate.time) {
            daysBetweenDates = ((Date().time - policyStartDate.time) / (millisecInDay)).toInt()
            DAYS_TO_BOOK_CALL - daysBetweenDates
            // if policy start date is later than today or equals
        } else {
            daysBetweenDates = ((policyStartDate.time - Date().time) / (millisecInDay)).toInt()
            // +1 to include the last day
            DAYS_TO_BOOK_CALL + daysBetweenDates + 1
        }
    }

    @JvmStatic
    fun getBookCallDueDate(policyStartDate: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = policyStartDate
        calendar.add(Calendar.DATE, DAYS_TO_BOOK_CALL)
        val dateDue = calendar.time
        return getDayDateMonth(dateDue)
    }

    fun getDayDateMonth(date: Date): String {
        val simpleDateFormat = SimpleDateFormat(DAY_DATE_MONTH_DISPLAY, Locale.getDefault())
        return simpleDateFormat.format(date)
    }
}