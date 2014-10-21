/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.aero.osrsmap;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Luke
 */
public class MapData {
    
    public BufferedImage base;
    public BufferedImage thumb;
    
    //208 176
    public MapData(BufferedImage imageBase){
        this.base = imageBase;
        thumb = new BufferedImage(208,176,BufferedImage.TYPE_INT_RGB);
        thumb.createGraphics().drawImage(base.getScaledInstance(208, 176, Image.SCALE_AREA_AVERAGING),0,0,null);
    }
    
}
