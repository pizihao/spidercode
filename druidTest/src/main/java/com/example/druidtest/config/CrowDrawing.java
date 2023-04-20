package com.example.druidtest.config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CrowDrawing {

    public static void main(String[] args) throws IOException, FontFormatException {
        C();
    }

    public static void A() {
        System.out.println("\033[31m" // 设置字体颜色为红色
                + "      ___       ___           ___           ___           ___           ___     \n"
                + "     /\\  \\     /\\__\\         /\\  \\         /\\__\\         /\\__\\         /\\  \\    \n"
                + "    /::\\  \\   /:/  /        /::\\  \\       /::|  |       /::|  |       /::\\  \\   \n"
                + "   /:/\\:\\__\\ /:/__/        /:/\\:\\  \\     /:|:|  |      /:|:|  |      /:/\\:\\__\\  \n"
                + "  /:/ /:/  / \\:\\  \\       /:/  \\:\\  \\   /:/|:|  |__   /:/|:|  |__   /:/ /:/  /  \n"
                + " /:/_/:/  /   \\:\\__\\     /:/__/ \\:\\__\\ /:/ |:| /\\__\\ /:/ |:| /\\__\\ /:/_/:/__/___\n"
                + " \\:\\/:/  /     \\/__/     \\:\\  \\ /:/  / \\/_|:|/:/  / \\/_|:|/:/  / \\:\\/:/  /\\__\\\n"
                + "  \\::/__/                 \\:\\  /:/  /     |:/:/  /     |:/:/  /   \\::/__/     \n"
                + "   \\:\\  \\                  \\:\\/:/  /      |::/  /      |::/  /     \\:\\  \\     \n"
                + "    \\:\\__\\                  \\::/  /       /:/  /       /:/  /       \\:\\__\\    \n"
                + "     \\/__/                   \\/__/        \\/__/        \\/__/         \\/__/    "
                + "\033[0m"); // 恢复控制台默认字体颜色
    }

    public void B() throws IOException {
        // 画布大小
        int width = 600;
        int height = 600;

        // 创建一个黑色背景的 BufferedImage
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // 设置字体
        Font font = new Font("宋体", Font.BOLD, 300);
        g.setFont(font);

        // 设置颜色为白色
        g.setColor(Color.WHITE);

        // 写字
        String crow = "CROW";
        int x = 50;
        int y = 400;
        g.drawString(crow, x, y);

        // 写声调
        String calligraphy = "CROW";
        font = new Font("宋体", Font.BOLD, 50);
        g.setFont(font);
        y = 460;
        x = (width - g.getFontMetrics().stringWidth(calligraphy)) / 2;
        g.drawString(calligraphy, x, y);

        // 保存为 PNG 文件
        ImageIO.write(image, "PNG", new File("D:\\桌面\\crow_calligraphy.png"));
    }


    public static void C() throws IOException, FontFormatException {
        // 定义Crow字体
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Crow.ttf"));
        Font baseFont = font.deriveFont(Font.PLAIN, 100);
        // 获得屏幕打印上下文
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable((g, pf, pageIndex) -> {
            // 获得打印区域
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());

            // 计算CROW字符串大小
            FontMetrics metrics = g2d.getFontMetrics(baseFont);
            int width = metrics.stringWidth("CROW");
            int height = metrics.getHeight();

            // 绘制CROW字符串
            g2d.setFont(baseFont);
            g2d.drawString("CROW", (width/2) , height*2);
            return Printable.PAGE_EXISTS;
        });
        // 打印输出
        try {
            job.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
