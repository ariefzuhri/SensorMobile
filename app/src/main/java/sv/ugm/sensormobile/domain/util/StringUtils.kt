package sv.ugm.sensormobile.domain.util

/**
 * Determines whether the string is a numeric value,
 * including integers, decimals, and negative numbers.
 *
 * @return `true` if the string represents a numeric value, `false` otherwise.
 */
fun String?.isNumber(): Boolean {
    return this?.matches(Regex("-?\\d+(\\.\\d+)?")) ?: false
}

/**
 * Converts the String to a Float if it contains only digits, or returns 0.0 if the String is null
 * or not a digit.
 *
 * @return The Float value of the String if it's a valid digit, or 0.0 otherwise.
 */
fun String?.toFloatOrZero(): Float {
    return this?.takeIf { it.isNumber() }?.toFloat() ?: 0f
}