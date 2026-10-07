import org.lwjgl.opengl.GL11;

public class drawObjects {

    // Hilfs-Methode für Linien
    static void drawLine(float x_start, float y_start, float x_end, float y_end)
    {

        GL11.glVertex2f(x_start, y_start);
        GL11.glVertex2f(x_end, y_end);


    }


    static void drawCube(float i)
    {
        GL11.glColor3f(i, 0f, i);

        drawObjects.drawLine(-i,  i,  i,  i);
        drawObjects.drawLine( i,  i,  i, -i);
        drawObjects.drawLine( i, -i, -i, -i);
        drawObjects.drawLine(-i, -i, -i,  i);
    }

    public static void drawCubeSmaller()
    {
        settings.applySettings();

        for (float i = 0.1f; i <= 0.5f; i += 0.1f) {
            GL11.glBegin(GL11.GL_LINES);
            drawObjects.drawCube(i);
            GL11.glEnd();
        }
    }

    static void drawTriangle(float i)
    {
        GL11.glBegin(GL11.GL_LINES);

        GL11.glColor3f(1f, 0f, 1f);

        drawLine(-i, -i, 0f, i);  // links
        drawLine(0f, i, i, -i);   // rechts
        drawLine(i, -i, -i, -i); // unten

        GL11.glEnd();
    }

    static void drawTrianglesSmaller()
    {
        settings.applySettings();
        for (float i = 0.1f; i <= 0.5f; i += 0.1f) {
            GL11.glBegin(GL11.GL_LINES);
            drawObjects.drawTriangle(i);
            GL11.glEnd();
        }
    }

    static void drawCube3D(float s)
    {
        GL11.glBegin(GL11.GL_LINES);

        GL11.glColor3f(1f, 0f, 1f);

        // Vorderseite
        draw3DLine(-s, -s,  s,  s, -s,  s);
        draw3DLine( s, -s,  s,  s,  s,  s);
        draw3DLine( s,  s,  s, -s,  s,  s);
        draw3DLine(-s,  s,  s, -s, -s,  s);

        // Rückseite
        draw3DLine(-s, -s, -s,  s, -s, -s);
        draw3DLine( s, -s, -s,  s,  s, -s);
        draw3DLine( s,  s, -s, -s,  s, -s);
        draw3DLine(-s,  s, -s, -s, -s, -s);

        // Verbindung vorne zu hinten
        draw3DLine(-s, -s,  s, -s, -s, -s);
        draw3DLine( s, -s,  s,  s, -s, -s);
        draw3DLine( s,  s,  s,  s,  s, -s);
        draw3DLine(-s,  s,  s, -s,  s, -s);

        GL11.glEnd();
    }

    static void draw3DLine(float x1, float x2, float y1, float y2, float z1, float z2)
    {
        GL11.glVertex3d(x1,y1,z1);
        GL11.glVertex3d(x2,y2,z2);
    }
}
