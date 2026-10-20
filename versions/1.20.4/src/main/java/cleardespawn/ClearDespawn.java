package cleardespawn;

import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(ClearDespawn.MOD_ID)
public final class ClearDespawn {
    public static final String MOD_ID = "cleardespawn";

    public ClearDespawn(IEventBus modBus) {
        if (FMLEnvironment.dist != Dist.CLIENT) return;
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
        modBus.addListener(ClearDespawn::registerRenderers);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityType.ITEM, RenderItemEntityExtended::new);
    }
}
