package com.caspian.client.mixin.network.packet.c2s;

import com.caspian.client.impl.imixin.IPlayerInteractEntityC2SPacket;
import com.caspian.client.util.Globals;
import com.caspian.client.util.network.InteractType;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 *
 *
 * @author linus
 * @since 1.0
 *
 * @see PlayerInteractEntityC2SPacket
 */
@Mixin(PlayerInteractEntityC2SPacket.class)
public abstract class MixinPlayerInteractEntityC2SPacket implements
        IPlayerInteractEntityC2SPacket, Globals
{
    // Mojang mane wtf ..
    //
    @Shadow
    @Final
    private int entityId;

    @Shadow public abstract void write(PacketByteBuf buf);

    /**
     *
     * @return
     */
    @Override
    public Entity getEntity()
    {
        return mc.world.getEntityById(entityId);
    }

    /**
     * Scuffed fix for IllegalAccessError net.minecraft.class_2824$class_5907?
     *
     * @return
     *
     * @see
     * <a href="https://github.com/BleachDev/BleachHack/blob/1.19.4/src/main/java/org/bleachhack/util/PlayerInteractEntityC2SUtils.java#L19">Bleachdev</a>
     */
    @Override
    public InteractType getType()
    {
        PacketByteBuf packetBuf = new PacketByteBuf(Unpooled.buffer());
        write(packetBuf);
        packetBuf.readVarInt();
        return packetBuf.readEnumConstant(InteractType.class);
    }
}


