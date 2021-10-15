package se.svt.radio.presentation

import se.svt.radio.domain.Broadcast
import java.time.LocalDateTime


data class BroadcastDto( // TODO find a better name to ensure there is no conflict with adapters?
    val title: String,
    val description: String,
    val broadcastAt: LocalDateTime
) {
    companion object Factory {
        fun fromModel(model: Broadcast): BroadcastDto = BroadcastDto(model.title, model.description, model.broadcastDateTime)
    }
}