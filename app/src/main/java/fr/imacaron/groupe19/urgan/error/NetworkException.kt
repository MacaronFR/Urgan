package fr.imacaron.groupe19.urgan.error

import kotlin.Exception

class NetworkException(override val message: String): Exception(message)