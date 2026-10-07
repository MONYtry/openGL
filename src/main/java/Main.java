import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Main {

    static long Window_Main;
    static boolean isWorldLoaded = false;
    static int[] worldHeights = new int[20];
    static Random random = new Random();


    public static void main(String[] args)
    {

        boolean sichtbar = true;
        int mode = 0;
        boolean spaceWasPressed = false;


        // OpenGL wird geladen
        GLFW.glfwInit();

        // OpenGL Fenster erstellen
        Window_Main = GLFW.glfwCreateWindow(
                800, 600,
                "MONYs Küche",
                0, 0
        );

        GLFW.glfwSetInputMode(Window_Main, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

        // OpenGL mit Fenster verbinden
        GLFW.glfwMakeContextCurrent(Window_Main);

        // OpenGL zeichnet das erstellte Fenster
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);



        // Solange das Fenster nicht geschlossen wird (Quasi Main Loop)
        while (!GLFW.glfwWindowShouldClose(Window_Main))
        {
            GLFW.glfwPollEvents();
            player.movment();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


            if (GLFW.glfwGetKey(Window_Main,GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS)
            {
                if (!spaceWasPressed)
                {
                    sichtbar = !sichtbar;
                    System.out.println("Space was pressed!");

                    mode++;
                    System.out.println("Modus-Auswahl: [" + mode + "]");

                    if (mode > 3)
                    {
                        System.out.println("Modus-Auswahl wurde zurückgesetzt!");
                        mode = 0;
                    }

                    spaceWasPressed = true;
                }
            }
            else
            {
                spaceWasPressed = false;
            }

            switch (mode)
            {
                case (0):
                    drawObjects.drawCubeSmaller();
                    drawText.drawText(10, 10, "Auswahl: Quadrat-Loop",2f,1f,1f,1f);
                    break;

                case (1):
                    settings.applySettings();
                    // Spieler bewegen
                    GL11.glTranslatef(player.playerX, player.playerY, 0f);

                    // Dreieck zeichnen
                    drawObjects.drawTriangle(0.5f);

                    // Text
                    drawText.drawText(10, 10, "Auswahl: Dreieck", 2f, 1f, 1f, 1f);
                    break;

                case (2):
                    drawText.drawText(10, 10, "Auswahl: Dreieck-Loop",2f,1f,1f,1f);
                    drawObjects.drawTrianglesSmaller();
                    break;
                case (3):
                    drawText.drawText(10, 10, "Auswahl: Rotierender Würfel",2f,1f,1f,1f);
                    make3DCube();
                    break;
                default:
                    drawText.drawText(20, 20, "Druecke [LEERTASTE] um fortzufahren!",3f,0f,1f,1f);
            }

            GLFW.glfwSwapBuffers(Window_Main);
        }

        // Fenster schließen
        GLFW.glfwTerminate();
    }




    static void make3DCube()
    {
        settings.apply3DSettings();

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