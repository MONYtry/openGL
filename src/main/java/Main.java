import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main {

    static float playerX = 0f;
    static float playerY = 0f;
    static float playerZ = 0f;
    static float yrotation = 0f;
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

        // OpenGL mit Fenster verbinden
        GLFW.glfwMakeContextCurrent(Window_Main);

        // OpenGL zeichnet das erstellte Fenster
        GL.createCapabilities();



        // Solange das Fenster nicht geschlossen wird (Quasi Main Loop)
        while (!GLFW.glfwWindowShouldClose(Window_Main))
        {
            GLFW.glfwPollEvents();
            movment();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);


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
                    GL11.glTranslatef(playerX, playerY, 0f);
                    GL11.glRotatef(yrotation,0f,1f,0f);

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
                    GL11.glTranslatef(playerX, playerY, 0f);
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


        GL11.glTranslatef(playerX, playerY, playerZ);

        GL11.glTranslatef(0f, 0f, -3f);
        GL11.glRotatef(yrotation, 0f, 1f, 0f);

        GL11.glBegin(GL11.GL_LINES);
        drawObjects.drawCube3D(0.5f);
        GL11.glEnd();
    }


    static void movment()
    {

        // Links
        if (GLFW.glfwGetKey(Window_Main, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS)
        {
            playerX += 0.01f;
        }
        // Rechts
        if (GLFW.glfwGetKey(Window_Main, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS)
        {
            playerX -= 0.01f;
        }

        // Vorwerts
        if (GLFW.glfwGetKey(Window_Main, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS)
        {
            playerZ += 0.01f;
        }

        // Rückwerts
        if (GLFW.glfwGetKey(Window_Main, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS)
        {
            playerZ -= 0.01f;
        }

        if (GLFW.glfwGetKey(Window_Main, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS)
        {
            playerY -= 0.02f;
        }

        if (GLFW.glfwGetKey(Window_Main, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS)
        {
            playerY += 0.02f;
        }


    }
}