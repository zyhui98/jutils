package com.jutils;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.twelvemonkeys.imageio.plugins.psd.PSDImageReader;
import com.twelvemonkeys.imageio.plugins.psd.PSDImageReaderSpi;
import com.twelvemonkeys.imageio.plugins.psd.PSDMetadata;
import com.twelvemonkeys.imageio.stream.URLImageInputStreamSpi;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
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
public class ImagePSDTest extends ImageReaderAbstractTest<PSDImageReader> {
    private static final PSDImageReaderSpi provider = new PSDImageReaderSpi();

    @Test
    public void test() throws IOException {

        PSDImageReader imageReader = createReader();

        try (ImageInputStream stream = ImageIO.createImageInputStream(getClassLoaderResource("/弹窗.psd"))) {
            imageReader.setInput(stream);

            IIOMetadata metadata = imageReader.getImageMetadata(0);
            IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(PSDMetadata.NATIVE_METADATA_FORMAT_NAME);
            NodeList layerInfo = root.getElementsByTagName("LayerInfo");
            for(int i=0;i<layerInfo.getLength();i++){
                System.out.print("|" + ((IIOMetadataNode) layerInfo.item(i)).getAttribute("name"));
                System.out.print("|" + ((IIOMetadataNode) layerInfo.item(i)).getAttribute("top"));
                System.out.print("|" + ((IIOMetadataNode) layerInfo.item(i)).getAttribute("left"));
                System.out.print("|" + ((IIOMetadataNode) layerInfo.item(i)).getAttribute("bottom"));
                System.out.println();

            }
            assertEquals(4, layerInfo.getLength()); // Sanity

        }
    }

    @Override
    protected List<TestData> getTestData() {
        return null;
    }

    @Override
    protected ImageReaderSpi createProvider() {
        return null;
    }

    @Override
    protected Class<PSDImageReader> getReaderClass() {
        return PSDImageReader.class;
    }

    @Override
    protected PSDImageReader createReader() {
        try {
           return (PSDImageReader)provider.createReaderInstance(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<String> getFormatNames() {
        return null;
    }

    @Override
    protected List<String> getSuffixes() {
        return null;
    }

    @Override
    protected List<String> getMIMETypes() {
        return null;
    }
}
