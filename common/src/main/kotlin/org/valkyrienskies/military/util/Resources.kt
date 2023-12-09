package org.valkyrienskies.military.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

fun getRes(clazz: Class<*>, path: String): Reader? {
    return BufferedReader(
        InputStreamReader(
            clazz.classLoader
                .getResourceAsStream(path) ?: return null, "UTF-8"
        )
    )
}