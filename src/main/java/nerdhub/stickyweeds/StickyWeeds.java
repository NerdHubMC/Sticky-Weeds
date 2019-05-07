package nerdhub.stickyweeds;

import nerdhub.stickyweeds.config.ConfigHandler;
import nerdhub.stickyweeds.config.ConfigReloader;
import nerdhub.stickyweeds.config.SWConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StickyWeeds implements ModInitializer {

    public static final String MODID = "sticky_weeds";
    public static final Logger logger = LogManager.getLogger(MODID);
    public static Tag<Block> GRASS_LIKE;
    public static Tag<Block> CROP_LIKE;

    @Override
    public void onInitialize() {
        logger.info("walking through tall grass is hard, isn't it?");
        ConfigHandler.registerConfig(MODID, SWConfig.class);
        ConfigReloader.init();
        GRASS_LIKE = TagRegistry.block(new Identifier("c", "grass_like"));
        CROP_LIKE = TagRegistry.block(new Identifier("c", "crop_like"));
    }

    public static SWConfig getConfig() {
        return ConfigHandler.getConfig(SWConfig.class);
    }
}
