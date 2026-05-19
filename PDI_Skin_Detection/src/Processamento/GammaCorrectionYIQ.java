/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processamento;
import Telas.Tela_Espaco_cor;
import java.awt.Color;
import java.awt.image.BufferedImage;


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
    
    public static BufferedImage applyGamma(BufferedImage Imagem, Tela_Espaco_cor Tela) {
        int Gamma_Value  = (int) Tela.Gamma_Value.getValue();
        //int width = Imagem.getWidth();
        //int height = Imagem.getHeight();
        //BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < Imagem.getWidth(); i++) {
            for (int j = 0; j < Imagem.getHeight(); j++) {
                /*int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;*/
                
                Color C = new Color(Imagem.getRGB(i, j));
                double r = Double.valueOf(C.getRed());
                double g = Double.valueOf(C.getGreen());
                double b = Double.valueOf(C.getBlue());

                // r = (int) (255 * Math.pow(r / 255.0, gamma));
                // g = (int) (255 * Math.pow(g / 255.0, gamma));
                // b = (int) (255 * Math.pow(b / 255.0, gamma));

                int rn = (int) Math.round(255 * Math.pow((r / 255.0), Gamma_Value));
                int gn = (int) Math.round(255 * Math.pow((g / 255.0), Gamma_Value));
                int bn = (int) Math.round(255 * Math.pow((b / 255.0), Gamma_Value));

                rn = Math.min(255, Math.max(0, rn));
                gn = Math.min(255, Math.max(0, gn));
                bn = Math.min(255, Math.max(0, bn));

                int newPixel = (rn << 16) | (gn << 8) | bn;
                Imagem.setRGB(i, j, newPixel);
            }
        }
        return Imagem;
    }
//    public static BufferedImage applyGamma(BufferedImage Imagem, double gamma) {;
//        int width = Imagem.getWidth();
//        int height = Imagem.getHeight();
//        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//        for (int i = 0; i < Imagem.getWidth(); i++) {
//            for (int j = 0; j < Imagem.getHeight(); j++) {
//                /*int rgb = image.getRGB(x, y);
//                int r = (rgb >> 16) & 0xFF;
//                int g = (rgb >> 8) & 0xFF;
//                int b = rgb & 0xFF;*/
//                
//                Color C = new Color(Imagem.getRGB(i, j));
//                double r = Double.valueOf(C.getRed());
//                double g = Double.valueOf(C.getGreen());
//                double b = Double.valueOf(C.getBlue());
//
//                // r = (int) (255 * Math.pow(r / 255.0, gamma));
//                // g = (int) (255 * Math.pow(g / 255.0, gamma));
//                // b = (int) (255 * Math.pow(b / 255.0, gamma));
//
//                int rn = (int) Math.round(255 * Math.pow((r / 255.0), gamma));
//                int gn = (int) Math.round(255 * Math.pow((g / 255.0), gamma));
//                int bn = (int) Math.round(255 * Math.pow((b / 255.0), gamma));
//
//                rn = Math.min(255, Math.max(0, rn));
//                gn = Math.min(255, Math.max(0, gn));
//                bn = Math.min(255, Math.max(0, bn));
//
//                int newPixel = (rn << 16) | (gn << 8) | bn;
//                output.setRGB(i, j, newPixel);
//            }
//        }
//        return output;
//    }


    /*public static BufferedImage Detection(BufferedImage Imagem,Tela_Espaco_cor Tela) {
    
        int Ymin  = (int) Tela.Ymin.getValue();
        int Ymax  = (int) Tela.Ymax.getValue();
        int Cbmin = (int) Tela.Cbmin.getValue();
        int Cbmax = (int) Tela.Cbmax.getValue();
        int Crmin = (int) Tela.Crmin.getValue();
        int Crmax = (int) Tela.Crmax.getValue();*/
        

        /*  Loop para percorrer os pixels da imagem (Coluna x Linha), e
            calcular os indices maximo e minimo de Bluness na imagem.   */
        /*for(int i = 0; i < Imagem.getWidth(); i++) {
            for(int j = 0; j < Imagem.getHeight(); j++) {

                // Niveis de cor de cada pixel.
                Color C = new Color(Imagem.getRGB(i, j));
                double R = Double.valueOf(C.getRed());
                double G = Double.valueOf(C.getGreen());
                double B = Double.valueOf(C.getBlue());

                double Y;
                double Cb;
                double Cr;

                Y  =  0.257*R + 0.504*G + 0.098*B + 16;
                Cb = -0.148*R - 0.291*G + 0.439*B + 128;
                Cr =  0.439*R - 0.368*G - 0.071*B + 128;
                
                Color Novo = new Color(0, 0, 0);
                if(Tela.Ymin.isEnabled() && !(Ymin<Y && Y<Ymax)){
                    Imagem.setRGB(i, j, Novo.getRGB());
                }      
                if(Tela.Cbmin.isEnabled() && !(Cbmin<Cb && Cb<Cbmax)){
                    Imagem.setRGB(i, j, Novo.getRGB());
                }    
                if(Tela.Crmin.isEnabled() && !(Crmin<Cr && Cr<Crmax)){
                    Imagem.setRGB(i, j, Novo.getRGB());
                }    
            }
        }

        return Imagem;
    }*/
}