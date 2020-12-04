package com.ammerzon.model

import io.micronaut.core.annotation.Introspected

@Introspected
data class UserAction(val action: ActionType, val payload: Any)
