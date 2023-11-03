package sv.ugm.sensormobile.domain.util

fun Float?.orZero(): Float {
    return this ?: 0f
}