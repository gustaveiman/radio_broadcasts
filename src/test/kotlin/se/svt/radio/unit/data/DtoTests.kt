package se.svt.radio.unit.data

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import se.svt.radio.data.BroadcastDto
import se.svt.radio.data.SRDateConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

const val ARBITRARY_EPOCH_MILLI = 1633554420000

@SpringBootTest
class BroadcastDtoTests {
    @Test
    fun toModel() {
        val dto = BroadcastDto("title", "description", Instant.ofEpochMilli(ARBITRARY_EPOCH_MILLI))
        val model = dto.toModel()
        assert(model.title == "title")
        assert(model.description == "description")
        assert(model.broadcastDateTime == LocalDateTime.ofInstant(
            Instant.ofEpochMilli(ARBITRARY_EPOCH_MILLI),
            ZoneId.of("Europe/Stockholm"))
        )
    }
}

@SpringBootTest
class SRDateConverterTests {
    @Test
    fun convert() {
        val epoch = ARBITRARY_EPOCH_MILLI
        val date = "/Date($epoch)/"
        assert(Instant.ofEpochMilli(ARBITRARY_EPOCH_MILLI) == SRDateConverter().convert(date))
    }
}