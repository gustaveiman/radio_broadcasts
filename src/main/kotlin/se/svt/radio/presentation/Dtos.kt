package se.svt.radio.presentation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import se.svt.radio.domain.Broadcast
import java.time.LocalDateTime

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "program not found")
class HttpProgramNotFound : RuntimeException()

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "the provided program name was invalid")
class HttpBadProgramName : RuntimeException()

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "unable to handle the request due to a temporary overload")
class HttpTemporaryOverload : RuntimeException()

data class BroadcastDto( // TODO find a better name to ensure there is no conflict with adapters?
    val title: String,
    val description: String,
    val broadcastAt: LocalDateTime
) {
    companion object Factory {
        fun fromModel(model: Broadcast): BroadcastDto = BroadcastDto(model.title, model.description, model.broadcastDateTime)
    }
}