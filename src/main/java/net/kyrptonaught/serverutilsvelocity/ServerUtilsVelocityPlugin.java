package net.kyrptonaught.serverutilsvelocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;


import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(
    id = "serverutils-velocity",
    name = "ServerUtils Velocity Plugin",
    version = "0.0.1",
    authors = {"kyptonaught"}
)
public class ServerUtilsVelocityPlugin {
    public static final ChannelIdentifier HANDSHAKE_ID = MinecraftChannelIdentifier.create("scoreboardplayerinfo", "has_mods_packet");

    private final ProxyServer proxyServer;
    private final Logger logger;
    private final Path dataDirectory;

    @Inject
    public ServerUtilsVelocityPlugin(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.proxyServer = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        proxyServer.getChannelRegistrar().register(HANDSHAKE_ID);
        proxyServer.getEventManager().register(this, new ServerUtilsHandshakeHandler());

    }
}
