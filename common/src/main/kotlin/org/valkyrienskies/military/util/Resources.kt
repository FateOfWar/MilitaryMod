package org.valkyrienskies.military.util

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

fun getRes(clazz: Class<*>, path: String): Reader? =
    getResStream(clazz, path)?.let { BufferedReader(InputStreamReader(it)) }

fun getResStream(clazz: Class<*>, path: String): InputStream? =
    clazz.classLoader
        .getResourceAsStream(path) ?: null