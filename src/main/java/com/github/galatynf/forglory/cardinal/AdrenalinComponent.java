package com.github.galatynf.forglory.cardinal;

import org.ladysnake.cca.api.v3.component.Component;

public interface AdrenalinComponent extends Component {
    float getAdrenalin();

    void setAdrenalin(float newAdrenalin);
    void addAdrenalin(float amount);
}
