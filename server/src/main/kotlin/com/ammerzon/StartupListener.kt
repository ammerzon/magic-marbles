package com.ammerzon

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class StartupListener : ApplicationEventListener<ServerStartupEvent> {
    private val logger = LoggerFactory.getLogger(StartupListener::class.java)

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        logger.info("""
                ----------------------------------------------------------
                "Application magicMarble is running!
                "Access under: ${event?.source?.url}
                ----------------------------------------------------------
                """)
    }
}