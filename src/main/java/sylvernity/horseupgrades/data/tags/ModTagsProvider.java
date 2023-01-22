package sylvernity.horseupgrades.data.tags;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import sylvernity.horseupgrades.block.ModBlocks;

public class ModTagsProvider<T> extends TagsProvider<T> {

    public ModTagsProvider(DataGenerator pGenerator, Registry<T> pRegistry, String modId) {
        super(pGenerator, pRegistry, modId, null);

    }

    @Override
    protected void addTags() {
        T anvil = (T) ModBlocks.HORSESHOE_ANVIL;
        this.tag((TagKey<T>) BlockTags.NEEDS_IRON_TOOL).add(anvil);
        this.tag((TagKey<T>) BlockTags.MINEABLE_WITH_PICKAXE).add(anvil);
        this.tag((TagKey<T>) BlockTags.ANVIL).add(anvil);
        // Adds an object to the tag
    }
}
