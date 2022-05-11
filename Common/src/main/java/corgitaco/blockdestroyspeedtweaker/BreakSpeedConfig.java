package corgitaco.blockdestroyspeedtweaker;

import blue.endless.jankson.api.SyntaxError;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import corgitaco.blockdestroyspeedtweaker.platform.Services;
import corgitaco.blockdestroyspeedtweaker.util.jankson.JanksonJsonOps;
import corgitaco.blockdestroyspeedtweaker.util.jankson.JanksonUtil;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public record BreakSpeedConfig(Map<Item, Float> itemToBreakSpeed) {

    public static final Codec<BreakSpeedConfig> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
            Codec.unboundedMap(Registry.ITEM.byNameCodec(), Codec.FLOAT).fieldOf("item_to_break_speed").forGetter(config -> config.itemToBreakSpeed)
        ).apply(builder, BreakSpeedConfig::new)
    );

    public static BreakSpeedConfig INSTANCE = null;

    private static final BreakSpeedConfig DEFAULT = new BreakSpeedConfig(new Object2ObjectOpenHashMap<>());


    public static BreakSpeedConfig getConfig() {
        return getConfig(false);
    }

    public static BreakSpeedConfig getConfig(boolean serialize) {
        if (serialize || INSTANCE == null) {
            read(Services.PLATFORM.configDir().resolve(Constants.MOD_ID + ".json5"));
        }
        return INSTANCE;
    }

    public static BreakSpeedConfig setConfig(BreakSpeedConfig config) {
        INSTANCE = config;
        return INSTANCE;
    }

    public static BreakSpeedConfig read(Path path) {
        try {
            if (!path.toFile().exists()) {
                JanksonUtil.createConfig(path, CODEC, JanksonUtil.HEADER_CLOSED, ImmutableMap.of("item_to_break_speed",
                    """
                        Map specifying the break speed of a specific item against a block.
                                                
                        Example:
                        "item_to_break_speed": {
                            "minecraft:diamond_pickaxe": 0,
                            "minecraft:stick": 55.9768
                        }
                        
                        In the example,
                        - "minecraft:diamond_pickaxe" & "minecraft:stick", are the item registry ID.
                        - 0 & 55.9768 are the destroy speed. Destroy speed is a 32 bit decimal/float value.
                        """), JanksonJsonOps.INSTANCE, DEFAULT);
            }

            INSTANCE = JanksonUtil.readConfig(path, CODEC, JanksonJsonOps.INSTANCE);
            return INSTANCE;
        } catch (IOException | SyntaxError e) {
            throw new IllegalArgumentException(e);
        }
    }
}
