package me.ahdg6.typewriter.mythicmobs.entries.action

import com.github.shynixn.mccoroutine.bukkit.launch
import io.lumine.mythic.bukkit.MythicBukkit
import me.gabber235.typewriter.adapters.Colors
import me.gabber235.typewriter.adapters.Entry
import me.gabber235.typewriter.adapters.modifiers.Help
import me.gabber235.typewriter.entry.Criteria
import me.gabber235.typewriter.entry.Modifier
import me.gabber235.typewriter.entry.entries.ActionEntry
import me.gabber235.typewriter.plugin
import me.gabber235.typewriter.utils.Icons
import org.bukkit.entity.Player


@Entry("despawn_mythicmobs_mob", "Despawn a mob from MythicMobs", Colors.ORANGE, Icons.TRASH)
/**
 * The `Despawn Mob Action` action removes MythicMobs mobs from the world.
 *
 * ## How could this be used?
 *
 * This action could be used in stories or quests in various ways. For instance, if a player fails a quest to kill 10 zombies, then the zombies could be despawned to signal that the quest is no longer active. One could even use this action for a quest to kill a certain amount of mobs within a time limit!
 */
class DespawnMobActionEntry(
    override val id: String = "",
    override val name: String = "",
    override val criteria: List<Criteria> = emptyList(),
    override val modifiers: List<Modifier> = emptyList(),
    override val triggers: List<String> = emptyList(),
    @Help("The mob's name")
    private val mobName: String = "",
) : ActionEntry {
    override fun execute(player: Player) {
        super.execute(player)
        val displayName : String = mobName;

        val mob = MythicBukkit.inst().mobManager.getMythicMob(displayName)
        if (!mob.isPresent) return

        plugin.launch {
            MythicBukkit.inst().mobManager.activeMobs.removeIf { it.type.equals(mob.get()) }
        }
    }
}