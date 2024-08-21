package com.github.galatynf.forglory.cardinal;

import org.ladysnake.cca.api.v3.component.Component;

import java.util.UUID;

public interface PlayerComponent extends Component {
    UUID getPlayer();

    void setPlayer(UUID player);
}
