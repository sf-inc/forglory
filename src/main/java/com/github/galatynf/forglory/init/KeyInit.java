package com.github.galatynf.forglory.init;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInit {
    private KeyInit() {
    }

    public static KeyBinding activateFeatKey;

    public static void initClient() {
        activateFeatKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.forglory.activateFeatKey",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                "category.forglory.keys"
        ));
    }
}
