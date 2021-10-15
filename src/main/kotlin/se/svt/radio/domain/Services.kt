package se.svt.radio.domain

import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withTimeout

class BroadcastService(private val broadcastRepository: BroadcastRepository) {
    // TODO Make limit and timeout configurable
    private val semaphore: Semaphore = Semaphore(3)

    suspend fun get(programName: ProgramName): List<Broadcast> {
        return withTimeout(5000) {
            semaphore.withPermit {
                broadcastRepository.getRecent(programName)
            }
        }
    }
}