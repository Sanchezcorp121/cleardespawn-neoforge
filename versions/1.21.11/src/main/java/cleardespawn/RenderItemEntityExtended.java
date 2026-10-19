package cleardespawn;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.entity.item.ItemEntity;

// Newer versions render from a state object, so the despawn check runs while the state is filled in
// and the item is skipped when the state is submitted. Same timing rules as before.
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
    public void submit(ItemEntityRenderState state, PoseStack matrixStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state instanceof State s && s.hidden) {
            return;
        }
        super.submit(state, matrixStack, submitNodeCollector, camera);
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
