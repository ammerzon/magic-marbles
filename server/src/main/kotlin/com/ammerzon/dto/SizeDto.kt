package com.ammerzon.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

/**
 * Immutable class for describing width and height dimensions in pixels.
 */
@Introspected
data class SizeDto(@get:NotEmpty @Min(3) @Max(14) val width: Int, @get:NotEmpty @Min(3) @Max(14) val height: Int)