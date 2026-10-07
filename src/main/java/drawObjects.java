import org.lwjgl.opengl.GL11;

public class drawCube {

    // Hilfs-Methode für Linien
    static void drawLine(float x_start, float y_start, float x_end, float y_end)
    {
        GL11.glVertex2f(x_start, y_start);
        GL11.glVertex2f(x_end, y_end);
    }
}
