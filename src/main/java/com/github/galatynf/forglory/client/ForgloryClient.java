package com.github.galatynf.forglory.client;

import com.github.galatynf.forglory.gui.AdrenalinBar;
import com.github.galatynf.forglory.init.BlocksInit;
import com.github.galatynf.forglory.init.KeyInit;
import com.github.galatynf.forglory.init.NetworkInit;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ForgloryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlocksInit.initClient();
        KeyInit.initClient();
        NetworkInit.initClient();

        AdrenalinBar adrenalinBar = new AdrenalinBar();
        CottonHud.INSTANCE.add(adrenalinBar, 135, -37, 9, 34);
    }
}
