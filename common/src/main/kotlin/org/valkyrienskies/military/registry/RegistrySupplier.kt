package org.valkyrienskies.military.registry

interface RegistrySupplier<T> {

    val name: String
    fun get(): T

}