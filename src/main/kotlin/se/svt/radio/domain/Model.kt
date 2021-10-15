package se.svt.radio.domain

import java.time.LocalDateTime

@JvmInline
value class ProgramName(private val name: String) {
    init {
        require(name.isNotEmpty())
    }

    override fun toString(): String {
        return name
    }
}

data class Broadcast(val title: String, val description: String, val broadcastDateTime: LocalDateTime)