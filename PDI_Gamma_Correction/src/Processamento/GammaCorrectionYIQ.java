///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Processamento;
//import Telas.Tela_Espaco_cor;
//import java.awt.Color;
//import java.awt.image.BufferedImage;
//import Telas.Tela_Gamma_YIQ;
//
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.util.Scanner;
//import javax.imageio.ImageIO;
//
//
///**
// *
// * @author 
// */
//public class GammaCorrectionYIQ {   
//    /*  Algoritmo de Transformação Gamma, desenvolvido
//        por Gabrielly Dionisio e Felipe Morais - 2026/1.
//    */
//    
//    public static BufferedImage applyGamma(BufferedImage Imagem, Tela_Gamma_YIQ Tela) {
//
//        double gamma = ((Number) Tela.spinnerGamma.getValue()).doubleValue();
//        double c = ((Number) Tela.spinnerC.getValue()).doubleValue();
//
//        for (int i = 0; i < Imagem.getWidth(); i++) {
//            for (int j = 0; j < Imagem.getHeight(); j++) {
//                
//                Color C = new Color(Imagem.getRGB(i, j), true);
//                int a = C.getAlpha(); // necessário para tratarmos o alpha em imagens .png
//                double r = Double.valueOf(C.getRed());
//                double g = Double.valueOf(C.getGreen());
//                double b = Double.valueOf(C.getBlue());
//
//                int rn = (int) Math.round(c *255 * Math.pow((r / 255.0), gamma));
//                int gn = (int) Math.round(c * 255 * Math.pow((g / 255.0), gamma));
//                int bn = (int) Math.round(c * 255 * Math.pow((b / 255.0), gamma));
//
//                rn = Math.min(255, Math.max(0, rn));
//                gn = Math.min(255, Math.max(0, gn));
//                bn = Math.min(255, Math.max(0, bn));
//
//                int newPixel = (a << 24) | (rn << 16) | (gn << 8) | bn;
//                Imagem.setRGB(i, j, newPixel);
//            }
//        }
//        return Imagem;
//    }
//}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processamento;
import Telas.Tela_Espaco_cor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import Telas.Tela_Gamma_YIQ;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;


/**
 *
 * @author 
 */
public class GammaCorrectionYIQ {   
    /*  Algoritmo de Transformação Gamma, desenvolvido
        por Gabrielly Dionisio e Felipe Morais - 2026/1.
    */
    
    public static BufferedImage applyGamma(BufferedImage Imagem, Tela_Gamma_YIQ Tela) {

        double gamma = ((Number) Tela.spinnerGamma.getValue()).doubleValue();
        double c = ((Number) Tela.spinnerC.getValue()).doubleValue();

        for (int i = 0; i < Imagem.getWidth(); i++) {
            for (int j = 0; j < Imagem.getHeight(); j++) {
                
                //Color C = new Color(Imagem.getRGB(i, j), true);
                //int a = C.getAlpha(); // necessário para tratarmos o alpha em imagens .png
                //double r = Double.valueOf(C.getRed());
                //double g = Double.valueOf(C.getGreen());
                //double b = Double.valueOf(C.getBlue());
                
                
                Color rgb = new Color(Imagem.getRGB(i, j));
                int a = rgb.getAlpha();

                // RGB -> YIQ
                double[] yiq = calculaYIQ(rgb);

                double Y = yiq[0];
                double I = yiq[1];
                double Q = yiq[2];

                // aplica gama SOMENTE no Y
                Y = 255 * Math.pow(Y / 255.0, gamma);

                // YIQ -> RGB
                int novoRGB = calculaRGB(a, Y, I, Q);

                Imagem.setRGB(i, j, novoRGB);
                
                //int yn = (int) Math.round(c * 255 * Math.pow((y / 255.0), gamma));
                //int rn = (int) Math.round(c *255 * Math.pow((r / 255.0), gamma));
                //int gn = (int) Math.round(c * 255 * Math.pow((g / 255.0), gamma));
                //int bn = (int) Math.round(c * 255 * Math.pow((b / 255.0), gamma));

                //rn = Math.min(255, Math.max(0, rn));
                //gn = Math.min(255, Math.max(0, gn));
                //bn = Math.min(255, Math.max(0, bn));

                //int newPixel = (a << 24) | (rn << 16) | (gn << 8) | bn;
                //Imagem.setRGB(i, j, newPixel);
            }
        }
        return Imagem;
    }
    
    
    public static double[] calculaYIQ(Color rgb) {

        final double[][] RGBtoYIQ = {
            {0.299,  0.587,  0.114},
            {0.596, -0.275, -0.321},
            {0.212, -0.523,  0.311}
        };
        
        double r = Double.valueOf(rgb.getRed());
        double g = Double.valueOf(rgb.getGreen());
        double b = Double.valueOf(rgb.getBlue());
        
        double y = RGBtoYIQ[0][0] * r +
                   RGBtoYIQ[0][1] * g +
                   RGBtoYIQ[0][2] * b;

        double i = RGBtoYIQ[1][0] * r +
                   RGBtoYIQ[1][1] * g +
                   RGBtoYIQ[1][2] * b;

        double q = RGBtoYIQ[2][0] * r +
                   RGBtoYIQ[2][1] * g +
                   RGBtoYIQ[2][2] * b;

        return new double[]{y, i, q};
    }
    
    public static int calculaRGB(int a, double y, double i, double q) {

        final double[][] YIQtoRGB = {
            {1.000,  0.956,  0.621},
            {1.000, -0.272, -0.647},
            {1.000, -1.105,  1.702}
        };

        double r = YIQtoRGB[0][0] * y +
                   YIQtoRGB[0][1] * i +
                   YIQtoRGB[0][2] * q;

        double g = YIQtoRGB[1][0] * y +
                   YIQtoRGB[1][1] * i +
                   YIQtoRGB[1][2] * q;

        double b = YIQtoRGB[2][0] * y +
                   YIQtoRGB[2][1] * i +
                   YIQtoRGB[2][2] * q;

        // Limita entre 0 e 255
        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));

        int R = (int) r;
        int G = (int) g;
        int B = (int) b;

        return (a << 24) | (R << 16) | (G << 8) | B;
    }
}