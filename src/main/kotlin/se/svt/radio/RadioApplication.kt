package se.svt.radio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import se.svt.radio.domain.BroadcastRepository
import se.svt.radio.domain.BroadcastService


@SpringBootApplication
class RadioApplication {
	@Bean
	fun broadcastService(broadcastRepository: BroadcastRepository): BroadcastService {
		return BroadcastService(broadcastRepository)
	}
}

fun main(args: Array<String>) {
	runApplication<RadioApplication>(*args)
}
