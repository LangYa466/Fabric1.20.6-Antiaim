package cn.langya.kxdsjxsw;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

/**
 * @author LangYa
 * @since 2024/6/1 下午2:12
 */

public class Mod implements ModInitializer {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private boolean state;

    public void onPlayerTick() {
        if (!state) return;
        if (mc.world != null && mc.player != null) {
            mc.player.headYaw = check(mc.player.headYaw);
            mc.player.bodyYaw = check(mc.player.bodyYaw);
            mc.player.elytraYaw = check(mc.player.elytraYaw);
            mc.player.prevYaw = check(mc.player.prevYaw);
            mc.player.prevPitch = check(mc.player.prevPitch);
            mc.player.elytraPitch = check(mc.player.elytraPitch);
        }
    }

    private float check(float rotation) {
        rotation+= 10;
        if (rotation <= 180) rotation = 0;
        return rotation;
    }

    @Override
    public void onInitialize() {
        System.out.println("狂笑的蛇将写散文初始化完毕");

        // r
        int bindKeyCode = 82;
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "狂笑的蛇将写散文 开启按键",
                InputUtil.Type.KEYSYM,
                bindKeyCode,
                "category.狂笑的蛇将写散文"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                state = !state;
                if (this.state) {
                    System.out.println("狂笑的蛇将写散文 已开启");
                } else {
                    System.out.println("狂笑的蛇将写散文 已关闭");
                }
            }
        });
    }


}
