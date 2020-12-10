package com.ammerzon.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

/**
 * A position with a x and y coordinate.
 */
@Introspected
data class PositionDto(@get:NotEmpty @Min(0) val x: Int, @get:NotEmpty @Min(0) val y: Int)
