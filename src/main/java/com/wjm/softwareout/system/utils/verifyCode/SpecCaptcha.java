package com.wjm.softwareout.system.utils.verifyCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName SpecCaptcha
 * @Description TODO
 *
 * @Date 2019/07/28 14:19
 * @Version 1.0
 **/
public class SpecCaptcha extends Captcha {

    public SpecCaptcha() {
    }

    public SpecCaptcha(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public SpecCaptcha(int width, int height, int len) {
        this(width, height);
        this.len = len;
    }

    public SpecCaptcha(int width, int height, int len, Font font) {
        this(width, height, len);
        this.font = font;
    }

    /**
     * 生成验证码
     *
     * @throws java.io.IOException IO异常
     */
    @Override
    public void out(OutputStream out) {
        graphicsImage(alphas(), out);
    }

    /**
     * 画随机码图
     *
     * @param strs 文本
     * @param out  输出流
     */
    private boolean graphicsImage(char[] strs, OutputStream out) {
        boolean ok = false;
        try {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            AlphaComposite ac3;
            Color color;
            int len = strs.length;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            // 随机画干扰的蛋蛋
            for (int i = 0; i < 15; i++) {
                color = color(150, 250);
                g.setColor(color);
                /**
                 * 画圆圈
                 */
                g.drawOval(num(width), num(height), 5 + num(10), 5 + num(10));
                color = null;
            }
            g.setFont(font);
            int h = height - ((height - font.getSize()) >> 1),
                    w = width / len,
                    size = w - font.getSize() + 1;
            /* 画字符串 */
            for (int i = 0; i < len; i++) {
                /**
                 * 指定透明度
                 */
                ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
                g.setComposite(ac3);
                /**
                 * 对每个字符都用随机颜色
                 */
                color = new Color(20 + num(110), 20 + num(110), 20 + num(110));
                g.setColor(color);
                g.drawString(strs[i] + "", (width - (len - i) * w) + size, h - 4);
                color = null;
                ac3 = null;
            }
            ImageIO.write(bi, "png", out);
            out.flush();
            ok = true;
        } catch (IOException e) {
            ok = false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ok;
    }
}
