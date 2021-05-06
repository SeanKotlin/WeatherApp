package com.sklinn.weathermap.Remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.sklinn.weathermap.Model.Icon
import java.lang.reflect.Type

class IconDeserializer:JsonDeserializer<Icon> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Icon? {
        return json?.asString?.let { iconCode->
            Icon(
                code = iconCode,
                url = "http://openweathermap.org/img/wn/${iconCode}@4x.png"
            )
        }
    }
}