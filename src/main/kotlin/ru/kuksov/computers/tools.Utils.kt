package ru.kuksov.computers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

fun <T> returnResponseEntityWithBody(body: T, status: HttpStatus): ResponseEntity<T> {
    return ResponseEntity
        .status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(body)
}

fun <T> returnEmptyResponseEntity(status: HttpStatus): ResponseEntity<T> {
    return ResponseEntity
        .status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .build()
}