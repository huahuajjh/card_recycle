package com.tqmars.cardrecycle.application.User.dto;

import java.awt.image.BufferedImage;

/**
 * Created by jjh on 1/14/17.
 */
public class VCodeOutput {
    private String vCode;
    private BufferedImage img;

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
}
