/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.aero.osrsmap.imagesearch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.aero.osrsmap.MapData;
import org.aero.osrsmap.MapRenderer;

/**
 *
 * @author Luke
 */
public class POIFinder {
    
    public static HashMap<Point, Integer> candidates = new HashMap<Point, Integer>();
    // another example of horribly hacky code // 
    public static void findPOIs(MapData md){
        BufferedImage ref = md.base;
        MapRenderer.instance.debugi = new BufferedImage(ref.getWidth(), ref.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = MapRenderer.instance.debugi.createGraphics();
//        MapRenderer.instance.debug = true;
        g.drawImage(ref,0,0,null);
        g.setColor(new Color(255,0,255,128));
//        int w = ref.getWidth();
//        int h = ref.getHeight();
        int h = 4786;
        int w = 5510;
        System.out.println();
        int poirefcount = POIDB.universalPixelMatches.size();
        
        
        ArrayList<Point> invalidated = new ArrayList<Point>();
        if(false){ // should be if(generateCandidates) but too lazy
            
            for(int y = 523; y < h; y++){
                for(int x = 274; x < w; x++){
//                System.out.println("Row " + x + "/" + w);
                    int totesmatch = 0;
                    for(Pixel p : POIDB.universalPixelMatches){
                        if(p.rgb == ref.getRGB(x+p.x, y+p.y))totesmatch++;
                    }
                    if(totesmatch>0){
                        g.setColor(new Color(255,0,255,1+(totesmatch*255/poirefcount)/8));
                        g.drawRect(x-2,y-2,18,18);
                    }
                    if(totesmatch>poirefcount/2){
                        Point abo = new Point(x, y-1);
                        Point blo = new Point(x, y+1);
                        Point ale = new Point(x-1, y);
                        Point ari = new Point(x+1, y);
                        boolean valid = true;
                        if(candidates.containsKey(abo)){
                            valid = candidates.get(abo) < totesmatch;
                            if(valid) invalidated.add(abo);
                        }
                        if(candidates.containsKey(blo)){
                            valid = candidates.get(blo) < totesmatch;
                            if(valid) invalidated.add(blo);
                        }
                        if(candidates.containsKey(ale)){
                            valid = candidates.get(ale) < totesmatch;
                            if(valid) invalidated.add(ale);
                        }
                        if(candidates.containsKey(ari)){
                            valid = candidates.get(ari) < totesmatch;
                            if(valid) invalidated.add(ari);
                        }
                        if(valid){
                            candidates.put(new Point(x, y), totesmatch);
                            g.setColor(Color.RED);
                            g.drawRect(x-2,y-2,18,18);
                            MapRenderer.instance.offsetx = x-200;
                            MapRenderer.instance.offsety = y-150;
                            MapRenderer.instance.repaint();
                        }
                    }
                }
            }
        }else{
            try {
                DataInputStream din = new DataInputStream(POIFinder.class.getResourceAsStream("/candidates.bin"));
                while(din.available()>3){
                    candidates.put(new Point(din.readShort(), din.readShort()), Integer.MAX_VALUE);
                }
            } catch (IOException ex) {
                Logger.getLogger(POIFinder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        HashMap<Point, Integer> finalizedCandidates = new HashMap<Point, Integer>();

        g.setColor(Color.GREEN);
        g.setFont(MapRenderer.instance.fon);
        boolean[][] hax = new boolean[1024][1024];
        for(int x=0;x<1024;x++)for(int y=0;y<1024;y++)hax[x][y]=false;
        for(Point p : candidates.keySet()){
            if(!invalidated.contains(p)){
                int x = (int)p.getX();
                int y = (int)p.getY();
                g.drawRect(x-2, y-2, 18, 18);
                POI nearest = null;
                int nearestmatching = 0;
                for(String s : POIDB.POIs.keySet()){
                    int matching = 0;
                    POI t = POIDB.POIs.get(s);
                    for(Pixel pi : t.compares){
                        if(pi.rgb == ref.getRGB(x+pi.x, y+pi.y))matching ++;
                    }
                    if(matching > nearestmatching){
                        nearestmatching = matching;
                        nearest = t;
                    }
                }
                if(nearest != null){
//                    g.drawLine(x+8,y-23, x+8, y+8);
//                    g.drawImage(nearest.image, x, y-28,null);

                    int rx = x/42;
                    int ry = y/10;
                    ry--;
                    rx++;
                    int rys = ry;
                    int rxs = rx;
                    boolean swi =  false;
                    while(hax[rx][ry]){
                        if((rx-rxs > 3))swi=true;
                        if(rys-ry < 4||(rx-rxs > 3))if(swi)ry++;else ry--;
                        else rx++;
                    }
                    hax[rx][ry] = true;
                    g.setColor(Color.BLACK);
                    g.drawString(nearest.name, rx*42+19-32, ry*10+9);
                    g.drawString(nearest.name, rx*42+17-32, ry*10+7);
                    g.drawString(nearest.name, rx*42+19-32, ry*10+7);
                    g.drawString(nearest.name, rx*42+17-32, ry*10+9);
                    g.drawString(nearest.name, rx*42+19-32, ry*10+8);
                    g.drawString(nearest.name, rx*42+17-32, ry*10+8);
                    g.drawString(nearest.name, rx*42+18-32, ry*10+9);
                    g.drawString(nearest.name, rx*42+18-32, ry*10+7);
                    g.drawLine(rx*42+18-32+1,ry*10+4,x+5,y+4);
                    g.drawLine(rx*42+18-32-1,ry*10+4,x+3,y+4);
                    g.setColor(Color.WHITE);
                    g.drawString(nearest.name, rx*42+18-32, ry*10+8);
                    g.drawLine(rx*42+18-32,ry*10+4,x+4,y+4);
//                    g.drawString(nearest.name, ((x/32)*42)+18, ((y/10)*10)+8);
                    MapRenderer.instance.offsetx = x-200;
                    MapRenderer.instance.offsety = y-150;
                    MapRenderer.instance.repaint();
                    finalizedCandidates.put(p, candidates.get(p));
                    POIDB.POIPoints.get(nearest.name).add(p);
                }
//                try {
//                    Thread.sleep(8);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(POIFinder.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        }
//        try {
//            DataOutputStream dout = new DataOutputStream(new FileOutputStream("D:\\out\\candidates.bin"));
//            for(Point p : finalizedCandidates.keySet()){
//                dout.writeShort((short)p.getX());
//                dout.writeShort((short)p.getY());
//            }
//            dout.flush();
//            dout.close();
//        } catch (Exception ex) {
//            Logger.getLogger(POIFinder.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println("Finalized POIList: ");
        for(String s : POIDB.POIPoints.keySet()){
            System.out.println("--" + s + ":");
            for(Point p : POIDB.POIPoints.get(s)){
                System.out.println("----" + p.getX() + ", " + p.getY());
            }
        }
//        try {
//            ImageIO.write(MapRenderer.instance.debugi, "PNG", new File("D:\\out\\debug.png"));
//        } catch (IOException ex) {
//            Logger.getLogger(POIFinder.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
