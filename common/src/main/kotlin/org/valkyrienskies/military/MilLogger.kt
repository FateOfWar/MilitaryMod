package org.valkyrienskies.military

object MilLogger {
    fun exception(e: Throwable) {
        e.printStackTrace() // TODO: Replace with proper logging
    }

    fun error(message: String, vararg args: Any?) {
        println(message.format(*args)) // TODO: Replace with proper logging
    }
}