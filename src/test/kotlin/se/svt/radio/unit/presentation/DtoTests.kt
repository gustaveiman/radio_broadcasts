package se.svt.radio.unit.presentation

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import se.svt.radio.domain.Broadcast
import se.svt.radio.presentation.BroadcastDto
import java.time.LocalDateTime

@SpringBootTest
class BroadcastDtoTests {
    @Test
    fun fromModel() {
        val model = Broadcast("title", "description", LocalDateTime.MIN)
        val dto = BroadcastDto.fromModel(model)
        assert(dto.title == model.title)
        assert(dto.description == model.description)
        assert(dto.broadcastAt == model.broadcastDateTime)
    }
}