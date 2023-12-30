package me.ahdg6.typewriter.mythicmobs.entries.event

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import me.gabber235.typewriter.adapters.Colors
import me.gabber235.typewriter.adapters.Entry
import me.gabber235.typewriter.adapters.modifiers.Help
import me.gabber235.typewriter.entry.EntryListener
import me.gabber235.typewriter.entry.Query
import me.gabber235.typewriter.entry.entries.EventEntry
import me.gabber235.typewriter.entry.triggerAllFor
import me.gabber235.typewriter.utils.Icons
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.entity.Player


@Entry("on_mythic_mob_die", "When a player kill a MythicMobs mob.", Colors.YELLOW, Icons.SKULL)
/**
 * The `Mob Death Event` event is triggered when a player kill a mob.
 *
 * ## How could this be used?
 *
 * After killing a final boss, a dialogue or cinematic section can start. The player could also get a special reward the first time they kill a specific mob.
 */
class MythicMobDeathEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<String> = emptyList(),
    @Help("Only trigger when a specific mob dies.")
    val mobName: String = "",
) : EventEntry

@EntryListener(MythicMobDeathEventEntry::class)
fun onMobDeath(event: MythicMobDeathEvent, query: Query<MythicMobDeathEventEntry>) {
    val player = event.killer as? Player ?: return
    var displayName : String = event.mob.name.lowercase()
    //why is chatcolor deprecated in KOTLIN!?!?!?!
    displayName = ChatColor.stripColor(displayName).toString()
    displayName.replace(" ", "")
    Bukkit.getLogger().info("[MMA] A mythic mob has died! Name: $displayName")
    //query findWhere { MythicBukkit.inst().mobManager.getMythicMob(it.mobName) == event.mob } triggerAllFor player
    query findWhere { it.mobName.lowercase() == displayName } triggerAllFor player
}
