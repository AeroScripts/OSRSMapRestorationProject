/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.aero.osrsmap;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.aero.osrsmap.imagesearch.POIDB;
import org.aero.osrsmap.imagesearch.POIFinder;

/**
 *
 * @author Luke
 */
public class MapRenderer extends Component {
    
    public static int W = 800, H = 600, IW = -1, IH = -1;
    
    //Remember kids! Only you can prevent bad coding, an example of bad coding can bee seen below//
    public static MapData data = null;    
    public static MapRenderer instance = null;
    boolean loadingMap = false;
    boolean loaded = false;
    
    double scale = 2d;
    
    public int offsetx = 0;
    public int offsety = 0;
    public float xoffsetf = 0;
    public float yoffsetf = 0;
    public boolean debug = false;
    
    public BufferedImage debugi = null;
    
    public static String[] keys = new String[0];
    
    public static void loadMap() {
        keys = new String[POIDB.b64map.size()];
        int i = 0;
        for(String s : POIDB.b64map.keySet()){System.out.println(s);keys[i]=s;i++;}
//        Set<String> tset = POIDB.b64map.keySet();
//        for(int i = 0; i < keys.length; i++)keys[i] = tset.
//        instance.loadingMap = true;
        instance.repaint();
        try {
            data = new MapData(ImageIO.read(MapRenderer.class.getResourceAsStream("/osrs_world_map_august_2014.png")));
            IW = data.base.getWidth();
            IH = data.base.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(MapRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.loadingMap = false;
        instance.loaded = true;
        instance.repaint();
    }
    
    int lx = 0;
    int ly = 0;
            
    public MapRenderer(){
        instance = this;
        setSize(W, H);
        setPreferredSize(new Dimension(W, H));
        requestFocus();
        
        try{
            fon = Font.createFont(Font.TRUETYPE_FONT, MapRenderer.class.getResourceAsStream("/RuneScape-Chat-07.ttf")).deriveFont(15f);
            fonb = Font.createFont(Font.TRUETYPE_FONT, MapRenderer.class.getResourceAsStream("/RuneScape-Chat-Bold-07.ttf")).deriveFont(16f);
        }catch(Throwable t){}

        this.addComponentListener(new ComponentAdapter() {
        

            @Override
            public void componentResized(ComponentEvent e) {
                W = getWidth();
                H = getHeight();
                repaint();
            }
            
        });
        
        

        this.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
//                scale *= e.getUnitsToScroll()/16f;
                double lasthack = scale;
                scale -= ((((float)e.getUnitsToScroll()))/(16f/scale));
                
//                scale = Math.
//                xoffset += (lx-W/2)/(scale*8);
//                xoffset += (W/2)/scale;
//                yoffset += (H/2)/scale;
//                yoffset += (ly-W/2)/(scale*8);
//                if(scale*W>IW || scale*H>IH) scale = lasthack;
//                int maxx = (int)Math.ceil(scale * W);
//                int maxy = (int)Math.ceil(scale * H);
//                if(xoffset+maxx>IW)xoffset = 0;//MAKE THIS BETTER
//                if(yoffset+maxy>IH)yoffset = 0;//MAKE THIS BETTER
                System.out.println(e.getUnitsToScroll());
                repaint();
            }
            
        });
        
        this.addMouseMotionListener(new MouseMotionListener() {

            
            @Override
            public void mouseDragged(MouseEvent e) {
                xoffsetf -= (e.getX()-lx)/scale;
                yoffsetf -= (e.getY()-ly)/scale;
                offsetx = (int)xoffsetf;
                offsety = (int)yoffsetf;
                lx = e.getX();
                ly = e.getY();
//                int maxx = (int)Math.ceil(scale * W);
//                int maxy = (int)Math.ceil(scale * H);
//                if(xoffset+maxx>IW)xoffset = 0;//MAKE THIS BETTER
//                if(yoffset+maxy>IH)yoffset = 0;//MAKE THIS BETTER
                
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                lx = e.getX();
                ly = e.getY();
                
                /*move to onMousePress*/
                xoffsetf = offsetx;
                yoffsetf = offsety;
            }
        });
        
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println(e.getKeyCode());
                switch(e.getKeyCode()){
                    case 40: sel--;break;
                    case 38: sel++;break;
                    default: break;
                }
                if(sel<0)sel = keys.length-1;
                if(sel>=keys.length)sel = 0;
                if(sel<26)offset = 0;
                else offset = sel-25;
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        setFocusable(true);
        requestFocus();
        
    }
    
    public Font fon;
    public Font fonb;
    
    int offset = 0;
    int sel = 0;
    
    double lastsafescalehack = 0;
    int lastsafeoffsetxhack = 0;
    int lastsafeoffsetyhack = 0;
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.fillRect(0,0,W,H);
        g2d.setFont(fonb);
        if(loadingMap){
            g.setColor(Color.WHITE);
            g.drawString("Loading map...", 10, 10);
        }
        g2d.scale(scale, scale);
        if(!loadingMap && loaded){
            try{
    //            g.drawImage(data.base, 0, 0, (int)(W/scale), (int)(H/scale), null);
                if(debug){
                    g.drawImage(debugi.getSubimage(offsetx, offsety, 1+(int)(W/scale), 1+(int)(H/scale)), 0, 0, null);
                }else{
                    g.drawImage(data.base.getSubimage(offsetx, offsety, 1+(int)(W/scale), 1+(int)(H/scale)), 0, 0, null);
                }
                g2d.scale(1d/scale, 1d/scale);
                g.drawImage(data.thumb, W-212,H-200, null);
                lastsafescalehack = scale;
                lastsafeoffsetxhack = offsetx;
                lastsafeoffsetyhack = offsety;
            }catch(Throwable t){
                g2d.scale(1d/scale, 1d/scale);
                scale = lastsafescalehack;
                offsetx = lastsafeoffsetxhack;
                offsety = lastsafeoffsetyhack;
                g2d.scale(scale, scale);
                if(debug){
                    g.drawImage(debugi.getSubimage(offsetx, offsety, 1+(int)(W/scale), 1+(int)(H/scale)), 0, 0, null);
                }else{
                    g.drawImage(data.base.getSubimage(offsetx, offsety, 1+(int)(W/scale), 1+(int)(H/scale)), 0, 0, null);
                }
                g2d.scale(1d/scale, 1d/scale);
                g.drawImage(data.thumb, W-212,H-200, null);
                //TODO FIX THIS HACK
            }

        }
        g.setColor(new Color(136,119,85));
        /*overview*/g.drawRect(W-213,H-200,209,176);
        g.setColor(Color.BLACK);
        /*overview*/g.drawRect(W-214,H-201,211,178);
        /*overview*/g.drawRect(W-214,H-22,211,17);
        /*key*/g.drawRect(10,H-22,163,17);
        
        //
        g.setColor(new Color(119,102,68));
        /*overview*/g.fillRect(W-212,H-20,208,14);
        /*key*/g.fillRect(12,H-20,160,14);
        
        //136,119,85
        g.setColor(new Color(136,119,85));
        /*overview*/g.drawLine(W-213,H-21,W-4,H-21);
        /*overview*/g.drawLine(W-213,H-21,W-213,H-6);
        
        /*key*/g.drawLine(11,H-21,171,H-21);
        /*key*/g.drawLine(11,H-21,11,H-6);
        
        //102,85,51
        g.setColor(new Color(102,85,51));
        /*overview*/g.drawLine(W-213,H-6,W-4,H-6);
        /*overview*/g.drawLine(W-4,H-21,W-4,H-6);
        /*key*/g.drawLine(11,H-6,172,H-6);
        /*key*/g.drawLine(172,H-21,172,H-6);
        
//        int itr = 0;
        
        
        g.setColor(new Color(0x77,0x77,0x77,220)); // disable alpha here to make it more "original-like"
        g.fillRect(11, 156, 161, 420);
        
        g.setColor(new Color(0x55,0x55,0x55,220));
        g.drawRect(11,156,161,420);
        
        
        g.setColor(Color.BLACK);
        g.drawRect(10,155,163,422);
        
        g.drawString("Overview",W-135, H-6);
        g.drawString("Key", 80, H-6);
        for(int i = offset; i < offset+26; i++) g.drawString(keys[i], 32, H-26-((i-offset)*16));
//        for(String s : keys) g.drawString(s, 32, H-6-(itr++*12));
        g.setColor(Color.WHITE);
        g.drawString("Overview",W-136, H-7);
        g.drawString("Key", 79, H-7);
//        for(String s : keys) g.drawString(s, 31, H-7-(itr++*12));
        for(int i = offset; i < offset+26; i++) {g.drawString(keys[i], 31, H-27-((i-offset)*16));g.drawImage(POIDB.POIImg.get(keys[i]), 14, H-41-((i-offset)*16), null);}
        g.setColor(Color.YELLOW);
        g.drawString(keys[sel], 31, H-27-((sel-offset)*16));
//        int itr = 0;
//        for(String s : keys) for(int i = 0; i < 16; i++) g.drawString(keys[i], 32, H-6-(i++*12));
        
        g.setColor(Color.RED);
        int ww = (int)((W*(1f/scale))/28.61f);
        int wh = (int)((H*(1f/scale))/30.54f);
        g.drawRect(W-212+(int)(offsetx/28.61f), H-200+(int)(offsety/30.54f), ww, wh);
        g.setColor(new Color(255,0,0,128));
        g.fillRect(W-212+(int)(offsetx/28.61f), H-200+(int)(offsety/30.54f), ww, wh);
        
        g.setColor(Color.YELLOW);
        
        for(Point p : POIDB.POIPoints.get(keys[sel])){
            
            // I CANNOT get this to work for some reason.. lines it is..
            int x = W-212+(int)(p.getX()/28.61f)-2;
            int y = H-200+(int)(p.getY()/30.54f)-2;
            g.drawLine(x-2, y, x+2, y);
            g.drawLine(x,y-2,x,y+2);
            g.drawRect(x-1, y-1, 2, 2);
//            g.fillRoundRect(W-212+(int)(p.getX()/28.61f)-2, , 4, 4,2,2);
        }
        
//        g.setColor(Color.GREEN);
//        for(Point p : POIFinder.candidates.keySet()){
//            int x = W-212+(int)(p.getX()/28.61f)-2;
//            int y = H-200+(int)(p.getY()/30.54f)-2;
//            g.drawLine(x,y,x,y);
////            System.out.println(p);
//        }
        
    }
    
}
