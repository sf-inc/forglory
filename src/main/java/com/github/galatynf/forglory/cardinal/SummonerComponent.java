package com.github.galatynf.forglory.cardinal;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;

import java.util.UUID;

public class SummonerComponent implements PlayerComponent {
    private UUID summonerID = null;

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        if (tag.getBoolean("noSummoner")) {
            this.summonerID = null;
        } else {
            this.summonerID = tag.getUuid("summoner");
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        if (this.summonerID != null) {
            tag.putUuid("summoner", this.summonerID);
            tag.putBoolean("noSummoner", false);
        } else {
            tag.putBoolean("noSummoner", true);
        }
    }

    @Override
    public void setPlayer(final UUID player) {
        this.summonerID = player;
    }

    @Override
    public UUID getPlayer() {
        return this.summonerID;
    }
}
