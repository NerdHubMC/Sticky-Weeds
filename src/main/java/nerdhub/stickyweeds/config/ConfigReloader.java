package nerdhub.stickyweeds.config;

import nerdhub.stickyweeds.StickyWeeds;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

@Deprecated
public class ConfigReloader implements SimpleSynchronousResourceReloadListener {

    private static final Identifier ID = new Identifier(StickyWeeds.MODID, "config_reloader");

    public static void init() {
        StickyWeeds.logger.debug("enabling config reloader");
        ResourceManagerHelper.get(ResourceType.DATA).registerReloadListener(new ConfigReloader());
    }

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public void apply(ResourceManager var1) {
        StickyWeeds.logger.debug("reloading configs");
        ConfigHandler.getRegisteredConfigs().forEach(ConfigHandler::reloadConfig);
    }
}