package se.svt.radio.presentation

import kotlinx.coroutines.TimeoutCancellationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.handler.ResponseStatusExceptionHandler
import se.svt.radio.domain.ProgramNotFound

@ControllerAdvice
class ExceptionHandler: ResponseStatusExceptionHandler() {

    @ExceptionHandler(ProgramNotFound::class)
    fun programNotFound(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("Programmet hittades inte", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("Felaktigt programnamn", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(TimeoutCancellationException::class)
    fun timeoutCancellationException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("För många anrop just, försök igen senare", HttpStatus.TOO_MANY_REQUESTS)
    }
}