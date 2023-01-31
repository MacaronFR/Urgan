package fr.imacaron.groupe19.urgan.error

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.Exception

class NetworkException(override val message: String): Exception(message)

val h = CoroutineExceptionHandler{ _, t ->
    t.printStackTrace()
}