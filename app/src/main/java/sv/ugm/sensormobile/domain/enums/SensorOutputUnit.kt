package sv.ugm.sensormobile.domain.enums

enum class SensorOutputUnit(val unit: String) {
    AIR_QUALITY("ppm"),
    APPROX_ALTITUDE("m"),
    HUMIDITY("%"),
    LIGHT("lux"),
    PRESSURE("Pa"),
    RAINDROP(""),
    SOIL_MOISTURE("%"),
    TEMPERATURE_1("°C"),
    TEMPERATURE_2("°C"),
}