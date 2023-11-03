package sv.ugm.sensormobile.domain.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toMillis(inputPattern: String): Long {
    val dateTimeFormatter = buildDateTimeFormatter(inputPattern)
    val dateTime = try {
        LocalDateTime.parse(
            this,
            dateTimeFormatter,
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return 0
    }
    
    val zoneId = ZoneId.systemDefault()
    val zonedDateTime = dateTime.atZone(zoneId)
    
    return zonedDateTime.toInstant().toEpochMilli()
}

fun Long.toDateTimeString(outputPattern: String): String {
    val dateTimeFormatter = buildDateTimeFormatter(outputPattern)
    
    val zoneId = ZoneId.systemDefault()
    val zonedDateTime = Instant.ofEpochMilli(this).atZone(zoneId)
    
    return dateTimeFormatter.format(zonedDateTime)
}

private fun buildDateTimeFormatter(
    pattern: String,
    locale: Locale = Locale.ENGLISH,
): DateTimeFormatter {
    return DateTimeFormatter.ofPattern(
        pattern,
        locale,
    )
}