package com.jutils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.twelvemonkeys.imageio.plugins.psd.PSDImageReader;
import com.twelvemonkeys.imageio.plugins.psd.PSDImageReaderSpi;
import com.twelvemonkeys.imageio.stream.URLImageInputStreamSpi;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhuyh
 * @date: 2018/8/17
 */
public class ImagePSDTest{
    static {
        IIORegistry.getDefaultInstance().registerServiceProvider(new URLImageInputStreamSpi());
        ImageIO.setUseCache(false);
    }
    public static void main(String[] args) throws IOException {


        // Create input stream
        ImageInputStream input = null;
        try {
            input = ImageIO.createImageInputStream(ImagePSDTest.class.getResource("/image/56.psd"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Get the reader
            Iterator<ImageReader> readers = ImageIO.getImageReaders(input);

            if (!readers.hasNext()) {
                throw new IllegalArgumentException("No reader for: " );
            }

            ImageReader reader = readers.next();

            try {
                reader.setInput(input);

                // Optionally, listen for read warnings, progress, etc.

                ImageReadParam param = reader.getDefaultReadParam();

                // Optionally, control read settings like sub sampling, source region or destination etc.
//                param.setSourceSubsampling(...);
//                param.setSourceRegion(...);
//                param.setDestination(...);
                // ...

                // Finally read the image, using settings from param
                BufferedImage image = reader.read(3, param);
                ImageIO.write(image, "png", new FileOutputStream("56-1.png"));


                // Optionally, read thumbnails, meta data, etc...
                int numThumbs = reader.getNumImages(false);
                System.out.println("numThumbs:"+numThumbs);
                // ...
            }
            finally {
                // Dispose reader in finally block to avoid memory leaks
                reader.dispose();
            }
        }
        finally {
            // Close stream in finally block to avoid resource leaks
            input.close();
        }


    }
}