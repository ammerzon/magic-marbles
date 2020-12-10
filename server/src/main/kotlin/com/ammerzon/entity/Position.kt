package com.ammerzon.entity

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

/**
 * A position with a x and y coordinate.
 */
data class Position(@get:NotEmpty @Min(0) val x: Int, @get:NotEmpty @Min(0) val y: Int)
