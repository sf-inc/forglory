package com.github.galatynf.forglory.items.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.GemsInit;
import com.github.galatynf.forglory.items.PoweredGem;

public class MachineGunGem extends PoweredGem {
    public MachineGunGem(Settings settings) {
        super(settings);
        feat = Feats.MACHINE_GUN;
        gem = GemsInit.damageGem;
    }
}
