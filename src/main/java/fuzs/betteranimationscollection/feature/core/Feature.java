package fuzs.betteranimationscollection.feature.core;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeConfigSpec;

@SuppressWarnings("unused")
public abstract class Feature<T extends Entity> {

    private ForgeConfigSpec.BooleanValue enable;

    public void register() {

        if (this.shouldLoad()) {
            this.loadModel();
        }
    }

    protected abstract void loadModel();

    protected boolean shouldLoad() {

        return this.enable != null && this.enable.get();
    }

    public abstract String getName();

    public abstract String getDescription();

    public void setupConfig(ForgeConfigSpec.Builder builder) {

        this.enable = builder.comment("Is this feature enabled.").define("enabled", true);
    }

}
