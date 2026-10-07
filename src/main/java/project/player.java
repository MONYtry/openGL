package project;

import org.lwjgl.glfw.GLFW;

public class player {
    // Koordinaten
    public static float playerX = 0f;
    public static float playerY = 0f;
    public static float playerZ = -5f;

    static float movementX;
    static float movementZ;

    static float velocityY = 0;
    static boolean isGrounded = true;

    static float gravity = 0.002f;
    static float jumpStrenght = 0.08f;

    // Sprint
    static boolean isSprinting = false;

    // Letzte Maus-Position
    static double lastMouseX = 0f;
    static double lastMouseY = 0f;

    // Aktuelle Rotation der Maus
    public static float xRotation = 0f;
    public static float yRotation = 0f;

    static void cameraMovment() {
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
        xRotation = Math.clamp(xRotation, -90, 90);

        // Werte werden gesetzt
        lastMouseX = xPos[0];
        lastMouseY = yPos[0];
    }


    static void movment() {
        // Gravitation
        velocityY -= gravity;
        playerY += velocityY;

        if (playerY < 0) {
            playerY = 0f;
            velocityY = 0f;
            isGrounded = true;
        }

        cameraMovment();

        float yaw = (float) Math.toRadians(yRotation);
        float speed = 0.01f;

        movementX = 0;
        movementZ = 0;

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            movementX += (float) Math.sin(yaw) * speed;
            movementZ -= (float) Math.cos(yaw) * speed;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            movementX -= (float) Math.sin(yaw) * speed;
            movementZ += (float) Math.cos(yaw) * speed;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            movementX -= (float) Math.cos(yaw) * speed;
            movementZ -= (float) Math.sin(yaw) * speed;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            movementX += (float) Math.cos(yaw) * speed;
            movementZ += (float) Math.sin(yaw) * speed;
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            if (isGrounded) {
                velocityY = jumpStrenght;
                isGrounded = false;
            }

        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
            if (isSprinting) {
                speed = 0.03f;
            }
        }

        if (GLFW.glfwGetKey(Main.Window_Main, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS) {
            playerY -= speed;
        }

        float newX = playerX + movementX;
        float newZ = playerZ + movementZ;

        if (!checkCollision(newX, playerY, newZ)) {
            playerX = newX;
            playerZ = newZ;
        }
    }


    public static boolean checkCollision(float newX, float newY, float newZ) {
        // Player-Settings
        float playerWidth = 0.6f;
        float playerHeight = 1.8f;

        // Geht durch die aktuelle Map
        for (int height = 0; height < 3; height++)
        {
            for (int length = 0; length < 3; length++)
            {
                for (int x = 0; x < 3; x++)
                {
                    // Erstellt Variablen
                    float blockX = x;
                    float blockY = height;
                    float blockZ = length;

                    // Wenn Condition stimmt wird der Boolean auf True gesetzt!
                    boolean collisionX = newX + playerWidth > blockX - 0.5f && newX - playerWidth < blockX + 0.5f;
                    boolean collisionY = newY < blockY + 1 && newY + playerHeight > blockY;
                    boolean collisionZ = newZ + playerWidth > blockZ - 0.5f && newZ - playerWidth < blockZ + 0.5f;

                    if (collisionZ && collisionX && collisionY)
                    {
                        return true;
                    }

                }
            }
        }
        return false;
    }
}
