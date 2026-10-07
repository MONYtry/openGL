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
    static boolean isSprinting = false;
    static void cameraMovment()
    {
        // Erstellt leeren Array
        double[] xPos = new double[1];
        double[] yPos = new double[1];

        // Holt sich Position
        GLFW.glfwGetCursorPos(Main.Window_Main, xPos, yPos);

        // MausMovment wird berechnet
        double mouseMovementX = xPos[0] - lastMouseX;
        double mouseMovementY = yPos[0] - lastMouseY;

        // Rotation wird addiert
        yRotation += (float) mouseMovementX * 0.2f;
        xRotation += (float) mouseMovementY * 0.2f;
        xRotation = Math.clamp(xRotation, -90,90);

        // Werte werden gesetzt
        lastMouseX = xPos[0];
        lastMouseY = yPos[0];
    }

    static void movment()
    {
        cameraMovment();

        // Winkel wird berechnet
        float yaw = (float) Math.toRadians(yRotation);
        // Geschwindigkeit
        float speed = 0.01f;

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS)
        {
            playerX += Math.sin(yaw) * speed;
            playerZ -= Math.cos(yaw) * speed;
        }
        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS)
        {
            playerX -= Math.sin(yaw) * speed;
            playerZ += Math.cos(yaw) * speed;
        }
        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS)
        {
            playerX -= Math.cos(yaw) * speed;
            playerZ -= Math.sin(yaw) * speed;
        }
        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS)
        {
            playerX += Math.cos(yaw) * speed;
            playerZ += Math.sin(yaw) * speed;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS)
        {
            playerY += speed;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS)
        {
            if (isSprinting)
            {
                speed = 0.03f;
            }
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS)
        {
            playerY -= speed;
        }
    }
}
