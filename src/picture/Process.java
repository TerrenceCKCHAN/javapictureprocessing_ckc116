package picture;

import picture.Color;
import picture.Picture;
import picture.Utils;

/**
 * Created by admin on 16/1/2017.
 */
public class Process {
    private Picture pic;

    public Process(Picture picture) {
        this.pic = picture;
    }
    public Picture getPic() {
        return pic;
    }
    public void invert() {
        int width  = pic.getWidth();
        int height = pic.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixelColor = pic.getPixel(x, y);
                int newred   = 255 - pixelColor.getBlue();
                int newgreen = 255 - pixelColor.getGreen();
                int newblue  = 255 - pixelColor.getRed();
                Color newpixelColor = new Color(newred, newgreen, newblue);
                pic.setPixel(x, y, newpixelColor);
            }
        }
    }
    public void grayscale() {
        int width  = pic.getWidth();
        int height = pic.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixelColor = pic.getPixel(x, y);
                int avg = (pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue()) / 3;
                Color newpixelColor = new Color(avg, avg, avg);
                pic.setPixel(x, y, newpixelColor);
            }
        }
    }



    public void rotate(String angle) {
        int width = pic.getWidth();
        int height = pic.getHeight();
        switch (angle) {
            case "90":
                Picture newPic = Utils.createPicture(height, width);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color pixelcolor = pic.getPixel(x, y);
                        newPic.setPixel(height - y - 1, x, pixelcolor);
                    }
                }
                pic = newPic;
                break;
            case "180":
                Picture newPic1 = Utils.createPicture(width, height);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color pixelcolor = pic.getPixel(x, y);
                        newPic1.setPixel(width - x - 1, height - y - 1, pixelcolor);
                    }
                }
                pic = newPic1;
                break;
            case "270":
                Picture newPic2 = Utils.createPicture(height, width);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color pixelcolor = pic.getPixel(x, y);
                        newPic2.setPixel(y , width - x - 1, pixelcolor);
                    }
                }
                pic = newPic2;
                break;
        }
    }


    public void flip(String orientation) {
        int width = pic.getWidth();
        int height = pic.getHeight();
        Picture newPic = Utils.createPicture(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixelcolor = pic.getPixel(x, y);
                switch (orientation) {
                    case "H":
                        newPic.setPixel(width - x - 1, y, pixelcolor);
                        break;
                    case "V":
                        newPic.setPixel(x, height - y - 1, pixelcolor);
                        break;

                }
            }
        } pic = newPic;
    }

    public void blend(Picture[] pics) {
        int width = pics[0].getWidth();
        int height = pics[0].getHeight();
        for (int i = 0; i < pics.length; i++) {
            if (pics[i].getWidth() < width) {
                width = pics[i].getWidth();
            }
        }
        for (int i = 0; i < pics.length; i++) {
            if (pics[i].getHeight() < height) {
                height = pics[i].getHeight();
            }
        }
        Picture newPic = Utils.createPicture(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixelcolor = pics[0].getPixel(x, y);
                int red = pixelcolor.getRed();
                int green = pixelcolor.getGreen();
                int blue = pixelcolor.getBlue();
                for (int i = 1; i < pics.length; i++) {
                    red = red + pics[i].getPixel(x, y).getRed();
                    green = green + pics[i].getPixel(x, y).getGreen();
                    blue = blue + pics[i].getPixel(x, y).getBlue();
                }
                red = red / pics.length;
                green = green / pics.length;
                blue = blue / pics.length;
                pixelcolor.setRed(red);
                pixelcolor.setGreen(green);
                pixelcolor.setBlue(blue);
                newPic.setPixel(x, y, pixelcolor);

            }
        } pic = newPic;
    }


    public void blur() {
        int width  = pic.getWidth();
        int height = pic.getHeight();
        Picture newPic = Utils.createPicture(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    newPic.setPixel(x, y, pic.getPixel(x, y));
                } else {
                    int red = 0;
                    int green = 0;
                    int blue = 0;
                    for (int i = x - 1; i < x + 2; i++) {
                        for (int j = y - 1; j < y + 2; j++) {
                            Color currentPix = pic.getPixel(i, j);
                            blue = blue + currentPix.getBlue();
                            green = green + currentPix.getGreen();
                            red = red + currentPix.getRed();
                        }
                    }
                    Color newColor = new Color(red / 9, green / 9, blue / 9);
                    newPic.setPixel(x, y, newColor);
                }
            }
        } pic = newPic;
    }
}





