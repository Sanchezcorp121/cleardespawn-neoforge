package cleardespawn;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.world.entity.item.ItemEntity;

public class RenderItemEntityExtended extends ItemEntityRenderer {
    public RenderItemEntityExtended(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ItemEntityRenderState createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(ItemEntity entity, ItemEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        ((State) state).hidden = isHidden(entity.lifespan - entity.getAge());
    }

    @Override
    public void render(ItemEntityRenderState state, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        if (state instanceof State s && s.hidden) {
            return;
        }
        super.render(state, matrixStack, buffer, packedLight);
    }

    static boolean isHidden(int remainingTime) {
        if (remainingTime <= 20 * Config.CLIENT.flashStartTime.get()) {
            if (Config.CLIENT.urgentFlash.get()) {
                int flashFactor = Math.max(2, remainingTime / 20);
                return remainingTime % flashFactor < 0.5f * flashFactor;
            }
            return remainingTime % 20 < 10;
        }
        return false;
    }

    public static class State extends ItemEntityRenderState {
        public boolean hidden;
    }
}
