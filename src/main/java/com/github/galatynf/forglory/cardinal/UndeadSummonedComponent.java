package com.github.galatynf.forglory.cardinal;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class UndeadSummonedComponent implements UndeadComponent {

    private UUID summonerID = null;

    @Override public void readFromNbt(CompoundTag tag) {
        if(tag.getBoolean("noSummoner")) {
            summonerID = null;
        }
        else {
            summonerID = tag.getUuid("summoner");
        }
    }

    @Override public void writeToNbt(CompoundTag tag) {
        if(summonerID !=null) {
            tag.putUuid("summoner", summonerID);
            tag.putBoolean("noSummoner", false);
        }
        else {
            tag.putBoolean("noSummoner", true);
        }
    }

    @Override
    public void setSummoner(UUID player) {
        summonerID = player;
    }

    @Override
    public UUID getSummoner() {
        return summonerID;
    }
}
