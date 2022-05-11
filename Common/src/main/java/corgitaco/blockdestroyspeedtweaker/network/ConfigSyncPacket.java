package corgitaco.blockdestroyspeedtweaker.network;

import corgitaco.blockdestroyspeedtweaker.BreakSpeedConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;

public record ConfigSyncPacket(BreakSpeedConfig config) implements S2CPacket {

    public static ConfigSyncPacket readFromPacket(FriendlyByteBuf buf) {
        try {
            return new ConfigSyncPacket(buf.readWithCodec(BreakSpeedConfig.CODEC));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        try {
            buf.writeWithCodec(BreakSpeedConfig.CODEC, this.config);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void handle(Level level) {
        BreakSpeedConfig.setConfig(this.config);
    }
}
