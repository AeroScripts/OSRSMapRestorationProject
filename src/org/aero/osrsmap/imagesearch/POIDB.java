/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.aero.osrsmap.imagesearch;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Luke
 */
public class POIDB {
    public static HashMap<String, String> b64map = new HashMap<String, String>();
    public static HashMap<String, BufferedImage> POIImg = new HashMap<String, BufferedImage>();
    public static HashMap<String, POI> POIs = new HashMap<String, POI>();
    
    public static HashMap<String, ArrayList<Point>> POIPoints = new HashMap<String, ArrayList<Point>>();
    
    
    public static final String prefix = "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";
    
    public static ArrayList<Pixel> universalPixelMatches = new ArrayList<Pixel>();
    
    public static boolean[][] pixmatch = new boolean[15][15];
    
    public static void init() throws IOException{
//        b64map.put("Axe Shop", "FRgAAAS1JREFUeNqckz9SwkAUxn8bKQhJ7pCGTg6xWOIhpKfwBpAjaM0N7MnYkLFxGEpn0NEC0ogzm9EmcUYbloJJSOKGwtfszrf7ve/9FVpr6iaE9QfUeifqWMtEiqJ5o8OyE5ErC2HpOmkyHh/OICgwKfuFA6uJOAtDXM8DII7jAo+ieRFFC4PNwpBEKWy7zSmzTKrPqxVZluK6Hlma8v04rbzn6gKENhUI4OX+ht1nwt3rGee9Hh3HYblYMAkCpOwfcj5F3P7+4HoeHcchUapSAyP542FaELtyhG23SZQiy1KuhsNjzibi13vM23ZDV44ASFTCZr1mMLgEwPf9Y5/zouWhLpZPXFzfVj7mofq+X/S60qomYv3eOGFlBZOVJ0yUF+PUbEvZp3G2/7NV+wEAGNGzL5rwh1kAAAAASUVORK5CYII=");
//        b64map.put("Bank", "FRgAAAP1JREFUeNqUU6GOAjEQfdOs2vAPVOPWrDm1BMNncMHisKizOCwBxS+A2wZyGJINDl3+4YJkTs2ku3T3jmemfc170+ZNiZnRBJF5IZmf1OSSmMi5Ej/3L+V7/YWehSYknYkMO1cCgArzLMXl+qiZFMVQDUyXUGqepTh9H+G9h3Ol3sIggjxLsVwdAADL1UHXL28Ou7Zh8LGu7aW7wT9wO09hrYW1tsZHxZfrA/PZGAC07ncjeO+7xb3+Qg3kzWLQRLSzGPyFhPlJsai6IFknbVEJ5rNxa1RGRq4ohkqG2UqVuMIJo/BjyORstxvcztNazpPJJ1pn+91fVVUV/w4AGdOFycaVQkUAAAAASUVORK5CYII=");
//        b64map.put("Herbalist", "FRgAAAURJREFUeNqckz9Lw0AYxp87RMRF6FB1kZuk4OQUt5IMin4BCw5Sx84KdvED6CdwyeJU8BM45DI2m0OFgg7hFNI4ZBAJKNrXoVzM5U8Fbzreu+f5Pdz7HiMiFBdjvFQkmrJibaFKJKVXa5g3YZrMGKcqUXHZtpMZ8HnC88fTUk1KL0vBi4cXNx1D6I56tSl4FVUbbH6kiBMFd9TD3sAp0Xk+4vHRAYaTVwDA4vAFcaKw2thAnCgAQBiGJllvVqIx2n2B9fsl48Lt2xP8KMXhVgve+2W1WAaziO2+yOh+lOL7+Qs0A2PwMDbomfjuzMsMNN36bIIU0NlvZdGN3hOR0ardKwe2tQwZpNhZa0JsNxAnCn6U4tpyAQDd7gmIpqzUKp1A0+NEQQa/QiEE/pyw4stqYX7CWP5jzJtt23ZQO9v/+VU/AwD7wLSZOZlvYwAAAABJRU5ErkJggg==");
//        b64map.put("Magic Shop", "FRgAAAR1JREFUeNqcky9rw1AUxc99tCQ1/QSDhamJtWYQKFOpy0eY2GShzM/uYwxqKipWXxHX6E2VTcy+QCtmoxIKuRPjPd6fJGJXXu75ncs79xEzwy0i4TWZG3J7gzZRnu87gSaElDORYFMULxf4eF15kCSZa4BoEwJAmNaIlwsNUpXne72FcMlqsMoCjJ4bSCktkFnCdVWDYVoDAB4/X1BlAcK0hpTSch+4NDWoH2UyRIgaVRYA057XNh1pMvQA3tptzl7GX2ccdif05vx9G4Pet7jOriynw+6EshB+9sxsRXXx9oDj+gfjywYAtEiu/jKPokhn7eGO9xucn25QFgJlITCe3VlCz7nrUFQ0ptC8MDI/Rt9tJ8kcnbf9n1/1OwAFapRp/oRtqAAAAABJRU5ErkJggg==");
//        b64map.put("Quest Start", "FRgAAASJJREFUeNqck6GOAjEQhv82J1CbE6jFVG66igSBXRQBgSAh4RXuAU7dK9yL4BCQVaxFbLJqN5U1oBAEdao9Ae1tt1tzY9pM8s3/T2dKtNboBiHUS2qtSDf31gcVxSlYsF2EGGVCqO5C+3OC1VQ4uSyb2QI0BJaCO6eJojhZF7RrrxTcA0rB8fntdQLapwoAl7uy992hwWbJIaV01Gmf1ctdYfROHRAAbj+L/tfenxMAylOO4hR59QfkVYqvbe3Cq6nwes0rgse1RhSnmI+fUxkOjni5dx9skjRe71Gc4nGtLQgAjLEnrLUiWTZDKOZjjc2SY3doLGhm7Y1qkjRWwcRwcMTHWvqjMivXVmeM2RbaVtuqznqGdltKaaHgbv/nV/0OAGjUkAY+DbXyAAAAAElFTkSuQmCC");
//        b64map.put("Water Source", "FRgAAAN9JREFUeNqcUysOwkAQfbPlEMhKHBbJ1iLQKILkDqSCSyAbEi6AQ7GYBnxDQmraS1S2iyC7afcH4anNzLx5bzKzJKWECSJmBaXsyIyNXCQhrt6G/SaklImYNEnnxwQAsJy9dIzzRDdgIWJRRZiOL6jrWseFuGoXDB4UVaTfcRw7a1jIrg9KneELTveFN2eR1az7zcd2XjaDmYNkAJqosD3OfyMXVYQ0a61ClzqTsiPOEyuRZi3ysnEqql1byrvVE3nZDIiH9c3ZxHthpk216/6FUf9jhG5bjea87X9+1XsAjdZ5cPHaHfkAAAAASUVORK5CYII=");
//        b64map.put("Dungeon", "FRgAAAOxJREFUeNqkUzsKwkAQfbOm0BvYiFsLthLs4jG0SaPgIUKQXCWQwgukMp0XEGxdtMkNbELGatdls5GAU83O8N7b+REzwzUi0Qkyt+TGAh+oqi69hDZJYCc1aF2/0Jyyr0KaGEIiwZpAuEAAaE4ZJouleRfx3vhVdTG/EBhoSqlOTLiq2t73Wy+RVh+s7DMvOEgT458fT+zieDj4Op0hSBMDzMMQUkr8nLNLMC9L5J5GGWXmlqJo00ms6xdWxwO241FHNYo2YG6pt2F6SYp47x2TqdmnbtfsUwUAsg/Dt9tKKUgpocnt3aZ/ruozALQkdLH+75fLAAAAAElFTkSuQmCC");

        b64map.put("Achievement", "FRgAAAUFJREFUeNqcUz9Lw0AcfXcmS5dMWQpCBqW4BGxKC27p4GDXgl+hQ0cHXRxcdHCUIv0MXSvokNscpE1HKTocOHfIkiWacwg57nKJgz8IgR+8P/fuHRFCoDqEUGMpRE6qO6sOxFjUSKiSkFKZECqqoPF2hEVnqe3CcCgJaBPw8uNC+5fDWCRdWFV7t841klVqEG3iGM/nugCtqiarFE6vhc/8Xe42cYzjbhecc02d1lldv60R9AMNCACP2YMeIkAEYxHG2xHqJt1lxu7p5AVhOIRUXnSWOKBH8gv6AdJdhp+vbwCA7/rwXR+z9lza12zfHd4DAJxeS4a2t29Jgok9BQB4nleURIicqKGpwFIRbnF2DApgedfGVV0lN+A218KZ2FNgYObxZ8POXk8xa881q2rDiPow6rrNOZdWG7v9n1f1OwBsMaNXKfdePwAAAABJRU5ErkJggg==");
        b64map.put("Agility Shortcut", "FRgAAAPVJREFUeNqcUyEOAjEQnG1QkFMkkPsAmgdgWssX8HwASwjB8gyeAPYqQMPpA0EwJNgLBpJbBGm5Hi0B1jTdZma2u7PEzKgGkXhLMhdUzdV8IK2TIGGZhIwykWAfqBpSKksgfgGaqkwVTtnrlsZks3KVoi7G8cBLJIxqfs3fgACg8xSz88LeVTay6lZ5ud8BdWDa6wOAQ6Tz1Dmdbmf3Jg7tE5J4DlyeD0lHYnZeBIG2YfHt6P3XOB5ARt1g8wQARI0o3F2PojPn0KhUNvKCeLgFc0G1TzNNOnO/UaBeZTMXJKX6yiRlh1F5MT5525B7vf3PVj0GADjifGEjLQBjAAAAAElFTkSuQmCC");
        b64map.put("Agility Training", "FRgAAAMFJREFUeNqcU8ENwzAIBNRBsoqziTNYvElYhUlyfVRQ4pJWqqXI8eHjOBszAJoHs3yAwMkz9qhIqsdtwpyEXZlZkEmtrVQlam2NBFIR8zCzy1r1eNt6KTNUFaoKIi4/j/kMgCSreqlj7KHk/x4zs1CXuSQiot43qvAxdlqWJXCZPd55r3D5tbH3LUrOqrfk7H32fCEDJ+fAfGjuf4w9rs3vulSuiFXZlw5zv2YWG13N17nDOD+Mb73tdsre/udVPQcAM4+awhq8+YoAAAAASUVORK5CYII=");
        b64map.put("Altar", "FRgAAAMNJREFUeNqcU8kNwyAQnEXuw67EK/x1S87LtJACkh7yNdpUQiXevExsAghlPkiDZvYmVUUKIvNDqu6Ucl1O5P1WNDyb0BGZyGgqGnnGW14XztopGphUOPJcfb3fYhYGGYQQYsTn444QQuQvpQCk3m8QEbSCmWHt9G0YM0NEcFuWomh1Dn3fxwxM6rg6VxUCwDAMKNZcwyHMiktp53hS1TiqlqYdzVLdqUs/0nHkxti0YTmcN4zOh1HbbWsnFHf7n6v6DADRGnatEOmQjAAAAABJRU5ErkJggg==");
        b64map.put("Amulet Shop", "FRgAAAO9JREFUeNqck7FqAkEQhr9ZhHR5gBC4wzfICyh7lra5NnkqH8C0qVOeRwQLn8BUsjYSCKnFxkkRVs7dOQn5y1n+b/7ZnRVVJZWIy4qqJ0lrA8vUtoteYBcisbOIU8uUyvvqDBBVNY3D20fuHw4ALN/fTMDAoh/3ni0ti3kA4Ol5mgEABOSi6+51QlE3hBAoy/JcH40vAd5XuJRW1A3HvTeNIYT+2466uWuzmdMkQN45Ru9qNJ5mxl6zpTQygFM9ifdVdrD5+OYryNW3NjsXdcPnynFY/0Z9mc9MyNUN60aNM2cb9pfdjqOZu/2fX/UzALqag9HNkhQuAAAAAElFTkSuQmCC");
        b64map.put("Anvil", "FRgAAAMdJREFUeNqkU8ENgzAMPEcMkikqvuFHpyCTQJYiv/KNGKB8mQT3UQWFxKnU1r9Yd77T2SFmRl5EqmgyH5T3Gom0LI/qwHQIRWUixRIpL2O6c4D6hhhdRRcKfxQBdKrO3mMNAZNzmMZRJEzOnfYvga0hvAEC8da2uPf9padyQDo91mBtQSxsFxbHEYO10FqLqVcDm70HADy3Dfu+i5iG+aB8VTG4NIdUPe5aVI6E9C2pVy9MAmutLxdG6cf4dNvGdKje9i+/6jUA5Z96Hat2SMoAAAAASUVORK5CYII=");
        b64map.put("Archery Shop", "FRgAAAShJREFUeNqck7FKxEAQhv9ZUwhWgpizUFL5EFbudYL4DB4qCKelop1XnaUHpvABrLQQ8YprEmx8A4liEziMOcIJdkmzYyF7xmxyAadZmOWbf4b5h5gZxSASRpJZUTFnlUG+71UWzBchrUwkuAwqhpTNSQGrDnTdS9h2A87yChbsRfi+ByLBzIpEnVK7fYDRKMbzS4B+/wFhGP7OXNduMOhhfT7BzWsyyWn1qcrBoAc1/oG2Wy3jX9SBUZZibm0XAHDW6VSvyjrcR1fF2NySUOME71+fWN04BgA4jvPnNZSvZiKcigaubz1EWWqARtvMiqRsAgB2Lu7RVTHOZ5fw+PRWCepdGzMfuXc4ST+wN8xqDVPpML3PonLeYZQ/jGne1qOVevs/V/U9AFyekvj9UKKnAAAAAElFTkSuQmCC");
        b64map.put("Axe Shop", "FRgAAAS1JREFUeNqckz9SwkAUxn8bKQhJ7pCGTg6xWOIhpKfwBpAjaM0N7MnYkLFxGEpn0NEC0ogzm9EmcUYbloJJSOKGwtfszrf7ve/9FVpr6iaE9QfUeifqWMtEiqJ5o8OyE5ErC2HpOmkyHh/OICgwKfuFA6uJOAtDXM8DII7jAo+ieRFFC4PNwpBEKWy7zSmzTKrPqxVZluK6Hlma8v04rbzn6gKENhUI4OX+ht1nwt3rGee9Hh3HYblYMAkCpOwfcj5F3P7+4HoeHcchUapSAyP542FaELtyhG23SZQiy1KuhsNjzibi13vM23ZDV44ASFTCZr1mMLgEwPf9Y5/zouWhLpZPXFzfVj7mofq+X/S60qomYv3eOGFlBZOVJ0yUF+PUbEvZp3G2/7NV+wEAGNGzL5rwh1kAAAAASUVORK5CYII=");
        b64map.put("Bank", "FRgAAAP1JREFUeNqUU6GOAjEQfdOs2vAPVOPWrDm1BMNncMHisKizOCwBxS+A2wZyGJINDl3+4YJkTs2ku3T3jmemfc170+ZNiZnRBJF5IZmf1OSSmMi5Ej/3L+V7/YWehSYknYkMO1cCgArzLMXl+qiZFMVQDUyXUGqepTh9H+G9h3Ol3sIggjxLsVwdAADL1UHXL28Ou7Zh8LGu7aW7wT9wO09hrYW1tsZHxZfrA/PZGAC07ncjeO+7xb3+Qg3kzWLQRLSzGPyFhPlJsai6IFknbVEJ5rNxa1RGRq4ohkqG2UqVuMIJo/BjyORstxvcztNazpPJJ1pn+91fVVUV/w4AGdOFycaVQkUAAAAASUVORK5CYII=");
        b64map.put("Candle Shop", "FRgAAAM1JREFUeNqkUy0PwjAQfdfMTQ9dj0evEwQLngTFj0JhsPwAxIbmH4BqkCwosuB2qC5dv0LgmSbXe/eu13fEzHBBJLwgc09uLAuRmqaOFrSLkFEmEuySynaOc3EaxZSqhgIiRbRPg6aphy4EEngt1wAArXXwXqRUDTb5NqgeVHbfue92QfVo2/3qjedtAT09AACklN+Tv8Ff5Iy5J3toctJ6SeUsB9BCP4rRX3vKJuF+PY6HeOm8oUUd5iaagdkOI3sxUt5WqkLU279s1WcAEMZ0Q8zNQUsAAAAASUVORK5CYII=");
        b64map.put("Chainbody Shop", "FRgAAASJJREFUeNqckz1SwzAQhZ80FDCEktQb09hju2Ogds7g3gcIx4kPoIvEdTLpiLEbHNWhVpul8o5/BMOwnbTzvv3Rk2JmTEMpPbtkvqrp3Y1PVFW7H4FDiOorK6XZJ5pGlq0FoJh5JjTGAADOXYckTeGcQ1EUM4Ceko0xiMMIALAKAjjnEIeRAIehh1XLcgsiQt02AojDSM49oKp2UEqzVC7LLZIkhbUWj8ulCOq2ESARjToQ8WbzBmst4jDCR10LgIhwOr0jDiPJH/eHsRiALIWI8HW5iGAVPI3yz68vc3GfsNaOZj93n6O8tM18VVm2HgGGrdZtgyRJvW+tfUbI8xyLxQP2xwOICPe3d17D/OqwfjHDdmcO+4u3+9G83v7Pr/oeAOctqb/YfwjVAAAAAElFTkSuQmCC");
        b64map.put("Combat Training", "FRgAAASlJREFUeNqMk7FKxEAURc+MoCgIi03snM5KGyH9LjY22ollUD/AUsFiy/yEi5WI3yC7va3K2iQMWGihItoFzbNYZshmEvU1A8M79w333VEiQr2U0uElKJGyfiEBNBoNA7Lb7QUiHlZKSxPUIqJEyglcBc+OtlnsLDSCu/3LKQHd1BQnqT/jJKUTLREnKdbaqT7d9Nyb82M/xRjDjHrHGIMxhoon0jrZWosxhvuLU7YOT5j9ug36dJsxbsrKxjoA47wIn/2bs8PrfT6fPxjnBQDfxev/YYC7/IFIMgAiyaama0BVAuANA+htDlgu33i0T0SS8TIX+1WJlO2ruurvAbB2MGB+dceDzgufMDXR8CtzTruznjCRUtXjCfBnth0YfIy6SLWqkKufAQBQQJD90/k3lQAAAABJRU5ErkJggg==");
        b64map.put("Crafting Shop", "FRgAAALVJREFUeNqckz0OwyAMhW2UKwR1ZOlALxCpCyKXicSBIvUizRau4pPUnUCUv6J4Qkafn2U/IzNDHoiiSDJ/MM9NNcj7s1kwLYJBGVFwDcrD2jUWECOgtWt8e3/GLsSIktZ3cG4r/sRou3K+wfs4ftSnEdVlecIsJTy0BiICpVQ57RZojIn5AHbhHEyhLuzcBvv+AiJqgnHP6dBSsAWFXRer+gcWylcdhulh9LwdXFb19pWr+g4ANUlrjtmlAGcAAAAASUVORK5CYII=");
        b64map.put("Dungeon", "FRgAAAOxJREFUeNqkUzsKwkAQfbOm0BvYiFsLthLs4jG0SaPgIUKQXCWQwgukMp0XEGxdtMkNbELGatdls5GAU83O8N7b+REzwzUi0Qkyt+TGAh+oqi69hDZJYCc1aF2/0Jyyr0KaGEIiwZpAuEAAaE4ZJouleRfx3vhVdTG/EBhoSqlOTLiq2t73Wy+RVh+s7DMvOEgT458fT+zieDj4Op0hSBMDzMMQUkr8nLNLMC9L5J5GGWXmlqJo00ms6xdWxwO241FHNYo2YG6pt2F6SYp47x2TqdmnbtfsUwUAsg/Dt9tKKUgpocnt3aZ/ruozALQkdLH+75fLAAAAAElFTkSuQmCC");
        b64map.put("Estate Agent", "FRgAAAN5JREFUeNqcUzsOwjAMtaMMVKJ36NIhhwCUjvQU5WD0FGVsBBwiQ5eOCMZ0rpkS2pBECEtRItvv+RMbiQh8QWRfSqIZfR0PgZTqo4RLErSRERmFQL5IWTkC5gOlrEDKyr19Uap3WbAQuxBlksDVHEpX6wHq+giTMSsC62ejc7+ew34H2zyHLNsAAMBkDAhROrsXCEkpRQD487H+fNkIP7pN+/l6gNYDtO15VR6LNaPrLkF9URQfMNGMqY6m/pqHjNfbfXXHJDph4zgGAU1zchOGy8VIzbYtLTjb/2zVewDgx4jNP3fBJQAAAABJRU5ErkJggg==");
        b64map.put("Farming Patch", "FRgAAAN5JREFUeNqck7EOgjAQhv9reBBewUcARwwOxsFdZ1mcZHU3bibyGrDJq3SGxEXqYkIdSAnWthBvvPS7+3v3H0kpoQcR+0lK2ZKe80xQWd6tBYdFSHUmYtIE6REEYV+ATQGv6QZZEkOpUioYJoTXCMyTC9Ld8ivPpsq9nfYAAM55390bg7IkxuPNsD2e4Zy2DVwfOtD3/XE4Lwq8hAAAK2gcWF4UqKuqqzxbWUEAYFK2FARhn1CgEAJN83Tu2rqquq4RRQvnMI0O45z3D3TJQ4fR8DBc3lZfM3r7n6v6DACp13iFZ+ovvQAAAABJRU5ErkJggg==");
        b64map.put("Farming Shop", "FRgAAAS5JREFUeNqcUz1LA0EQfbN4iV+oHIIIFlvZKOnTeQFb/4GVpM8/sNAfYGMlYpk6/oAcBH+ABK6UWzuTKAHhJMJxYxFub/f2TsTpdpj3Zt7MW2JmlINIOEnmjMq5lSpQGA5rCU0SyjsTCa4ClSMIOpqAmNkC3vfOdGFrdwNHe6v6vd59sAiEyZoDR7MUo1mKwesXbp/n6EcfAICD/rmt2ewaJ0s5xzsNnOyvAYDuvDVu4g1NqLZCGA5BJFhU6fK9DOP3BAAQTRaIJgsAwGfru37b2418ke75ykAH7HsZ4oTge8tlmWMDwOFmiqlRb419cfP465me2leQUhZg5oyCoGMVxQlpzS+Cam/tLOz6blCQpIX26eml67o6hymlnGIppeuwv3g7l1bp7f/8qp8BALHIimJCewqAAAAAAElFTkSuQmCC");
        b64map.put("Fishing Shop", "FRgAAAONJREFUeNqkk70NwjAQhZ+dNCmQ6GiPARjCicQOtERiDAZBCozABImHyAInpaKjo+MokFFsx/yIV975u/cs+5SIIJRSOiqK3FVYy6cga7vkwPGQfNycgq4HAwA4b4D5zkIpLW5A/g14XZ9ARGBmWNu9BmgkNAZT0lOuIUhEXt+560+ODqz4Fjv/AtZYTMMOHLIliCiK6sTMMTzfWQzZEgDQN1vvUEsFAKDBxX97EfGeqm+2mFX75H1bKmBMCZG7ysNYq/oIZvZit1S8kowCPZ3ffZRQztWDP/1tY8poQdQ/W/UYAKxFiEAQLJOaAAAAAElFTkSuQmCC");
        b64map.put("Fishing Spot", "FRgAAAQBJREFUeNqkU6GOwkAQfbMhR5rT5/cTcLhLl1N1Z1En+xkN6j6BBHNycw5XB/wAooIE3KauQZ0gULOL2rLbbS8BnpyZN28yb4aMMWiDiAVBYzS1Y4Mu0nq96m3oNiGrTMRMF6kNISZNA3YP0U5lp2B4AqytmkYx0ijG5/I9KLYxq+4pp1GMSmpUUnvFLpRSN2U3UUmN4SLqVPtQZ/Ralcw4gBL1+IChU1AXJZKC4wVAjRIYIVTOM+UR6qLE3+8Wl/0R868NLvtjqGyMJru0PFMQ09cm+fO9u1kkT0hmb57Xg8BHeWqWwjn3cnmm4OzruQsj9zH+u20hJui97Ue+6joAJfmKVl+xCn8AAAAASUVORK5CYII=");
        b64map.put("Food Shop", "FRgAAAVVJREFUeNqkkzFLw1AUhb+XtlKokUKo2GKg7eLsVnBK/4FLwLGOzt1LR3+Bo4su/QEOCs1aXBxcpEsL1SINgWpICRbznBKTJorgmR733nPO4537hJSSTQihpIpSBmKzls8iWdbwR8G4SD7eDEmu5zIa9CNiy+xFgkIoMhQQUsoE8ebqnMLaZr+mA1AulniYORTWNi2zh1pSMYw2UgYice27yy6Nmg7o6PVqVD8Eln6R0aBPy+xFdSXuGiJOBFD3dqLz4mWMZQ0RQpFKfKhl9pjYPll4ns9oHp2Q29K+nRMOJZXfkNvSqNfr2WSARqWYIrmv75liCbLruUxsP3NY+qt09vGobi/O0JsHBI6NolWiqJa+R+DYjOcTjrvX2VEBPD3es61VYT4DYOav8Lw3FsuA3bLCdDpNOsc3LGx+fjiJhwrR6ZyS2LC/7LZhtFMfRPznV30NADwCoyGxFUJrAAAAAElFTkSuQmCC");
        b64map.put("Furnace", "FRgAAALpJREFUeNqkU7ENwjAQvDempIKWEWjpnUUYggGcDMAQLBL3tCxgiRYqyig5ChSUOHkRyEmWpX/d3b//LSSRQsQMgmQjacyOkUIoVcGuiLTOIoZjpBTOZR8BIdkj5t6rxLwoegKml/Qe2/qhnlTYjpV73j8HrofLCgAQY0QIJUQMjVai7JbA6f6+FRjMgNUSvFbAcQOi+o3c9vfVmWyk+2i3xXryrG06xxjj5J7nb9iU3XYug7rb//yq1wAqHXUYZL7AZAAAAABJRU5ErkJggg==");
        b64map.put("Gem Shop", "FRgAAASpJREFUeNqck7FLw1AQxn8vFEORLJbQQdDMgrh1KkjiUugsZHYodBb8IwqdpQ7tGggu4toEIZP/RGNX6SQUUmieU555SdrBm+7d8d139+47IaWkakIYtaCUuajGWk2gOI4OFiwXEQWzEIYsg/pBoPzE95Xvup4qYDQBby7ONdbeeKT8OI5UF0a1vQL48/QIwO4jqhUozCiz9oMAazJVyQIIEK7WfA0GGnuNGcCaTDGHQwBObj3C1Vrl0jT9Y+aIta+uAbjM88Z8qylYtLu3bR5mMwDuTLM+c/mR+L42ZzKfK3+ZZZy9hlinbbQ9V1fVG4+0OZdZxv37G7vtlo7dVbtunPnz+UV7e4sFm+8NHburq+6Qwqo/6zhOTWGifBjHtO26Hge1/Z+r+h0APtaLdTJnRG0AAAAASUVORK5CYII=");
        b64map.put("General Store", "FRgAAAYlJREFUeNqck00vA1EUhp87aeOrhKLNYDGJKGHVSNgQmS4kFSEWIlbW/A6/wX8gYmfb0YRYICSoz8Ygs/ARVS1CZa6FzNTUV+Ju7s25ed5zznvPFVJKSpcQypeglLYojfm+gwwjgWmabry6qsK9+ywinMxCKNIwEqwtL3Cc2uI1e8FD5hmABq2dsBqkf2yGkeFRV8BXClobS6StZy6fCsWSdnaZUAdYnJvFMBIIoUgpbeEp+y0QJnf/xIn18sWH/fV1GrR2T0xxsgL48lcAtDWXe3aA7Kvk1jzC8UQIRSqflbKZMya76omrOQYjZa7AYKSMuJoj6Lc9RnrKzp9u44820ROqowcoXGdIK4KH8w+goyZP6uAATdM+ynbAZDLJWLSJv9bV3kqxZ+fQogY4vL3/Ew6rwSIspS10PUZrpJvNu8ZfQUsJ0dk3ga7HkNIWHsP08WmOHyt/hAO9U26/306YaZqkVufxF26otSzO3qQLDsXjblYPXDrbgPssmqah6zF+nO3//Kr3AQDQ6bJwVo4NbAAAAABJRU5ErkJggg==");
        b64map.put("Guide", "FRgAAANlJREFUeNqkk7ERgzAMRb98wAKUaahYIBNAnz4tqTJSRkifHk/AAlRuKFkg3KFU8glju4k6S/f0pfMXMTPCIDKnJPNOYa6IQdaOyYa6SaGLAs1bfYKlRmRYGhAzR8HX++PB5/0GAGjLFQDQdT2YdzqMrUEB9Hveat8AAIxWDcFqmbyyTOKcg7UjiAwbJELAXBRhQlS/l2t0miTcliuccx6WUfUaTdOklaU4b8iCAGCYd+q6PrtbCMpXmRwU2/OgLJaLqWujhKreYTFva4tWy4RheJwOhP65qt8AdRuFFoyR1OUAAAAASUVORK5CYII=");
        b64map.put("Hair Dressers", "FRgAAALRJREFUeNqkU9sRxCAIXJwUYivYiSksdhJboZJwX+TAmMzkji9nh32gSKqKsYjSBVQ9aMSWGan3/VbQi5A5EyWdkcZiLqdAekO0VJYi4Y8igE5X5oLZzDOcucQL632HiIRm5hJwLxCcfYkIal0D1toGAMg5X53HmK1tqHU9SbWuwXm5m01EgoCdRQQ55+87+6cykjX4MXxk1YMusUfSE55s5Szymw0j/zGedtvEp7v9y6/6DAAZ54MKxYoLRAAAAABJRU5ErkJggg==");
        b64map.put("Helmet Shop", "FRgAAAUNJREFUeNqck79Kw1AUxn+3bUCNSwhpKR2cKiLiFNpRUoRYcfcFOvo4HV06+hKWqKsvUBG6SKk1BCnXakt6HUKj+dMinulw7vm+795zviuUUqRDiEKmqNRSpGulPFC/f7uW8DeJWCkLUVB5oHQ4TismEEqpDHA4HMb52/gVu9nIJSikmZ8Hj5iWiWmZABhGEc/zcm8hQMSqI++a/afooKtpXC0WDOo/zdWTTkI9ofyydcy006GraVjlMje1Grv2JV97pwyo40/GCeUE2DCK9Ho9AN6rOgD3dw8A6FvbbFxVEIRRo66zM5J8SLlx8pmBXZyfIWUEdNsutm2vByu1FI7TAsBuNpjKWaSs6/gTH4Bw7iM/Z5lVldJs4dzHbbsAMTgIQo4OD7KrynOYPxljWpU4X4VpVbIO+4u3V0/L9fZ/ftX3ALufl/FscIxiAAAAAElFTkSuQmCC");
        b64map.put("Herbalist", "FRgAAAURJREFUeNqckz9Lw0AYxp87RMRF6FB1kZuk4OQUt5IMin4BCw5Sx84KdvED6CdwyeJU8BM45DI2m0OFgg7hFNI4ZBAJKNrXoVzM5U8Fbzreu+f5Pdz7HiMiFBdjvFQkmrJibaFKJKVXa5g3YZrMGKcqUXHZtpMZ8HnC88fTUk1KL0vBi4cXNx1D6I56tSl4FVUbbH6kiBMFd9TD3sAp0Xk+4vHRAYaTVwDA4vAFcaKw2thAnCgAQBiGJllvVqIx2n2B9fsl48Lt2xP8KMXhVgve+2W1WAaziO2+yOh+lOL7+Qs0A2PwMDbomfjuzMsMNN36bIIU0NlvZdGN3hOR0ardKwe2tQwZpNhZa0JsNxAnCn6U4tpyAQDd7gmIpqzUKp1A0+NEQQa/QiEE/pyw4stqYX7CWP5jzJtt23ZQO9v/+VU/AwD7wLSZOZlvYwAAAABJRU5ErkJggg==");
        b64map.put("Hunting Grounds", "FRgAAAOtJREFUeNqkU8ENgzAMvETsEf9TfixAJF7tEvSDxABInaAS77IOGaLqn0lwX44MAalS7xc7d7YvjmFm7GGMzYLMq9nHiiNSjPOpoBaxOhnjjBhn9H2XSH3fYVkWSE53ZTVRQ0gAMI7PFNcCFj9ChDTsUdUzyDhS3R4lvS8xDI8U977M7mRuA8A0vXC93tJZi+xHKM5a1CZ5XyZB3cWGLFU0EQA+n3dGBICCeTViGhEduiq4XEo459C2dzCvJnsqIjolO+c2eSO7vX8y7WoIDaqqAhGhrkNa0UIvvt7tEJpN1ba9Zx/E/POrvgMAmX94QrPys9gAAAAASUVORK5CYII=");
        b64map.put("Jewelery", "FRgAAASVJREFUeNqckzFLw0AUx//vyOAQO3W25+Zgmy1bkMvkl3B36UcopTh29AsIhY6ubgnBQVAQk2xOsYtLUTAq2MHnID0uuWsF33Z/7vd/7917R8yMdhAJS2T+prbmuaA0TTYamia0zkwkuA11xhO8TcYNTalYG4ht4OfLM7zhaUNP00RX4cERYZShvrjT5929DLfXR3YrADWyhlGGZX6Oogyw5BN0aYZBP0c3GDYMlIp/yzajXkw1CAB+b4SiDFAvpqiqqnFXuMpeg1JKbfD1umPds+CHmw8cH95rEAAO/EtXDht+vNrHSswRRpkGV2KOogzsB2Nma1TvT2cY9HN9Nt9ASqln7RyV3xshKSpLN1v5c8NcYW4YmR9j224rFWPjbv/nV/0MAAyKjmXM1agZAAAAAElFTkSuQmCC");
        b64map.put("Kebab Shop", "FRgAAAWNJREFUeNqckz1rwlAUhp8rdisOWRxcQgkWca9DB4kUB2cJgS4qFLr0P2QplK4iOLSlowR/ghiC4A8oSIuGkkWKHRzErWI6WC9J/Bh6l/vBfd7z3nPOFUEQEB9CJHYOg2At4mfJfZDj9A8KhkXENrIQiSAMveg6AA3HiYjoekkKJI6BAJVhmcqwLPeO05cuEnF7X+6TXHfvT6imNJ5/DGzL3H0KCBnVtkyW7gyaOXmh3Vrx+DHhEzgtpjGsjrQvI9uWyWt5Ds0c3YVHd+HRbq2kyBmwdGcRB5FsV1Ma3L1TK6ZZujNumVC4ueQ7vSCf1TjPqAycHr7v78KFNwXFumY+9VH0q82cUckDSkZlPvUjb5a2DavDaOxJYDvzBw6cHqOxx0XtgUidw6WyLZN8VpMCo7EHEIHq9QZBsBbJePoNq7NJyh5IVdVoqQ512DYpcSjcYSL8MY71tq6XONjb//lVvwMAdRauRiDyhosAAAAASUVORK5CYII=");
        b64map.put("Magic Shop", "FRgAAAR1JREFUeNqcky9rw1AUxc99tCQ1/QSDhamJtWYQKFOpy0eY2GShzM/uYwxqKipWXxHX6E2VTcy+QCtmoxIKuRPjPd6fJGJXXu75ncs79xEzwy0i4TWZG3J7gzZRnu87gSaElDORYFMULxf4eF15kCSZa4BoEwJAmNaIlwsNUpXne72FcMlqsMoCjJ4bSCktkFnCdVWDYVoDAB4/X1BlAcK0hpTSch+4NDWoH2UyRIgaVRYA057XNh1pMvQA3tptzl7GX2ccdif05vx9G4Pet7jOriynw+6EshB+9sxsRXXx9oDj+gfjywYAtEiu/jKPokhn7eGO9xucn25QFgJlITCe3VlCz7nrUFQ0ptC8MDI/Rt9tJ8kcnbf9n1/1OwAFapRp/oRtqAAAAABJRU5ErkJggg==");
        b64map.put("Minigame", "FRgAAASJJREFUeNqcUzuOwjAUnGe5oQgXWCFFaVIhGi4QlDPkBCloqGjoopxhGwpOkCMgRC6QBlEhrZAlxAVIQRPhLUhCnuNssa+xNfK8mfcxaa1hBpHogVq/yMSkjZTnx8GE3STUKBMJbZLGSYpHmjAsCBZtAjFEdHY7djaR58fWhTTtmY8brMwyYL9nuLCpAgDd7u29zDI4UQSlFFMXNlW63aEnX4wIANPDwd7tcZJalaXv4XkqPoRVgep7y8mPNOnV+zwVqC5XSN/DaDYHAJzDEKjtM9tlHPdql76H6nL9EAG4rvsma/2iIFhgKEazOZwoene7Jjaz7o2qjGMopVhzzmEI1KqsL39tmFwt8bPeMKvdDaPux7DttlKqtTq42//5Vb8DACs4kYeJR3B/AAAAAElFTkSuQmCC");
        b64map.put("Mining Site", "FRgAAARxJREFUeNqck79OwzAQhz+HspCHwHQrc8SeIiGVubtnNh6Ap2Bgz8IT0JUqA48AEjBgiRapAQIVKmXqMTlK4lhC3Obzfb/z/bESEdqmVOQ5RTaq7et1QdPpdVCwLqJcZqUiqUPWWt4WBUX5zvFoVPnTdFgJKBFpgFmWEccxO3HsZXciTiBqBxhjSJKE/cGA16Jo3FlrG+eo/VwArTVaa5LtR3Y/bvherQCYTK5wPVEqkh4Bu708Y74s+VyU3MkWe/0+6/UP1lq01n632+Dz/YzD03MOgDzPvbioC3x4eapAVwbAeDwOww4sZ18eWO9HBYtsVJoOAZgvyyBojPFm3aj56OSiGkc7Y5cvuGEh8zbsL7vtSuvc7f/8qt8BAMi7omwmugtwAAAAAElFTkSuQmCC");
        b64map.put("Platebody Shop", "FRgAAAPlJREFUeNqckzFywjAQRd96UgR0CQMVodUF5I5cwgcgl3LvOnThFKR2jUuQSzZFYo2Q7Ewmv9FoV//vfmklqkoKkSILqt4ljT1NkU6nj1nBWETGyiKFTpFSOFcFAVHVjPh+PPJ5PgOwWDyzWm943e8zgSJVbtsWgJfdDoDVehMEMysgoWrTNBhjAFj+rCnGDpyrCJVjoveewftAGKJ93EEg13XN0histdxu14dq3nustd92tlu6rnskxy3NYcyXZZmT48Qc4nyhehfnquyQjzyb5PJmnwrgcHij73v6y4Uh8ps91W8TNl5M3G42YX+Z7dHa5Gz/51d9DQAnnoTf1fRgNQAAAABJRU5ErkJggg==");
        b64map.put("Platelegs Shop", "FRgAAAMRJREFUeNqcUzEOgzAMtCMmQscys9JfhDfwXiIe0paZjMUZcae0QOwK1VNyyp3P8gWZGY6FaDKQecUjVkgk7wdVcCuCqTOiYYl0LOe6jwAyc0Ycx3FHCCFA3/eZgIETRUQwTVOGF5pdilEV834ARMNiZ4oRno873Nr2pyORbMtyf7dWJBcSGEL4nucZluV1vnNpLVTVBQAArnWt22Ze0blOfRCJ1F2rq0pzbl1kqdMSlvbaNM3unCXsTLbTaGK2//lV7wEAzDByCpe6RDsAAAAASUVORK5CYII=");
        b64map.put("POH", "FRgAAAUFJREFUeNqcUz2KwkAYfZOVQDAs2mhlIyJkG4s0dot7BU8QK4+QI1jsAazWE+QKpk0zRarAMoRAKruwBASLfFvojJPMptmBKebxvfe972cYEaF7GLMMkKhhXWzwFymOz72CugiTmRmzSCdFYYYyrTBbjbA9eArfbD6UACOiFjEKMwBALWxFcBc3AFAiUsDSrUVhhlrYqIWNueurKzEprGru2gWAuetjul4+gQTIa66ecXwGYxYNulkl8c3TTS3vAoIjCjP4ewcAoCLKtDI6nEx/DEyPU+TZamQEri+vBjYeTsw5bw8eojBDLjiQPKw+ziX5VjW/f748yUQN6zYtr6WA9lYjcxAEOxA1rLVh/t4BP17vBMFbdt3FTTWqd8OKogAA8OMVZVphPJy0rMqsLbK+v6fTl9GoINihd7f/86t+BwDq2KLAcWNjfgAAAABJRU5ErkJggg==");
        b64map.put("Poll Booth", "FRgAAATdJREFUeNqkU7FKxEAQfbOkyCkpgngHgroHdmJhGa6Qs7CxOKy0sLnANX7DfUfKS20lilx5wcrSytqoIOghKQRNlbHaZbObYOFUO7Pz5r2dnSFmhm1EwgkyV2THvCbQ0+Nla0GziGde3txeY69fIuz4DjhNZ5BSgkiwKiAUMMsWDqD4KQEAUZHg5GANeZ4jyxZahTCTg9UA+ce69sOOj6hIcB9eoMmEzSq7S0RFohnnfgwAuLr7hJQSADS7aKo492PNqN6vZNeYbaCSbUt9eA40cytYdpeaTTUMAPa3v9w3m850Mqpdml92fHSO6WRUk+4xV2Q2rb97ijYb7AQAgOHwEMwVeXbC2aCH1/dv7W/2VvTZjAMAqdkmEry14eMve3kr9YiSuRhqctJ05oDG49hZEPrPVv0OAJm6gF7svgdGAAAAAElFTkSuQmCC");
        b64map.put("Pottery Wheel", "FRgAAAThJREFUeNpi/P//PwM6YGRkwhD8//8fI7oYCzZN+/fvw2kgsiGMMJsZGZn+Y9OEDhwdneAGMJGiEeYqmCtY0CVXNUQwKLIwMnyQlmaQYudgYGBgYGASFmVgYGBg0HTPR/UKAwMj3Na5BX4M978iwoWfDcH28XNEMcDR0QnibGRw++kPhttPfzA8/vab4cqHXwxXPvxi+PjrP8PSNRALHjx4AFeLodlNjZ2BgYGB4cf7v3D8+NtvhugQJwa8UYVsAK8AF4O2mgrcz//evsZQh2Hzu98Qoc8fvsHFYBpPPf2Nqvn//3+Mjo4QJyVP2MQgxPoPbsDVW3fgCq/eusPgGJqJEtcYNsMMgNl+4tQlBm6rFAazhA4GBgYGBgUFBQaCKQw5VJE1IacwRuSMgS9tw7yGNW2Tk6sAAwDDuJa3Kt/5OwAAAABJRU5ErkJggg==");
        b64map.put("Quest Start", "FRgAAASJJREFUeNqck6GOAjEQhv82J1CbE6jFVG66igSBXRQBgSAh4RXuAU7dK9yL4BCQVaxFbLJqN5U1oBAEdao9Ae1tt1tzY9pM8s3/T2dKtNboBiHUS2qtSDf31gcVxSlYsF2EGGVCqO5C+3OC1VQ4uSyb2QI0BJaCO6eJojhZF7RrrxTcA0rB8fntdQLapwoAl7uy992hwWbJIaV01Gmf1ctdYfROHRAAbj+L/tfenxMAylOO4hR59QfkVYqvbe3Cq6nwes0rgse1RhSnmI+fUxkOjni5dx9skjRe71Gc4nGtLQgAjLEnrLUiWTZDKOZjjc2SY3doLGhm7Y1qkjRWwcRwcMTHWvqjMivXVmeM2RbaVtuqznqGdltKaaHgbv/nV/0OAGjUkAY+DbXyAAAAAElFTkSuQmCC");
        b64map.put("Range", "FRgAAAOBJREFUeNpi/P//PwM6YGRkwhD8//8fI7oYCzZN+/fvw2kgsiGMMJsZGZn+Y9OEDhwdneAGMJGiEeYqmCuYGCgAjAwMjHBb5xb4MTAwMDDwCnAxaMpyMVx//I0hrGEFTufDA2xVQwTDkotfoLwvcEWXUv0Z+NlQA7p06gbM0J4Uo8Bw/fE3hs8fvjEwMDAwvPvNxPDxF2ZUPnjwAFMzLhBaOgGrOAuxgaOgoICp+f//f4ywqLo8NwlDQUr1RJxxjWKzbvI8Bl6of/DZSJUUxoicMfClbUdHJwacaZucXAUYAG93bGroYb7gAAAAAElFTkSuQmCC");
        b64map.put("Rare Tree", "FRgAAAPlJREFUeNqkk7EOAUEQhv9Zp6DVScg+g0aiuytIvICaQqFXeAa9hAi1F5C44nSe40Ki02pwo5Bdu3d7QUx198/N/83tzBIzIx1EIiMyJ5TWPFfRfh/lGpomnpk0izqbAIXa21vliAQrA2Jmq7A9DUB1WIUqtq0QAOD7AZgTEvgh4ji23oWL6opypYjReah/gUjw1+Tr5QYA6B7ab3JmJMd8crr9XPLjdHeSZ9WFmxyOI02fN1dorEvWx+pZSvmaM3NC5qGF4yhzqttWqDUppR6V52pZOX/ShFo53w+sRK/bAQDslhNLV1S9YXm7bbbf7w8yF4T+uVXPAQD84X1gnTVznAAAAABJRU5ErkJggg==");
        b64map.put("Scimitar Shop", "FRgAAARNJREFUeNqkk71KA0EUhb8ZxcqHmMbGV7BYNlgLEawX0WdYCdiLeQBBIYK2WRQsTCPZykIxVawCkSllg1V+LISM1QzZ3ckPessD3z135p4rjDEUSwhZEo2ZiqK27oPStD234WwTYZ2FkMYHFSsMK66BXBV8e3nFTmWnkKxQ/V6Ho4MqWuucLpe59nsd9nf3qB4eO826l5xPTpsOsuDd0wPZ4JOFv110A2g077m+aRDHteVwsLPJ1e0HaxvPJEnCaDh0oFJqPvzYajEZj9nemlCvn/GlNecXl14QQBozFWFYAWCQZbx3uwRBQBzXyL5/vKDddc45iiK3DqUUadourSeXuv8kTMwexqJs26d5s/2Xq/odAD3PlCkK6NY5AAAAAElFTkSuQmCC");
        b64map.put("Shield Shop", "FRgAAAMpJREFUeNqkU7sNgzAUvGdRhLAELRHpWMAsBu4pwyq4C6uwAwFS4VS2sLFRSF73TrqPns+klII7RGwHKrWSi0U+Ut8/g4JbEdLOREz5SO5wXhoB5hLrqkLTNBahkxKdlNCpdIrIp/4aR7TtA3l+xzTPBh+GAWmamp19E3eeJmvX7gx/zCF5G/k0+bRzLQQAYFneFn7Lsh05Umql0NHcQ+lL67f2xq6FQBxfcE0SAEBRFN7Yhw3rpDRxXVeLHOq2LgbnJYLd/uVXfQYAOW1y7ktf99kAAAAASUVORK5CYII=");
        b64map.put("Silk Trader", "FRgAAAMxJREFUeNqckz0OgkAQhd8QWu1MKGkoPcQSG1s6SryMlzDhBrYUGjmEF9hyEworSx0rkFlmI/jKyX4zb36WmBm+iKJJkPlNfizWoLa9BROOk1BfmShiDfJlTD4kIGYW4H1XT4BknQAANue9SCBs96B7OQG7h0NWl3haizRNv60AJOxqlXttr5WwH2sPuqJR4a5oBusAEGGhrLX6qn7ZXp2M6FnAF3MMglldTnc/Z1X+wNRVLR1Y8MLGgxmrqg7ywubctjE5grf9z6/6DACIdHd9gOYEpgAAAABJRU5ErkJggg==");
        b64map.put("Silver Trader", "FRgAAAM5JREFUeNqck2EOgyAMhVuyg3gVepKxc2DQyLlKvIknsfvhShDBbHt/VML7XosFRQRqIZrLosiO9dqjZUqJu8ASgpqMaKRlqmUtZYD5xahVaRWmtWFdV5hCyM+eTCs1MYMlgtF7sEQZVKdfkqcQgPmALTEefRJBYj4BumUTUX4fvQcAAGaGxAzbtt2bNalMX2IEW0C7Zu1ZAemTqt/ZLLKjtXQL0MSnc6d/fT2wec6bymTVMAzw1YSVh6Ny7pUnDMuLcTfb2lpztv+5Ve8BAG04kIAwgTsWAAAAAElFTkSuQmCC");
        b64map.put("Skirt Shop", "FRgAAALhJREFUeNqkU7ENwjAQ/H8lJQXehIIBnEkyGjMwQLwBDQUlbihAyBkAKUdlKzhvOcBV9ll3f5bPDIByMMuCBCbOuUYTOTcUDecmHCczCzRRDmu7ZMAAVKE5boiIaAyB0LeqgWjuUZjiHV5qClkb13uf1s4NxCyQmmhrTPFMSpHHEGgMIfHP+6MuLmF/2X1EV8XzaTUIMLG1HX2D+FTNooZ9Syd/Tvsr3Yom/zdsTbfj1dRu//Kr3gMAxHprA7HqAKUAAAAASUVORK5CYII=");
        b64map.put("Slayer Master", "FRgAAARNJREFUeNqkUz1OwzAU/p6TMzB2yikqtaFBYogEZ6g7cI2212Cgd6ASAxIOLJwiQ5SxZ8B5DOkLfnEy8RbLzvf38mxiZoyLyESHzB2Nz9IpUr5ezQqGImn4UUjvX0/48bUil8UfTgTMFBEA0iRT5DfXs/P1akhhQoAQ50oEpAyR4apyETCM7bmF53bYn04vIDKsnO/z52EtCwfPLcrC4eGuvvatTaLYoSMn3wp8/sjmRxX25rkF44LXz8s1e4aEFgDqaWeJLfV4ewPyS5BfTsdm7mizKXA4HhWgd+nXhBZD31trYe0OzB0p57GAjEb2W2v1rZO7LYOvKofDfh/9ByGKqyKPBZqmUWRrd9EDof+8qt8BAH0zidAuyWitAAAAAElFTkSuQmCC");
        b64map.put("Spice Trader", "FRgAAAPZJREFUeNqkk7EOwVAUhv9zkYitA4uI6ItQYqjBZjJ4C2tr8iSm7gxC36QiYqnBJpImPQbatPfeNsJZ2tzb7/tv7jklZoZcREJZZI5JXqvqIN8/FgqzEkqSiQTrILksa5gKiJm14Nkbpe/d2UErEDr72RthGsVYDCqYRnFOlC0hpyZg8Jjjvo9gjGuKwPePIBKcS3YdJwXNxkYRbHe7fLJ8lLWxTMGsYG0sUdoqAKjXvPeHBgBknp89wC6GL9c+Om1Pe0HPaJbvvdwq13HQM000Wy0Fnth2eavc1QqnIMAtDHELQwVUkv+asG9m27KGKJztX/6q1wDHEJiNPmDE3gAAAABJRU5ErkJggg==");
        b64map.put("Spinning Wheel", "FRgAAAXZJREFUeNqckzFIQlEUhv/7KggtaGt9kxDtDS3xBAWXwEUil162PKcGkQIHh4aINziELoUtirQILoKQd2tod7HlLDmGIYkunpbewefThu5yL5f7/f9/7zlXMTMWh1JGYJN5phb31pdBWndXCs6LKM9ZKYPnoV69IOv90xtZW1ZUBBQz+8BOJQsAiDtl9OoFhA8v8P364BPxBIz5aM/FEwBAJJEHEeHj61OgwXTiSwMAxrzrxtYaIok8+u07mKYphzqVLGK2i8F0AiKC1l0oZbA4P14eI5mrSeROJYu4U0a/fYe4UwYRIWa7coXAa3uxX4ZjZEotEfDeoQ9AbYYQJgrCB2e3PqFUsYGmm0YyVwP9Au/63ldB1lqz1pqdo90/5+vUHmutuVqtMqDYYJ4py4oCAEbDsTgSEUbDMYgIqWJDohMRbPs8WKpMqSWRTdPE9k4IANB001JCf+YlHUZEeHu6kkNeEgDi6oOX9bYHeNDK3v7Pr/oZAEki5UOlVr4QAAAAAElFTkSuQmCC");
        b64map.put("Staff Shop", "FRgAAALhJREFUeNqcUzkSgzAMXGloU6bnKybfSMU7mLR5BS3PgEcw9K7CE6BCqZSx8YEnW8qz2pW1IhHBGUQcFEUOOteqGGmaxmRDtwmpMhFLjHSGMc2vAZcQl6HD3LdQV+qCr5SWocOxfXB7vGCt9d641C4A1HXtqXOpagxVijj3LfZ1w/359lQ92ylFAFli0YeliADAIgcZ0wRz7ut2uetAWYlqOYdownSfMctuwsg9jFy2dbRotv+5qu8Al0Z01TOrckgAAAAASUVORK5CYII=");
        b64map.put("Sword Shop", "FRgAAAPBJREFUeNqskz0KwkAQhd8EbyHI3sAjhA3WFoJnSZPSxrOkEkSsEiKKVQTBSggEwUIkndlC0LXKkJ+NijjVMvC9N7MzQ1pr1IPIaiS1flI91zFBYRi0CpZFqHAmsrQJqoeUDgtY34L9XhfZ9YIwDLgK65NTcozR73URxDvMF8tqKwC1uibHGKPBkEHbtgEAQghI6VQ/zATOVhs87hnnhRD8rpTter4RXG8P7FqORs+u57eCZdcGPJ2MAQD70/kjyHMuRhVFEXKlEG1uLJamaQMsZl1xzpWCynOuwPV8o+NfNozKh/Fut6V00Lrbv1zVawB6CJwOSeFSZwAAAABJRU5ErkJggg==");
        b64map.put("Tannery", "FRgAAARVJREFUeNqcUy1vAkEUnLctqgg4QwLmMFVNGn7CnmhS2QTRBIkCTdAYXEVV+QU1NU0qEeU3NKls0lMkmAMSVkHvVe1mv0pIn3yzM2/e7iwxM/wiEkGTuSS/dx4jLRbvfwraIqQnEwmOkfySMjMCxMwBcT4b4uNziaRS4lC9QLtVx83gKRAQvvJ8NgQAXF81UewF2q2603dWAchMfZnco9ZIjtrWDqTMYCa/PvRQayRQaovNqghISm0DB4Z8N3oGAHS6U6wPZA5rYqc7BQBc3o6R57lLti35td/9OHiapiHZBuyqVM+iuGAuScrMTc5OnfTWIgb2H9/w9a2wWRXOvsFTHUuYvhjbbpCwU7KtV4tm+z+/6ncAaPiLMKAAT2cAAAAASUVORK5CYII=");
        b64map.put("Tavern", "FRgAAARpJREFUeNqck79Lw0AUxz93qFgR1KkuYhAD3XRzqnAR5/4NgmPH/hni5CiIs+DoIuQmi50VESuSOhS66VBslpxTLrmkEds3vuP74733PWGMoVhCyFLTmEQUewvTQFqHlYR5EpEqCyGN1iGHzSbfgw8ApHil1vuiu7llSZQKLIEsAgHWtncASEyDn4N1Gqu3Fqx1aF04tj9v9ljeDZi8Z7b7vSXexisM4y7HJ2fOKDJVrap4fwMAX7W5v+o46pIZK4qiTPm/oL6+KPVmUvZVez5wCvQ8LwMbkwilArvZ+tOdfXyO6wweYobxxAGmt3ZOtdg65zqKGIeXADy+jDg67eBXuCklrLjRotV8wkT+Y/yV7XS0qdme51f9DgD3Q4I6oKDz9wAAAABJRU5ErkJggg==");
        b64map.put("Transportation", "FRgAAAQRJREFUeNqkk69uwzAQxr+zwqpq3C0ICW7ppOYB8ggDBQmI+hJDe4kqtGB8pCwhlUrHQwxa8ypKYW5gsuP81aQdsuz7ffedfEfMjH4QicElc0P9O28MKop8UtAV8dzHMWj9uQcAlOkKt7cTiAQbATEHmjgsIytUFLl1IfCHqLXuCFi3AHGZrmbhfbUBACykxLE6AwCC7N72fFhGqLUehRdSotb614GMrIBwrc3ZHjvbyo/vchJ+2Qbo5IU9+Ct8zvTcgm6ex9yQ+Sql1ADcXd+tKwMG2R3MDXUmzPf9Ydlr19nl9QPIkta2qT41mi4Yx4kdUXIXY0pAKYU4TgYLQv/Zqp8BAMUvi9644z2BAAAAAElFTkSuQmCC");
        b64map.put("Water Source", "FRgAAAN9JREFUeNqcUysOwkAQfbPlEMhKHBbJ1iLQKILkDqSCSyAbEi6AQ7GYBnxDQmraS1S2iyC7afcH4anNzLx5bzKzJKWECSJmBaXsyIyNXCQhrt6G/SaklImYNEnnxwQAsJy9dIzzRDdgIWJRRZiOL6jrWseFuGoXDB4UVaTfcRw7a1jIrg9KneELTveFN2eR1az7zcd2XjaDmYNkAJqosD3OfyMXVYQ0a61ClzqTsiPOEyuRZi3ysnEqql1byrvVE3nZDIiH9c3ZxHthpk216/6FUf9jhG5bjea87X9+1XsAjdZ5cPHaHfkAAAAASUVORK5CYII=");
        b64map.put("Windmill", "FRgAAAOhJREFUeNqcU7ENwkAMPL/SgFggXSQmeRoEE1CBEtGxACOwAFQ0MAR08SrpskBaYgr01uf5B4Qbv/yyz76zSUQQGpF5C4r0FMayWBJznSzoFyGHTGTET1oetujuLSaLHLf9WePWzrSAiSUCQHdv1TdNo3HmWrswSNhkkQ98zEwMNUQOzUMnYWYZzafy6MfqU+/RfCrMLACJSSGFbcc4MKkZvxUDAHq1/ZrZZ3W92en7ejm+zV2WFTKRnhxpRVEM2HY6n1YY/Dmtk1J9Ylulcitn7ewnnf0NI/8wwt32OSjLCsnd/ueqngMAji6qFI+I3Y8AAAAASUVORK5CYII=");

        for(String s : b64map.keySet()){
            POIImg.put(s, ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(prefix + b64map.get(s)))));
            POIs.put(s, new POI(POIImg.get(s), s));
            POIPoints.put(s, new ArrayList<Point>());
        }
        
        pixmatch = new boolean[15][15];
        
        for(int x = 0; x < 15; x++){
            for(int y = 0; y < 15; y++){
                pixmatch[x][y] = true;
            }
        }
        
        BufferedImage basec = POIImg.get("Bank");
        int avoid = basec.getRGB(0, 0);
        
        for(String s : b64map.keySet()){
            BufferedImage ref = POIImg.get(s);
//            System.out.println(ref.getWidth());
//            System.out.println(ref.getHeight());
            for(int x = 0; x < 15; x++){
                for(int y = 0; y < 15; y++){
                    int rgb = ref.getRGB(x, y);
                    boolean safe = false;
//                    if(y == 13 && (x == 10 || x == 11)){
//                        System.out.println(pixmatch[x][y]);
//                    }
                    if(pixmatch[x][y]){
                        if(rgb == avoid) {pixmatch[x][y] = false;System.out.println("--"+x+","+y);}
                        else if(rgb != basec.getRGB(x, y)) {pixmatch[x][y] = false;System.out.println("--"+x+","+y);}
                        else {
                            safe = true;
                        }
                    }
                    if(!pixmatch[x][y]){
                        if(y == 13 && (x == 10 || x == 11)){
                            System.out.println(s);
                            System.exit(0);
                        }
                    }
                }
            }
        }
        
        /*
            // "perfect" map
            000001111100000
            000111111111000
            001111000111100
            011111000000110
            011000000000010
            110000000000001
            100000000000001
            100000000000001
            100000000000001
            110000000000001
            010000000000010
            011000000000010
            001111000001100
            000111100111000
            000001111100000
        
        */
        
        for(int t = 0; t < 15; t++){
            if(t>9){
                System.out.print(" " + t);
            }else{
                System.out.print("  " + t);
            }
        }
        System.out.println("\n---------------------------------------------");
        
        for(int x = 0; x < 15; x++){
            for(int y = 0; y < 15; y++){
                if(pixmatch[x][y]){
                    universalPixelMatches.add(new Pixel(x, y, basec.getRGB(x,y)));
                }
                System.out.print(pixmatch[x][y]?"  1":"  0");
            }
            System.out.println("| " + x);
        }
        
        for(String s : b64map.keySet()){
            POIs.get(s).generateComparator();
        }
    
    }
}
