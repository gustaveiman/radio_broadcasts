package se.svt.radio.unit.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import se.svt.radio.domain.ProgramName

@SpringBootTest
class ModelTests {
    @Test
    fun canNeverBeEmpty() {
        assertThrows<IllegalArgumentException> {
            ProgramName("")
        }
    }
}