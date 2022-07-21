import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create(InputStream inputStream, String nameMovie) throws Exception {
        // Forma de leitura de dados
        // InputStream inputStream = new FileInputStream(new File("img/movieFull.jpg"));
        // InputStream inputStream = new URL(
        // "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
        // .openStream();
        // BufferedImage imageOrigen = ImageIO.read(new File("img/movieFull.jpg"));

        BufferedImage imageOrigen = ImageIO.read(inputStream);

        int width = imageOrigen.getWidth();
        int height = imageOrigen.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOrigen, 0, 0, null);

        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        graphics.drawString("TESTE", (width / 2) - (3 * 62), newHeight - 100);

        // ImageIO.write(newImage, "png", new File("imgOut/movie1_sticker.png"));
        ImageIO.write(newImage, "png", new File(nameMovie));

    }

}
