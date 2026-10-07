import org.lwjgl.glfw.GLFW;

public class player
{
    // Spieler Variablen
    static float playerX = 0f;
    static float playerY = 0f;
    static float playerZ = 0f;

    static double lastMouseX = 0f;
    static double lastMouseY = 0f;

    static float xRotation = 0f;
    static float yRotation = 0f;

    static void cameraMovment()
    {
        // Erstellt leeren Array
        double[] xPos = new double[1];
        double[] yPos = new double[1];

        // Holt sich Position
        GLFW.glfwGetCursorPos(Main.Window_Main, yPos, xPos);

        // MausMovment wird berechnet
        double mouseMovementX = xPos[0] - lastMouseX;
        double mouseMovementY = yPos[0] - lastMouseY;

        // Rotation wird addiert
        xRotation += (float) mouseMovementX * 0.2f;
        yRotation += (float) mouseMovementY * 0.2f;

        // Werte werden gesetzt
        lastMouseX = xPos[0];
        lastMouseY = yPos[0];
    }

    static void movment()
    {
        cameraMovment();
        // Links
        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS)
        {
            playerX += 0.01f;
        }
        // Rechts
        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS)
        {
            playerX -= 0.01f;
        }

        // Vorwerts
        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS)
        {
            playerZ += 0.01f;
        }

        // Rückwerts
        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS)
        {
            playerZ -= 0.01f;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS)
        {
            playerY -= 0.02f;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS)
        {
            playerY += 0.02f;
        }
    }
}
