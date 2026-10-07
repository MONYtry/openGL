package project.helper;

import org.lwjgl.opengl.GL11;
import project.settings.renderingSettings;

public class drawObjects {

    // Hilfs-Methode für Linien
    static void draw2DLine(float x_start, float y_start, float x_end, float y_end)
    {
        GL11.glVertex2f(x_start, y_start);
        GL11.glVertex2f(x_end, y_end);
    }

    static void drawCube(float i)
    {
        GL11.glColor3f(i, 0f, i);

        drawObjects.draw2DLine(-i,  i,  i,  i);
        drawObjects.draw2DLine( i,  i,  i, -i);
        drawObjects.draw2DLine( i, -i, -i, -i);
        drawObjects.draw2DLine(-i, -i, -i,  i);
    }

    public static void drawCubeSmaller()
    {
        renderingSettings.applySettings();

        for (float i = 0.1f; i <= 0.5f; i += 0.1f) {
            GL11.glBegin(GL11.GL_LINES);
            drawObjects.drawCube(i);
            GL11.glEnd();
        }
    }

    public static void drawTriangle(float i)
    {
        GL11.glBegin(GL11.GL_LINES);

        GL11.glColor3f(1f, 0f, 1f);

        draw2DLine(-i, -i, 0f, i);  // links
        draw2DLine(0f, i, i, -i);   // rechts
        draw2DLine(i, -i, -i, -i); // unten

        GL11.glEnd();
    }
    public static void draw3DTriangle(float s)
    {
        GL11.glBegin(GL11.GL_QUADS);

        // VORNE
        GL11.glVertex3f(-s, -s,  s);
        GL11.glVertex3f( s, -s,  s);
        GL11.glVertex3f( s,  s,  s);
        GL11.glVertex3f(-s,  s,  s);
    }
    public static void drawTrianglesSmaller()
    {
        renderingSettings.applySettings();
        for (float i = 0.1f; i <= 0.5f; i += 0.1f) {
            GL11.glBegin(GL11.GL_LINES);
            drawObjects.drawTriangle(i);
            GL11.glEnd();
        }
    }

    public static void drawCube3D(float s)
    {
        GL11.glColor3f(1f, 1f, 1f);

        GL11.glBegin(GL11.GL_QUADS);

        // VORNE
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex3f(-s, -s,  s);

        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex3f( s, -s,  s);

        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex3f( s,  s,  s);

        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex3f(-s,  s,  s);


        // HINTEN
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex3f( s, -s, -s);

        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex3f(-s, -s, -s);

        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex3f(-s,  s, -s);

        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex3f( s,  s, -s);


        // LINKS
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex3f(-s, -s, -s);

        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex3f(-s, -s,  s);

        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex3f(-s,  s,  s);

        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex3f(-s,  s, -s);


        // RECHTS
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex3f( s, -s,  s);

        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex3f( s, -s, -s);

        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex3f( s,  s, -s);

        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex3f( s,  s,  s);


        // OBEN
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex3f(-s,  s, -s);

        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex3f( s,  s, -s);

        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex3f( s,  s,  s);

        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex3f(-s,  s,  s);


        // UNTEN
        GL11.glTexCoord2f(0f, 0f);
        GL11.glVertex3f(-s, -s,  s);

        GL11.glTexCoord2f(1f, 0f);
        GL11.glVertex3f( s, -s,  s);

        GL11.glTexCoord2f(1f, 1f);
        GL11.glVertex3f( s, -s, -s);

        GL11.glTexCoord2f(0f, 1f);
        GL11.glVertex3f(-s, -s, -s);

        GL11.glEnd();
    }

    static void draw3DLine(
            float x1, float y1, float z1,
            float x2, float y2, float z2)
    {
        GL11.glVertex3f(x1, y1, z1);
        GL11.glVertex3f(x2, y2, z2);
    }
}
