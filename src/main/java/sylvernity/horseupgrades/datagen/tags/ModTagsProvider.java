package sylvernity.horseupgrades.datagen.tags;

import com.sylvernity.horseupgrades.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;

public class ModTagsProvider<T> extends BlockTagsProvider {

    public ModTagsProvider(DataGenerator pGenerator, Registry<T> pRegistry, String modId){
        super(pGenerator);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.HORSESHOE_ANVIL.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.HORSESHOE_ANVIL.get());
        this.tag(BlockTags.ANVIL).add(ModBlocks.HORSESHOE_ANVIL.get());
        // Adds an object to the tag
    }
}
