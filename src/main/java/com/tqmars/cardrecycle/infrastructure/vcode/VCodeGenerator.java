package com.tqmars.cardrecycle.infrastructure.vcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by jjh on 1/14/17.
 */
public class VCodeGenerator {
    public static CodeObj getVCode(int width,int height) {
        String vCode = "";
        int length = 4;
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            vCode += rd.nextInt(10);
        }

        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);

        for(int i=0; i<50; i++){

            g.setColor(new Color(rd.nextInt(100)+155,rd.nextInt(100)+155,rd.nextInt(100)+155));

            g.drawLine(rd.nextInt(width), rd.nextInt(height),rd.nextInt(width), rd.nextInt(height));

        }

        g.setColor(Color.gray);
        g.drawRect(0,0,width-1,height-1);

        Font[] fonts = {new Font("隶书",Font.BOLD,18),new Font("楷体",Font.BOLD,18),new Font("宋体",Font.BOLD,18),new Font("幼圆",Font.BOLD,18)};

        for(int i=0; i<length; i++){

            g.setColor(new Color(rd.nextInt(150),rd.nextInt(150),rd.nextInt(150)));

            g.setFont(fonts[rd.nextInt(fonts.length)]);

            g.drawString(vCode.charAt(i)+"", width/vCode.length()*i+2, 18);

        }

        g.dispose();

        return new CodeObj(vCode,img);
    }

    public static class CodeObj{
        private String vCode;
        private BufferedImage img;

        public String getvCode() {
            return vCode;
        }

        public void setvCode(String vCode) {
            this.vCode = vCode;
        }

        public BufferedImage getImg() {
            return img;
        }

        public void setImg(BufferedImage img) {
            this.img = img;
        }

        public CodeObj(String vCode, BufferedImage img) {
            this.vCode = vCode;
            this.img = img;
        }
    }

}
