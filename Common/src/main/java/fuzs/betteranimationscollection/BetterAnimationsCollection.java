package fuzs.betteranimationscollection;

import fuzs.betteranimationscollection.config.ClientConfig;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.core.CoreServices;
import fuzs.puzzleslib.core.ModConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterAnimationsCollection implements ModConstructor {
    public static final String MOD_ID = "betteranimationscollection";
    public static final String MOD_NAME = "Better Animations Collection";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder CONFIG = CoreServices.FACTORIES.clientConfig(ClientConfig.class, () -> new ClientConfig());
}
