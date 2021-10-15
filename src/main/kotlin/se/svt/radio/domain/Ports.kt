package se.svt.radio.domain

interface BroadcastRepository {
    /**
     * Fetches the most recent broadcasts for a radio program.
     *
     * @param programName name of the program
     * @return a list of the most recent broadcasts
     * @throws ProgramNotFound
     */
    suspend fun getRecent(programName: ProgramName): List<Broadcast>
}