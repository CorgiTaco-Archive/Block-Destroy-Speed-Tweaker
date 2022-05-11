package corgitaco.blockdestroyspeedtweaker.mixin;

import corgitaco.blockdestroyspeedtweaker.BreakSpeedConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
public class MixinInventory {

    @Shadow
    @Final
    public NonNullList<ItemStack> items;

    @Shadow
    public int selected;

    @Inject(at = @At("RETURN"), method = "getDestroySpeed", cancellable = true)
    private void modifyBreakSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        ItemStack itemStack = this.items.get(this.selected);
        cir.setReturnValue(BreakSpeedConfig.getConfig().itemToBreakSpeed().getOrDefault(itemStack.getItem(), cir.getReturnValue()));
    }
}