package me.earth.pingbypass.api.platform;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.earth.pingbypass.api.util.ReflectionUtil;
import org.jetbrains.annotations.VisibleForTesting;

@Slf4j
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED, onConstructor_={@VisibleForTesting})
public class PlatformProvider {
    private static final PlatformProvider INSTANCE;

    private final PlatformService platformService;
    private final Platform current;

    public static PlatformProvider getInstance() {
        return INSTANCE;
    }

    static {
        Platform platform;
        PlatformService platformService;
        platform = Platform.FABRIC;
        platformService = new FabricPlatformService();

        log.info("Detected platform: " + platform.getName());
        INSTANCE = new PlatformProvider(platformService, platform);
    }

}
