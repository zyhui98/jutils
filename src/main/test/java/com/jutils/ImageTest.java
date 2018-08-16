package com.jutils;

import org.im4java.core.*;
import org.im4java.process.ProcessStarter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhuyh
 * @date: 2018/8/16
 */
public class ImageTest {

    public static final String E_GIT_WORKSPACE_JUTILS_SRC_MAIN_TEST_RESOURCES_IMAGE = "E:\\git-workspace\\jutils\\src\\main\\test\\resources\\image\\";
    static String myPath="C:\\Program Files\\GraphicsMagick-1.3.28-Q8";

    public static void main(String[] args) {

        IMOperation op = new IMOperation();
        op.addImage(E_GIT_WORKSPACE_JUTILS_SRC_MAIN_TEST_RESOURCES_IMAGE + "2.png");     // 输入要压缩的文件路径
        op.resize(640);                  // 多番尝试后才知道这是限定width，height等比缩放
        op.addImage(E_GIT_WORKSPACE_JUTILS_SRC_MAIN_TEST_RESOURCES_IMAGE + "2-new.png");  // 压缩后的文件的输出路径，当然可以没有扩展名！

        ImageCommand cmd = getImageCommand(1);
        try {
            cmd.run(op);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }


    }

    private static ImageCommand getImageCommand(int type) {
        ImageCommand cmd = null;
        Boolean USE_GRAPHICS_MAGICK_PATH = true;
        switch (type) {
            case 1:
                cmd = new ConvertCmd(USE_GRAPHICS_MAGICK_PATH);
                break;
            case 2:
                cmd = new IdentifyCmd(USE_GRAPHICS_MAGICK_PATH);
                break;
            case 3:
                cmd = new CompositeCmd(USE_GRAPHICS_MAGICK_PATH);
                break;
        }

        if (cmd != null && System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
            cmd.setSearchPath(USE_GRAPHICS_MAGICK_PATH ? myPath : myPath);
        }

        return cmd;
    }
}
