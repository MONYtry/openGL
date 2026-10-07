package project.world;

import org.lwjgl.opengl.GL11;
import project.helper.TextureLoader;
import project.helper.drawObjects;
import project.player;
import project.settings.renderingSettings;

import java.util.Random;

public class worldgeneration
{
    static int zaxes = 0;
    static boolean isWorldLoaded = false;
    static Random random = new Random();
    static boolean isMessageSend = false;

    static int[] randomHEIGHT = new int[5];
    static int grassTexture;

    static
    {
        for (int i = 0; i < randomHEIGHT.length; i++)
        {
            randomHEIGHT[i]= random.nextInt(6) + 1;
        }
    }
    static
    {
        grassTexture = TextureLoader.loadTexture("textures/dirt.png");
    }

    public static void generateWorld()
    {
        if (!isWorldLoaded)
        {
            isWorldLoaded = true;
            System.out.println("Map: Wurde geladen!");
        }
        else
        {
            if (!isMessageSend) {
                System.out.println("Map: Ist schon geladen!");
                isMessageSend = true;
            }
        }

            renderingSettings.apply3DSettings();

            // Kamera (erst Rotation, dann Position)
            GL11.glRotatef(player.xRotation, 1f, 0f, 0f);
            GL11.glRotatef(player.yRotation, 0f, 1f, 0f);
            GL11.glTranslatef(-player.playerX, -player.playerY, -player.playerZ);
            for (int height = 0; height < 3; height++)
            {

                for (int length = 0; length < 3; length++)
                {
                    for (int x = 0; x < 3; x++)
                    {
                        GL11.glPushMatrix();
                        GL11.glTranslatef(x, height, 0);
                        drawObjects.drawCube3D(0.5f);
                        GL11.glPopMatrix();
                    }

                    for (int z_axis = 0; z_axis < 3; z_axis++)
                    {
                        GL11.glPushMatrix();
                        GL11.glTranslatef(length, height, z_axis);
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glBindTexture(GL11.GL_TEXTURE_2D, grassTexture);

                        drawObjects.drawCube3D(0.5f);
                        GL11.glPopMatrix();
                    }
                }
            }
    }
}
