package project.helper;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBEasyFont;

import java.nio.ByteBuffer;

public class drawText {


    public static void drawText(float x, float y,String input, float scale,float r, float g, float b)
    {
        // Speicher für den Text
        ByteBuffer buffer = BufferUtils.createByteBuffer(10000);

        // Bildschirm-Koordinaten einstellen
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 600, 0, -1, 1);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glScalef(scale,scale,1f);

        // Textfarbe
        GL11.glColor3f(r, g, b);

        // Text erstellen
        int quads = STBEasyFont.stb_easy_font_print(x, y, input, null, buffer);

        // Text zeichnen
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, buffer);
        GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);

        // Fertig mit dem Text
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }
}
