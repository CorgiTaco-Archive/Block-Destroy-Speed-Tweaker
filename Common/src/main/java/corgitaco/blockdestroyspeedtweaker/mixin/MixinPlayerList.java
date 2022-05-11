package corgitaco.blockdestroyspeedtweaker.mixin;

import corgitaco.blockdestroyspeedtweaker.BreakSpeedConfig;
import corgitaco.blockdestroyspeedtweaker.network.ConfigSyncPacket;
import corgitaco.blockdestroyspeedtweaker.platform.Services;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class MixinPlayerList {

    @Inject(method = "sendLevelInfo", at = @At(value = "HEAD"))
    private void sendContext(ServerPlayer playerIn, ServerLevel worldIn, CallbackInfo ci) {
        Services.PLATFORM.sendToClient(playerIn, new ConfigSyncPacket(BreakSpeedConfig.getConfig()));
    }
}
