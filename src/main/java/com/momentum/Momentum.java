package com.momentum;

import com.momentum.api.command.CommandDispatcher;
import com.momentum.api.event.handler.EventHandler;
import com.momentum.init.Bootstrap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Client main
 *
 * @author linus
 * @since 01/09/2023
 */
public class Momentum
{
    // client logger
    public static Logger LOGGER;

    // event bus
    public static EventHandler EVENT_HANDLER;

    // command dispatcher
    public static CommandDispatcher CMD_DISPATCHER;

    /**
     * Runs before the {@link MomentumMod#onInitialize()}
     */
    public static void preInit()
    {
        LOGGER = LogManager.getLogger("Momentum");
        LOGGER.info("Starting preInit ...");
        // init event bus
        EVENT_HANDLER = new EventHandler();
    }

    /**
     * Runs during the {@link MomentumMod#onInitialize()}
     */
    public static void init()
    {
        LOGGER.info("Starting init ...");
        Bootstrap.init();
        CMD_DISPATCHER = new CommandDispatcher();
    }

    /**
     * Runs after the {@link MomentumMod#onInitialize()}
     */
    public static void postInit()
    {
        LOGGER.info("Starting postInit ...");
    }
}
