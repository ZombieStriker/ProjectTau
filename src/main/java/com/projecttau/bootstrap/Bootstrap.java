package com.projecttau.bootstrap;

import net.minestom.converter.StandaloneConverter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerCommandEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.*;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;
import net.minestom.server.storage.StorageLocation;
import net.minestom.server.storage.StorageOptions;
import net.minestom.server.storage.systems.FileStorageSystem;
import net.minestom.server.utils.Position;
import net.minestom.server.world.biomes.Biome;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Bootstrap {

    private static MinecraftServer minecraftServer;
    private static InstanceManager instanceManager;
    private static InstanceContainer instanceContainer;

    public static InstanceContainer getInstanceContainer(){
        return instanceContainer;
    }
    public static void main(String... args){
        minecraftServer= MinecraftServer.init();
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
        });

        minecraftServer.getCommandManager().register(new TestSaveCommand());

        minecraftServer.start("0.0.0.0", 25565);

    }

    private static class GeneratorDemo implements ChunkGenerator {

        @Override
        public void generateChunkData(ChunkBatch batch, int chunkX, int chunkZ) {
            // Set chunk blocks
            if(chunkX==0&&chunkZ==0) {
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
