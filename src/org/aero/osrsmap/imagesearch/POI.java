/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.aero.osrsmap.imagesearch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Luke
 */
public class POI {
    
    public ArrayList<Pixel> compares = new ArrayList<Pixel>();
    
    BufferedImage image;
    
    public String name;
    
    public POI(BufferedImage image, String name){
        this.image = image;
        this.name = name;
    }

    void generateComparator() {
        int avoid = image.getRGB(0,0);
        int rgb = 0;
        for(int x = 0; x < 15; x++){
            for(int y = 0; y < 15; y++){
                if(!POIDB.pixmatch[x][y] && (rgb = image.getRGB(x, y)) != avoid){
                    compares.add(new Pixel(x, y, rgb));
                }
            }
        }
    }
}
