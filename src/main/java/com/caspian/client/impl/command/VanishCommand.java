package com.caspian.client.impl.command;

import com.caspian.client.api.command.Command;
import com.caspian.client.api.command.arg.Argument;
import com.caspian.client.api.command.arg.arguments.StringArgument;
import com.caspian.client.mixin.accessor.AccessorEntity;
import com.caspian.client.util.chat.ChatUtil;
import net.minecraft.entity.Entity;

import java.util.Arrays;

/**
 *
 *
 * @author linus
 * @since 1.0
 */
public class VanishCommand extends Command
{
    //
    Argument<String> dismountArg = new StringArgument("Dismount/Remount",
            "Desync or resync the entity", Arrays.asList("dismount", "remount"));
    //
    private Entity mount;

    /**
     *
     */
    public VanishCommand()
    {
        super("Vanish", "Desyncs the riding entity");
    }

    /**
     * Runs when the command is inputted in chat
     */
    @Override
    public void onCommandInput()
    {
        String dismount = dismountArg.parse();
        if (dismount.equalsIgnoreCase("dismount"))
        {
            if (mc.player.isRiding() && mc.player.getVehicle() != null)
            {
                if (mount != null)
                {
                    ChatUtil.error("Entity vanished, must remount!");
                    return;
                }
                mount = mc.player.getVehicle();
                mc.player.dismountVehicle();
                mc.world.removeEntity(mount.getId(), Entity.RemovalReason.DISCARDED);
            }
        }
        else if (dismount.equalsIgnoreCase("remount"))
        {
            if (mount == null)
            {
                ChatUtil.error("No vanished entity!");
                return;
            }
            //
            ((AccessorEntity) mount).hookUnsetRemoved();
            mc.world.addEntity(mount.getId(), mount);
            mc.player.startRiding(mount, true);
            mount = null;
        }
    }
}
