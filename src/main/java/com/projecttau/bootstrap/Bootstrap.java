package com.projecttau.bootstrap;

import com.projecttau.defaults.commands.CommandManager;
import com.projecttau.defaults.functions.FunctionManager;
import com.projecttau.hub.ServersGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.color.Color;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.optifine.OptifineSupport;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.instance.*;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.storage.systems.FileStorageSystem;
import net.minestom.server.utils.Position;
import net.minestom.server.world.biomes.Biome;

import java.util.Arrays;
import java.util.List;

public class Bootstrap {

    private static MinecraftServer minecraftServer;
    private static InstanceManager instanceManager;
    private static InstanceContainer instanceContainer;

    public static InstanceContainer getInstanceContainer() {
        return instanceContainer;
    }

    public static void main(String... args) {
        minecraftServer = MinecraftServer.init();
        instanceManager = MinecraftServer.getInstanceManager();
        // Create the instance
        MinecraftServer.getStorageManager().defineDefaultStorageSystem(FileStorageSystem::new);
        instanceContainer = instanceManager.createInstanceContainer(MinecraftServer.getStorageManager().getLocation("world_chunk_data"));
        // Set the ChunkGenerator
        instanceContainer.setChunkGenerator(new GeneratorDemo());

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addEventCallback(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Position(0, 3, 0));
            player.getInventory().addItemStack(ItemStack.of(Material.COMPASS).withDisplayName(Component.text( "Branch Selector").color(TextColor.color(255, 57, 28))));
        });

        CommandManager.init();
        FunctionManager.init(globalEventHandler);

        globalEventHandler.addEventCallback(PlayerUseItemEvent.class, event -> {
            final Player player = event.getPlayer();
            ServersGUI.open(player);
        });


        OptifineSupport.enable();

        VelocityProxy.enable("hfRCTwEyVNSJ");

        minecraftServer.start("0.0.0.0", 25565);

    }

    private static class GeneratorDemo implements ChunkGenerator {

        @Override
        public void generateChunkData(ChunkBatch batch, int chunkX, int chunkZ) {
            // Set chunk blocks
            if (chunkX == 0 && chunkZ == 0) {
                for (byte x = 0; x < Chunk.CHUNK_SIZE_X; x++)
                    for (byte z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                        for (byte y = 0; y < 1; y++) {
                            batch.setBlock(x, y, z, Block.STONE);
                        }
                    }
            }
        }

        @Override
        public void fillBiomes(Biome[] biomes, int chunkX, int chunkZ) {
            Arrays.fill(biomes, Biome.PLAINS);
        }

        @Override
        public List<ChunkPopulator> getPopulators() {
            return null;
        }
    }
}
