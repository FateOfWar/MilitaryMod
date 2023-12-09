package org.valkyrienskies.military

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema

object MilConfig {
    @JvmField
    val CLIENT = Client()

    @JvmField
    val SERVER = Server()

    class Client

    class Server
}
