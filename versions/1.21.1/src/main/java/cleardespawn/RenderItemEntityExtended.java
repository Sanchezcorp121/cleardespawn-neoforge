package cleardespawn;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.item.ItemEntity;

public class RenderItemEntityExtended extends ItemEntityRenderer {
    public RenderItemEntityExtended(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ItemEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        int remainingTime = entity.lifespan - entity.getAge();
        if (remainingTime <= 20 * Config.CLIENT.flashStartTime.get()) {
            if (Config.CLIENT.urgentFlash.get()) {
                int flashFactor = Math.max(2, remainingTime / 20);
                if (remainingTime % flashFactor < 0.5f * flashFactor) {
                    return;
                }
            } else if (remainingTime % 20 < 10) {
                return;
            }
        }
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
}
