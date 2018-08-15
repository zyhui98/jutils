package com.jutils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhuyh
 * @date: 2018/8/14
 */
public class ComposeImageTest {
    public static final String E_GIT_WORKSPACE_JUTILS_SRC_MAIN_TEST_RESOURCES_IMAGE = "E:\\git-workspace\\jutils\\src\\main\\test\\resources\\image\\";

    /**
     * 图片合成
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        int i=1;
        int j=2;
        InputStream imagein = new FileInputStream(E_GIT_WORKSPACE_JUTILS_SRC_MAIN_TEST_RESOURCES_IMAGE + "FFFFFF-gaozhimianPlus-1.jpg");
        InputStream imagein2 = new FileInputStream(E_GIT_WORKSPACE_JUTILS_SRC_MAIN_TEST_RESOURCES_IMAGE + "2.png");

        BufferedImage image = ImageIO.read(imagein);
        BufferedImage image2 = ImageIO.read(imagein2);
        Graphics g = image.getGraphics();
        g.drawImage(image2, 190, 180,
                image2.getWidth(), image2.getHeight(), null);
        OutputStream outImage = new FileOutputStream(E_GIT_WORKSPACE_JUTILS_SRC_MAIN_TEST_RESOURCES_IMAGE + "custom" + j + "-" + i + ".jpg");
        JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(outImage);
        enc.encode(image);
        imagein.close();
        imagein2.close();
        outImage.close();
        System.out.println("========================");

    }
}
