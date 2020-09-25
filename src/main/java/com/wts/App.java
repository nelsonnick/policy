package com.wts;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class App {
    public static void main(String[] args) throws Exception {
        // 新建document对象
        Document document = new Document();

        // 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/test.pdf"));

        // 打开文档
        document.open();
        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);


//        字体设置
//        参数一：新建好的字体；参数二：字体大小，参数三：字体样式，多个样式用“|”分隔
//        Font topfont = new Font(bfChinese,14,Font.BOLD);
//        Font textfont =new Font(bfChinese,10,,Font.BOLD|Font.UNDERLINE);
        //黑色字体
        Font blackFont = new Font(bfChinese,20,Font.BOLD);
        blackFont.setColor(BaseColor.BLACK);
        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.GREEN);
        //蓝色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLUE);
        //红色字体
        Font redFont = new Font(bfChinese);
        redFont.setColor(BaseColor.RED);
        //橙色字体
        Font orangeFont = new Font(bfChinese);
        orangeFont.setColor(BaseColor.ORANGE);
        //青色字体
        Font cyanFont = new Font(bfChinese);
        cyanFont.setColor(BaseColor.CYAN);
        //黄色字体
        Font yellowFont = new Font(bfChinese);
        yellowFont.setColor(BaseColor.YELLOW);
        //粉色字体
        Font pinkFont = new Font(bfChinese);
        pinkFont.setColor(BaseColor.PINK);
        //灰色字体
        Font grayFont = new Font(bfChinese);
        grayFont.setColor(BaseColor.GRAY);
        //深灰字体
        Font dark_grayFont = new Font(bfChinese);
        dark_grayFont.setColor(BaseColor.DARK_GRAY);
        //浅灰字体
        Font light_grayFont = new Font(bfChinese);
        light_grayFont.setColor(BaseColor.LIGHT_GRAY);
        //紫色字体
        Font magentaFont = new Font(bfChinese);
        magentaFont.setColor(BaseColor.MAGENTA);
        //白色字体
        Font whiteFont = new Font(bfChinese);
        whiteFont.setColor(BaseColor.WHITE);



        // 添加一个内容段落
        document.add(new Paragraph("这是和是你们", blackFont));

        document.newPage();
        document.add(new Paragraph("你好", blueFont));
        //设置属性
        //标题
        document.addTitle("人社政策库");
        //作者
        document.addAuthor("速读人社");
        //主题
        document.addSubject("政策速查");
        //关键字
        document.addKeywords("人社");
        //创建时间
        document.addCreationDate();
        //应用程序
        document.addCreator("");
        // 关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
