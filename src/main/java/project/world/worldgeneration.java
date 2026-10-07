package project.world;

import org.lwjgl.opengl.GL11;
import project.helper.drawObjects;
import project.player;
import project.settings.renderingSettings;

import java.util.Random;

public class worldgeneration {

    static boolean isWorldLoaded = false;
    static int[] worldHeights = new int[20];
    static Random random = new Random();

    public static void generateWorld()
    {
        renderingSettings.applySettings();

        // Welt nur EINMAL generieren
        if (!isWorldLoaded)
        {
            for (int i = 0; i < worldHeights.length; i++)
            {
                worldHeights[i] = random.nextInt(6);
            }
            isWorldLoaded = true;
        }

        // Kamera (erst Rotation, dann Position)
        GL11.glRotatef(player.xRotation, 1f, 0f, 0f);
        GL11.glRotatef(player.yRotation, 0f, 1f, 0f);
        GL11.glTranslatef(-player.playerX, -player.playerY, -player.playerZ);

        // Welt zeichnen
        for (int x = 0; x < worldHeights.length; x++)
        {
            GL11.glPushMatrix();

            GL11.glTranslatef(x, worldHeights[x], 0f);

            drawObjects.drawCube3D(0.5f);

            GL11.glPopMatrix();
        }
    }
}
