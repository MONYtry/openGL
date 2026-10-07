package project;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import project.helper.drawObjects;
import project.helper.drawText;
import project.settings.renderingSettings;
import project.world.worldgeneration;

public class Main {

    static long Window_Main;

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
        // Deaktiviert den Cursor
        GLFW.glfwSetInputMode(Window_Main, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

        // OpenGL mit Fenster verbinden
        GLFW.glfwMakeContextCurrent(Window_Main);

        // OpenGL zeichnet das erstellte Fenster
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);



        // Solange das Fenster nicht geschlossen wird (Quasi project.Main Loop)
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
                    renderingSettings.applySettings();
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
                    drawText.drawText(10, 10, "Auswahl: Minecraft-Clone",2f,1f,1f,1f);
                    worldgeneration.generateWorld();
                    break;
                default:
                    drawText.drawText(20, 20, "Druecke [LEERTASTE] um fortzufahren!",3f,0f,1f,1f);
            }

            GLFW.glfwSwapBuffers(Window_Main);
        }

        // Fenster schließen
        GLFW.glfwTerminate();
    }
}