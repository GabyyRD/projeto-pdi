/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processamento;
import Telas.Tela_Espaco_cor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import Telas.Tela_Gamma_Cinza;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;


/**
 *
 * @author 
 */
public class GammaCorrectionCinza {   
    /*  Algoritmo de Transformação Gamma, desenvolvido
        por Gabrielly Dionisio e Felipe Morais - 2026/1.
    */
    
    public static BufferedImage applyGamma(BufferedImage Imagem, Tela_Gamma_Cinza Tela) {
        
        // Pega os valores dos spinners
        double gamma = ((Number) Tela.spinnerGamma.getValue()).doubleValue();
        double c = ((Number) Tela.spinnerC.getValue()).doubleValue();

        for (int i = 0; i < Imagem.getWidth(); i++) {
            for (int j = 0; j < Imagem.getHeight(); j++) {
                
                // Preserva o alpha, para imagens .png
                Color C = new Color(Imagem.getRGB(i, j), true);
                int a = C.getAlpha(); 
                
                // Pegando o tom de apenas um canal, pois em níveis de cinza R = G = B.
                double tom = Double.valueOf(C.getRed());

                // Aplicando a fórmula do Gamma para o tom
                int novoTom = (int) Math.round(c * 255 * Math.pow((tom / 255.0), gamma));

                // Garantia de que o valor vai estar entre 0 a 255.
                novoTom = Math.min(255, Math.max(0, novoTom));

                // Remontagem dos pixels da imagem
                int newPixel = (a << 24) | (novoTom << 16) | (novoTom << 8) | novoTom;
                Imagem.setRGB(i, j, newPixel);
            }
        }
        
        return Imagem;
    }
}