package me.ahdg6.typewriter.mythicmobs.entries.event

import io.lumine.mythic.bukkit.events.MythicMobInteractEvent
import me.gabber235.typewriter.adapters.Colors
import me.gabber235.typewriter.adapters.Entry
import me.gabber235.typewriter.adapters.modifiers.Help
import me.gabber235.typewriter.entry.EntryListener
import me.gabber235.typewriter.entry.Query
import me.gabber235.typewriter.entry.entries.EventEntry
import me.gabber235.typewriter.entry.triggerAllFor
import me.gabber235.typewriter.utils.Icons
import org.bukkit.ChatColor
import org.bukkit.entity.Player


@Entry("on_mythic_mob_interact", "When a player interact a MythicMobs mob.", Colors.YELLOW, Icons.HANDSHAKE)
/**
 * The `Mob Death Event` event is triggered when a player kill a mob.
 *
 * ## How could this be used?
 *
 * After killing a final boss, a dialogue or cinematic section can start. The player could also get a special reward the first time they kill a specific mob.
 */
class MythicMobInteractEntry (
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<String> = emptyList(),
    @Help("Only trigger when a specific mob interacts.")
    val mobName: String = "",
) : EventEntry

@EntryListener(MythicMobInteractEntry::class)
fun onMobInteract(event: MythicMobInteractEvent, query: Query<MythicMobInteractEntry>) {
    val player = event.player ?: return
    var displayName : String = event.activeMob.name.lowercase()
    //why is chatcolor deprecated in KOTLIN!?!?!?!
    displayName = ChatColor.stripColor(displayName).toString()
    displayName.replace(" ", "")
    query findWhere { it.mobName.lowercase() == displayName } triggerAllFor player
}
