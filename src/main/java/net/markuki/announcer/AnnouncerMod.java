package net.markuki.announcer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnouncerMod implements ModInitializer {
    public static final String MOD_ID = "announcer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing PlayersConnections mod");

        // Register player join event
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            String message = String.format("[PlayersConnections] %s joined the game!", player.getName().getString());
            server.getPlayerManager().broadcast(Text.literal(message), false);
        });

        // Register player disconnect event
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            String message = String.format("[PlayersConnections] %s left the game!", handler.getPlayer().getName().getString());
            server.getPlayerManager().broadcast(Text.literal(message), false);
        });
    }
} 