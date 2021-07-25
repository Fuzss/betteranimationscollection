package fuzs.betteranimationscollection.client.element;

import fuzs.puzzleslib.config.ConfigManager;
import fuzs.puzzleslib.config.option.OptionsBuilder;
import fuzs.puzzleslib.element.AbstractElement;
import fuzs.puzzleslib.element.side.IClientElement;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

/**
 * this element is handled via a mixin instead of replacing the model as usual due to it being a really bad idea to replace the player model as many mods depend on it
 */
public class AnimatedPlayerElement extends AbstractElement implements IClientElement {

    public boolean eatingAnimation;
    public boolean rowingAnimation;
    public boolean ridingAnimation;
    public Set<Item> inspectableItems;

    @Override
    public String[] getDescription() {

        return new String[]{"A collection of small animations for the player."};
    }

    @Override
    public void setupClientConfig(OptionsBuilder builder) {

        builder.define("Eating Animation", true).comment("Animate eating in third-person view.").sync(v -> this.eatingAnimation = v);
        builder.define("Rowing Animation", true).comment("The player's arms actually move when rowing in a boat.").sync(v -> this.rowingAnimation = v);
        builder.define("Riding Animation", true).comment("Make the player hold on to a horse's reins.").sync(v -> this.ridingAnimation = v);
        builder.define("Inspectable Items", ConfigManager.getKeyList(Items.COMPASS, Items.CLOCK)).comment("Unique items the player should take a closer look at when holding.").sync(v -> this.inspectableItems = ConfigManager.deserializeToSet(v, ForgeRegistries.ITEMS));
    }

}
