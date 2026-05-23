package Processamento;

import Telas.Tela_Espaco_cor;
import java.awt.image.BufferedImage;
import Telas.Tela_Gamma_YIQ;
import Telas.Tela_Gamma_Cinza;

/**
 * @author 
 */
public class Processamento_Imagem_ {
        public static BufferedImage CriaCopia(BufferedImage Imagem){
            BufferedImage Copia = new BufferedImage(Imagem.getWidth(),Imagem.getHeight(),Imagem.getType());
            for(int x=0;x<Imagem.getWidth();x++){
                for(int y=0;y<Imagem.getHeight();y++){
                    Copia.setRGB(x,y,Imagem.getRGB(x,y));
                }
            }
            return Copia;
        }
        
        public static BufferedImage YCbCr(BufferedImage Imagem,Tela_Espaco_cor Tela){
            
            // Aplicacao do algoritmo.
            return ColorSpace_YCbCr.Detection(Imagem, Tela);
	}
        
        public static BufferedImage XYZ(BufferedImage Imagem,Tela_Espaco_cor Tela){
            
            // Aplicacao do algoritmo.
            return ColorSpace_XYZ.Detection(Imagem, Tela);
	}
        
	
        /*  Metodo para aplicar uma
            segmentacao em imagens. */
	public static BufferedImage Segment_(BufferedImage Imagem, int Metodo) {
            
            // Aplicacao do algoritmo.
            switch(Metodo){
                
                case 1:  return Segmentacao_.Otsu_Binarization_(Imagem);
                case 2:  return Segmentacao_.Fuzzy_Huang_Binarization_(Imagem);
                default: return Imagem;
            }
	}
        
        public static BufferedImage Gamma_YIQ(BufferedImage Imagem,Tela_Gamma_YIQ Tela){
            
            // Aplicacao do algoritmo.
            return GammaCorrectionYIQ.applyGamma(Imagem, Tela);
	}
        
        public static BufferedImage Gamma_Cinza(BufferedImage Imagem,Tela_Gamma_Cinza Tela){
            
            // Aplicacao do algoritmo.
            return GammaCorrectionCinza.applyGamma(Imagem, Tela);
	}
}
