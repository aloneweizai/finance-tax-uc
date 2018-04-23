package com.abc.service;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by stuy on 2017/10/16.
 */
public class ImgCompress {
    private Image img;
    private int width;
    private int height;

    protected Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 构造函数
     */
    public ImgCompress(InputStream is) throws IOException {
        try {
            img = ImageIO.read(is);      // 构造Image对象
            width = img.getWidth(null);    // 得到源图宽
            height = img.getHeight(null);  // 得到源图长
        } catch (Exception e) {
            log.info("异常"+e.getMessage());
        }
    }
    /**
     * 按照宽度还是高度进行压缩
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public String resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            return resizeByWidth(w);
        } else {
            return resizeByHeight(h);
        }
    }
    /**
     * 以宽度为基准，等比例放缩图片
     * @param w int 新宽度
     */
    private String resizeByWidth(int w) throws IOException {
        int h = (int) (height * w / width);
        return resize(w, h);
    }
    /**
     * 以高度为基准，等比例缩放图片
     * @param h int 新高度
     */
    private String resizeByHeight(int h) throws IOException {
        int w = (int) (width * h / height);
        return resize(w, h);
    }
    /**
     * 强制压缩/放大图片到固定的大小
     * @param w int 新宽度
     * @param h int 新高度
     */
    private String resize(int w, int h) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        ByteArrayOutputStream  out = new ByteArrayOutputStream(); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image); // JPEG编码
        out.close();
        BASE64Encoder encoders = new BASE64Encoder();
        return encoders.encode(out.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    public static String getCompressBase64FromUrl(InputStream is, int width, int height) throws Exception {
        // Image img = ImageIO.read(is);
        //BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的优先级比速度高 生成的图片质量比较好 但速度慢
        // tag.getGraphics().drawImage(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH), 0, 0, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //ImageIO.write(tag, "jpeg", baos); // 这里也可以传 FileOutputStream 写进文件里

        Thumbnails.of(is).size(width, height).toOutputStream(baos);

        return new String(Base64.encodeBase64(baos.toByteArray()));
    }

}
