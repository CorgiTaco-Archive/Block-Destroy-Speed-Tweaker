package corgitaco.blockdestroyspeedtweaker;

import corgitaco.blockdestroyspeedtweaker.network.FabricNetworkHandler;
import net.fabricmc.api.ModInitializer;

public class BlockDestroySpeedTweakerFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockDestroySpeedTweaker.init();
        FabricNetworkHandler.init();
    }
}
