package com.jutils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.twelvemonkeys.imageio.stream.URLImageInputStreamSpi;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.spi.IIORegistry;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhuyh
 * @date: 2018/8/17
 */
public class ImagePSDWriteTest {
    static {
        IIORegistry.getDefaultInstance().registerServiceProvider(new URLImageInputStreamSpi());
        ImageIO.setUseCache(false);
    }
    public static void main(String[] args) throws IOException {


        String format = "png";

        // Get the writer
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);

        if (!writers.hasNext()) {
            throw new IllegalArgumentException("No writer for: " + format);
        }

        ImageWriter writer = writers.next();

        try {
            URL url =ImagePSDTest.class.getResource("/image/56.psd");
            File file = new File(url.getFile());
            // Create output stream


            ImageOutputStream output = ImageIO.createImageOutputStream(file);

            try {
                writer.setOutput(output);

                // Optionally, listen to progress, warnings, etc.

                ImageWriteParam param = writer.getDefaultWriteParam();

                // Optionally, control format specific settings of param (requires casting), or
                // control generic write settings like sub sampling, source region, output type etc.

                // Optionally, provide thumbnails and image/stream metadata
                writer.writeInsert(1,read(2),param);
                //System.out.println("b=" + b);

                //writer.write(read(1));
            }
            finally {
                // Close stream in finally block to avoid resource leaks
                output.close();
            }
        }
        finally {
            // Dispose writer in finally block to avoid memory leaks
            writer.dispose();
        }


    }

    public static IIOImage read(int index){
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

            reader.setInput(input);

            // Optionally, listen for read warnings, progress, etc.

            ImageReadParam param = reader.getDefaultReadParam();

            // Finally read the image, using settings from param
            //BufferedImage image = reader.read(index, param);
            IIOImage image = reader.readAll(index, param);
            reader.dispose();

            return image;

        }catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Close stream in finally block to avoid resource leaks
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}