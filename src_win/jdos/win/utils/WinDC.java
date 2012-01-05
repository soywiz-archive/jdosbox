package jdos.win.utils;

import jdos.hardware.Memory;
import jdos.win.Win;
import jdos.win.builtin.WinAPI;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;

public class WinDC extends WinObject {
    WinBitmap bitmap;

    int address;
    int width;
    int height;
    int bpp;
    int bkColor = 0;
    int textColor = 0xFFFFFF;
    int[] palette;
    boolean addressOwner = false;
    int hPalette = 0;

    public WinDC(int handle, int bpp, int address, int width, int height, int[] palette) {
        super(handle);
        this.bpp = bpp;
        this.address = address;
        this.width = width;
        this.height = height;
        this.palette = palette;
    }

    public int setBkColor(int color) {
        int oldColor = Pixel.BGRtoRGB(bkColor);
        bkColor = Pixel.BGRtoRGB(color);
        return oldColor;
    }

    public int setTextColor(int color) {
        int oldColor = Pixel.BGRtoRGB(textColor);
        textColor = Pixel.BGRtoRGB(color);
        return oldColor;
    }

    public int gtTextExtent(String text, int lpSize) {
        // :TODO: this is expensive, need to investigate caching this
        BufferedImage bi = Pixel.createImage(address, bpp, palette, width, height);
        Graphics2D g = (Graphics2D)bi.getGraphics();
        FontRenderContext frc = g.getFontRenderContext();
        Font font = g.getFont().deriveFont(16f);
        g.setFont(font);
        int sw = (int)font.getStringBounds(text, frc).getWidth();
        LineMetrics lm = font.getLineMetrics(text, frc);
        int sh = (int)(lm.getAscent() + lm.getDescent());
        Memory.mem_writed(lpSize, sw);
        Memory.mem_writed(lpSize+4, sh);
        return WinAPI.TRUE;
    }

    public int textOut(int x, int y, String text) {
        // :TODO: this is expensive, need to investigate caching this
        BufferedImage bi = Pixel.createImage(address, bpp, palette, width, height);
        Graphics2D g = (Graphics2D)bi.getGraphics();

        FontRenderContext frc = g.getFontRenderContext();
        Font font = g.getFont().deriveFont(16f);
        g.setFont(font);
        int sw = (int)font.getStringBounds(text, frc).getWidth();
        LineMetrics lm = font.getLineMetrics(text, frc);
        int sh = (int)(lm.getAscent() + lm.getDescent());

        g.setColor(new Color(bkColor));
        g.fillRect(x, y, sw, sh);
        g.setColor(new Color(textColor));
        g.drawString(text, x, y+sh-(int)lm.getDescent());
        Pixel.writeImage(address, bi, bpp, width, height);
        return WinAPI.TRUE;
    }

    protected void onFree() {
        if (address != 0 && addressOwner) {
            WinSystem.getCurrentProcess().heap.free(address);
        }
        super.onFree();
    }

    public BufferedImage getImage() {
        return Pixel.createImage(address, bpp, palette, width, height);
    }

    public void writeImage(BufferedImage image) {
        Pixel.writeImage(address, image, bpp, width, height);
    }

    public int selectPalette(WinPalette palette, boolean bForceBackground) {
        int oldPalette = hPalette;
        hPalette = palette.getHandle();
        return oldPalette;
    }

    public int realizePalette() {
        if (hPalette != 0) {
            WinObject object = WinSystem.getObject(hPalette);
            if (object instanceof WinPalette) {
                return ((WinPalette)object).palette.length;
            }
        }
        return 0;
    }
    public int select(WinGDI gdi) {
        WinGDI old = null;

        if (gdi instanceof WinBitmap) {
            if (address != 0 && !addressOwner) {
                Win.panic("Tried to select a bitmap into a dc that is already backed by video memory");
            }
            old = bitmap;
            bitmap = (WinBitmap)gdi;
            width = bitmap.width;
            height = bitmap.height;
            if (address != 0)
                WinSystem.getCurrentProcess().heap.free(address);
            address = bitmap.createCompatibleCopy(bpp, palette);
            addressOwner = true;
        } else {
            Win.panic("WinDC.select was not implemented for "+gdi);
        }
        if (old != null)
            return old.handle;
        return 0;
    }
}