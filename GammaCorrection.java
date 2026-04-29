import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class GammaCorrection {
    public static BufferedImage applyGamma(BufferedImage image, double gamma) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // r = (int) (255 * Math.pow(r / 255.0, gamma));
                // g = (int) (255 * Math.pow(g / 255.0, gamma));
                // b = (int) (255 * Math.pow(b / 255.0, gamma));

                int rn = (int) Math.round(255 * Math.pow((r / 255.0), gamma));
                int gn = (int) Math.round(255 * Math.pow((g / 255.0), gamma));
                int bn = (int) Math.round(255 * Math.pow((b / 255.0), gamma));

                rn = Math.min(255, Math.max(0, rn));
                gn = Math.min(255, Math.max(0, gn));
                bn = Math.min(255, Math.max(0, bn));

                int newPixel = (rn << 16) | (gn << 8) | bn;
                output.setRGB(x, y, newPixel);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        try {
            File cwd = new File(".");
            System.out.println("Current working directory;");
            System.out.println(cwd.getAbsolutePath());
            System.out.println();

            String filename = "rosto.jpg"; //FIXED - deve estar dentro

            Scanner sc = new Scanner(System.in);
            System.out.print("Coloque nome da imagem ou url: ");
            filename = sc.nextLine().trim();
            sc.close();

            File imgFile = new File(filename);
            System.out.println("Imagem: " + imgFile.getAbsolutePath());

            if(!imgFile.exists()) {
                System.err.println("Erro: Arquivo não encontrado.");
                System.err.println(cwd.getAbsolutePath());
                return;
            }

            BufferedImage input = ImageIO.read(imgFile);
            if(input == null) {
                System.err.println("Erro: Não foi possível ler a imagem. Verifique o formato do arquivo.");
                return;
            }

            double[] gammas = {0.4, 1.0, 2.5};
            for (double gamma: gammas) {
                BufferedImage out = applyGamma(input, gamma);
                String outName = "output_gamma_" + gamma + ".jpg";
                ImageIO.write(out, "jpg", new File(outName));
                System.out.println("Salva: " + new File(outName).getAbsolutePath());
            }

            System.out.println("Processamento concluído.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

