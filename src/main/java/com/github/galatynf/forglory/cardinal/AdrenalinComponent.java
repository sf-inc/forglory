package com.github.galatynf.forglory.cardinal;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

public interface AdrenalinComponent extends ComponentV3 {
    float getAdrenalin();
    void setAdrenalin(float newAdrenalin);
    void addAdrenalin(float amount);
}
