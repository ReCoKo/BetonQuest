package org.betonquest.betonquest.quest.event.command;

import org.betonquest.betonquest.Instruction;
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory;
import org.betonquest.betonquest.api.quest.event.Event;
import org.betonquest.betonquest.exceptions.InstructionParseException;
import org.betonquest.betonquest.quest.event.OnlineProfileRequiredEvent;
import org.betonquest.betonquest.quest.event.OpPlayerEventAdapter;
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Creates a new OpSudoEvent from an {@link Instruction}.
 */
public class OpSudoEventFactory extends BaseCommandEventFactory {

    /**
     * Create the OpSudoEvent factory.
     *
     * @param loggerFactory logger factory to use
     * @param server        server to use
     * @param scheduler     scheduler scheduler to use
     * @param plugin        plugin to use
     */
    public OpSudoEventFactory(final BetonQuestLoggerFactory loggerFactory, final Server server,
                              final BukkitScheduler scheduler, final Plugin plugin) {
        super(loggerFactory, server, scheduler, plugin);
    }

    @Override
    public Event parseEvent(final Instruction instruction) throws InstructionParseException {
        return new PrimaryServerThreadEvent(
                new OnlineProfileRequiredEvent(
                        loggerFactory.create(SudoEvent.class, "opsudo"),
                        new OpPlayerEventAdapter(new SudoEvent(parseCommands(instruction))),
                        instruction.getPackage()),
                server, scheduler, plugin);
    }
}
