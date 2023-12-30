package me.ahdg6.typewriter.mythicmobs.entries.event

import io.lumine.mythic.bukkit.events.MythicMobLootDropEvent
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
import org.bukkit.entity.Player


@Entry("on_mythic_mob_loot_drop", "When a player kills a mob and drops loot.", Colors.YELLOW, Icons.BITCOIN)
/**
 * The `Mob Death Event` event is triggered when a player kill a mob.
 *
 * ## How could this be used?
 *
 * After killing a final boss, a dialogue or cinematic section can start. The player could also get a special reward the first time they kill a specific mob.
 */
class MythicMobLootDropEventEntry (
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<String> = emptyList(),
    @Help("Only trigger when a specific mob drops an item after being killed by a player.")
    val mobName: String = "",
) : EventEntry

@EntryListener(MythicMobLootDropEventEntry::class)
fun onMobLootDrop(event: MythicMobLootDropEvent, query: Query<MythicMobLootDropEventEntry>) {
    val player = event.killer as? Player ?: return
    var displayName : String = event.mob.name.lowercase()
    //why is chatcolor deprecated in KOTLIN!?!?!?!
    displayName = ChatColor.stripColor(displayName).toString()
    displayName.replace(" ", "")
    Bukkit.getLogger().info("[MMA] A mythic mob has droped loot! Name: " + event.mob.name.lowercase());
    query findWhere { it.mobName.lowercase() == displayName } triggerAllFor player
}
