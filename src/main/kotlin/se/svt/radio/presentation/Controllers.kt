package se.svt.radio.presentation

import kotlinx.coroutines.TimeoutCancellationException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import se.svt.radio.domain.ProgramName
import se.svt.radio.domain.BroadcastService

@RestController
class BroadcastResource(val broadcastService: BroadcastService) {

    @GetMapping("/broadcasts")
    suspend fun broadcasts(@RequestParam programName: String): List<BroadcastDto> {
        // Conversion needs to go here as we cannot use "value classes" as @RequestParam.
        // See: https://github.com/spring-projects/spring-framework/issues/27345
        val name = ProgramName(programName)
        return broadcastService.get(name).map { BroadcastDto.fromModel(it) }

    }
}
