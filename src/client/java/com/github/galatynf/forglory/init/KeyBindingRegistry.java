package com.github.galatynf.forglory.init;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindingRegistry {
    private KeyBindingRegistry() {
    }

    public static final KeyBinding ACTIVATE_FEAT = new KeyBinding(
            "key.forglory.activateFeatKey",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            "category.forglory.keys"
    );

    public static void init() {
        KeyBindingHelper.registerKeyBinding(ACTIVATE_FEAT);
    }
}
