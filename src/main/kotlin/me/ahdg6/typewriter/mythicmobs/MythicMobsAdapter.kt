package me.ahdg6.typewriter.mythicmobs

import lirand.api.extensions.server.server
import me.gabber235.typewriter.adapters.Adapter
import me.gabber235.typewriter.adapters.TypewriteAdapter
import me.gabber235.typewriter.logger

@Adapter("MythicMobs", "For Using MythicMobs", "0.3.2")
/**
 * The MythicMobs Adapter is an adapter for the MythicMobs plugin. It allows you handle mob-related things in TypeWriter.
 */
object MythicMobsAdapter : TypewriteAdapter() {

    override fun initialize() {
        if (!server.pluginManager.isPluginEnabled("MythicMobs")) {
            logger.warning("MythicMobs plugin not found, try installing it or disabling the MythicMobs adapter")
            return
        }
    }

}