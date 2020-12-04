package com.ammerzon

import io.micronaut.runtime.Micronaut.build
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
        info = Info(
                title = "Magic Marbles",
                version = "0.1",
                description = "The Magic Marble API",
        )
)
object Api

fun main(args: Array<String>) {
    build()
            .args(*args)
            .packages("com.ammerzon")
            .start()
}

