package se.svt.radio.data

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObject
import org.springframework.stereotype.Repository
import se.svt.radio.domain.*


@Repository
class SRBroadcasts : BroadcastRepository {
    override suspend fun getRecent(programName: ProgramName): List<Broadcast> {
        val programId = getProgramId(programName)
        val broadcastsUrl = "https://api.sr.se/api/v2/broadcasts?programid=$programId&sort=-broadcastdateutc&format=json&size=5"
        val response = Fuel.get(broadcastsUrl).awaitObject(BroadcastResponseDeserializer)
        return response.broadcasts.map { it.toModel() }
    }

    private suspend fun getProgramId(programName: ProgramName): Int {
        val programsUrl = "https://api.sr.se/api/v2/programs?format=json&pagination=false"
        val response = Fuel.get(programsUrl).awaitObject(ProgramResponseDeserializer)
        return response.programs.singleOrNull{it.name.lowercase() == programName.toString().lowercase()}?.id
            ?: throw ProgramNotFound()
    }
}