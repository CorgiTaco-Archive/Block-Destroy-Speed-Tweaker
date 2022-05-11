package corgitaco.blockdestroyspeedtweaker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Constants.MOD_ID)
public class BlockDestroySpeedTweakerForge {

    public BlockDestroySpeedTweakerForge() {
        MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);

    }

    private void onItemTooltip(ItemTooltipEvent event) {

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        BlockDestroySpeedTweaker.init();
    }
}