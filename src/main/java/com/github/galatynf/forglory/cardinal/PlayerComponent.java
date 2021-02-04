package com.github.galatynf.forglory.cardinal;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

import java.util.UUID;

public interface PlayerComponent extends ComponentV3 {
    void setPlayer(UUID player);

    UUID getPlayer();
}
