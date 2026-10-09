
package project.minecraft.block;

import java.util.ArrayList;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import project.Main;
import project.helper.drawObjects;
import project.minecraft.player;

public class BlockWorld {

    public static ArrayList<Block> blocks = new ArrayList<>();

    static boolean lastRightClick = false;

    public static void addBlock(int x, int y, int z) {
        for (Block b : blocks) {
            if (b.x == x && b.y == y && b.z == z)
                return;
        }

        blocks.add(new Block(x, y, z,BlockType.DIRT));
    }

    public static void render() {
        for (Block b : blocks) {
            GL11.glPushMatrix();

            GL11.glTranslatef(b.x, b.y, b.z);
            drawObjects.drawCubeSmaller();

            GL11.glPopMatrix();
        }
    }

    public static void update() {
        boolean rightClick = GLFW.glfwGetMouseButton(Main.Window_Main, GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS;

        if (rightClick && !lastRightClick) {
            // Testweise einen Block vor dem Spieler platzieren
            int x = Math.round(player.playerX);
            int y = Math.round(player.playerY);
            int z = Math.round(player.playerZ - 2);

            addBlock(x, y, z);
        }

        lastRightClick = rightClick;
    }
}
