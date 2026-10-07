import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // OpenGL wird geladen
        GLFW.glfwInit();

        // OpenGL Fenster erstellen
        long Window_Main = GLFW.glfwCreateWindow(
                800, 600,
                "MONYs Küche",
                0, 0
        );

        // OpenGL mit Fenster verbinden
        GLFW.glfwMakeContextCurrent(Window_Main);

        // OpenGL zeichnet das erstellte Fenster
        GL.createCapabilities();

        boolean sichtbar = true;
        int mode = -1;
        boolean spaceWasPressed = false;

        // Solange das Fenster nicht geschlossen wird (Quasi Main Loop)
        while (!GLFW.glfwWindowShouldClose(Window_Main))
        {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            if (GLFW.glfwGetKey(Window_Main,GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS)
            {
                if (!spaceWasPressed)
                {
                    sichtbar = !sichtbar;
                    System.out.println("Space was pressed!");

                    mode++;
                    System.out.println("Modus-Auswahl: [" + mode + "]");

                    if (mode > 2)
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
                    drawCubeSmaller();
                    drawText.drawText(10, 10, "Auswahl: Quadrat-Loop",2f,1f,1f,1f);
                    break;
                default:
                    drawText.drawText(20, 20, "Druecke [LEERTASTE] um fortzufahren!",3f,0f,1f,1f);
            }


            GLFW.glfwSwapBuffers(Window_Main);
            GLFW.glfwPollEvents();
        }

        // Fenster schließen
        GLFW.glfwTerminate();
    }

    public static void drawCubeSmaller()
    {
        // Kamera Einstellungen
        GL11.glMatrixMode(GL11.GL_PROJECTION);

        // Setzt Transform zurück
        GL11.glLoadIdentity();

        // Sichtbarer Bereich von -1 - 1
        GL11.glOrtho(-1, 1, -1, 1, -1, 1);

        // Setzt Kamera einstellung
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // Setzt Transform zurück
        GL11.glLoadIdentity();

        for (float i = 0.1f; i <= 0.5f; i += 0.1f) {
            GL11.glBegin(GL11.GL_LINES);
            drawObjects.drawCube(i);
            GL11.glEnd();
        }
    }
}