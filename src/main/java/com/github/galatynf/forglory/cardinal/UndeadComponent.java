package com.github.galatynf.forglory.cardinal;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

import java.util.UUID;

public interface UndeadComponent extends ComponentV3 {
    void setSummoner(UUID summoner);
    UUID getSummoner();
}
