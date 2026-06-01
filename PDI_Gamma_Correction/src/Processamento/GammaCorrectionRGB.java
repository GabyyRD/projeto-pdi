/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processamento;
import Telas.Tela_Espaco_cor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import Telas.Tela_Gamma_RGB;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;


/**
 *
 * @author 
 */
public class GammaCorrectionRGB {   
    /*  Algoritmo de Transformação Gamma, desenvolvido
        por Gabrielly Dionisio e Felipe Morais - 2026/1.
    */
    
    public static BufferedImage applyGamma(BufferedImage Imagem, Tela_Gamma_RGB Tela) {

        double gamma = ((Number) Tela.spinnerGamma.getValue()).doubleValue();
        double c = ((Number) Tela.spinnerC.getValue()).doubleValue();

        for (int i = 0; i < Imagem.getWidth(); i++) {
            for (int j = 0; j < Imagem.getHeight(); j++) {
                
                Color C = new Color(Imagem.getRGB(i, j), true);
                int a = C.getAlpha(); // necessário para tratarmos o alpha em imagens .png
                double r = Double.valueOf(C.getRed());
                double g = Double.valueOf(C.getGreen());
                double b = Double.valueOf(C.getBlue());
                
                int rn = (int) Math.round(c *255 * Math.pow((r / 255.0), gamma));
                int gn = (int) Math.round(c * 255 * Math.pow((g / 255.0), gamma));
                int bn = (int) Math.round(c * 255 * Math.pow((b / 255.0), gamma));

                rn = Math.min(255, Math.max(0, rn));
                gn = Math.min(255, Math.max(0, gn));
                bn = Math.min(255, Math.max(0, bn));

                int newPixel = (a << 24) | (rn << 16) | (gn << 8) | bn;
                Imagem.setRGB(i, j, newPixel);
            }
        }
        return Imagem;
    }
}