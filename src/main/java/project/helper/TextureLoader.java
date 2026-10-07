package project.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class TextureLoader
{
    public static int loadTexture(String path)
    {
        try
        {
            InputStream input = TextureLoader.class.getClassLoader().getResourceAsStream(path);

            // Falls Datei Pfad nicht gefunden wird <- Abbruch
            if (input == null)
            {
                throw new RuntimeException("Texture nicht gefunden: " + path);
            }

            BufferedImage image = ImageIO.read(input);
            // Holt sich große
            int width = image.getWidth();
            int height = image.getHeight();

            // Erstellt Pixel
            int[] pixels = new int[width * height];

            image.getRGB(0, 0, width, height, pixels, 0, width);

            ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
            // Holt sich die Pixel
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    int pixel = pixels[y * width + x];

                    buffer.put((byte) ((pixel >> 16) & 0xFF)); // R
                    buffer.put((byte) ((pixel >> 8) & 0xFF));  // G
                    buffer.put((byte) (pixel & 0xFF));         // B
                    buffer.put((byte) ((pixel >> 24) & 0xFF));// A
                }
            }

            buffer.flip();

            int textureID = GL11.glGenTextures();

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

            return textureID;
        }
        catch (IOException e)
        {
            throw new RuntimeException("Texture konnte nicht geladen werden!", e);
        }
    }
}