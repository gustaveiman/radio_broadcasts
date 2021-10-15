# SR broadcasts
This is an API to fetch recent broadcasts of different programs from Sveriges Radio.

## Project structure
The project is split into three layers. The purpose of the split is to ensure that each layer can be changed and tested more independently.

`domain` - Contain all our business logic (BroadcastService), domain entities (Broadcast and ProgramName) and abstractions for fetching those entities (BroadcastRepository). Most importantly, it has no dependencies on any other layer to keep it flexible in the face of new requirements.

`data` - Contains everything we need to fetch broadcasts and provide them to our domain layer. Implements the repository abstraction from our domain layer. Depends on the domain layer.

`presentation` - Contains everything related to presenting the data to the user, in this case our API endpoints and possible responses. Also depends on the domain layer.

An "application layer" was also considered, but I could not justify its existence and therefore found it to be a distraction for future readers.

## Running

To run the application, execute `mvn spring-boot:run`, and in another shell try `curl "localhost:8080/broadcasts?programName=Tankesmedjan"`.

## Tests

To run the tests, execute `mvn test`.


## TODO
- [] Setup springfox
- [] Make concurrency limit and timeout configurable
- [] Decouple rate limiting from the `BroadcastService`
  - [] Test rate limiting (limit and timeouts)
- [] Improve error messages in the API (e.g. `curl "localhost:8080/broadcasts` lacks any mention that `programName` is a required query param).
- [] Write integration tests
- [] Test all known responses from `/broadcasts` including errors
- [] Caching `programId` in the data layer
