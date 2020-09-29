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


        Paragraph title = new Paragraph("法律", blackFont);
        title.setAlignment(Element.ALIGN_CENTER);
        Chapter chapter = new Chapter(1);
        chapter.setTitle(title);
        chapter.setBookmarkTitle("法律");
        title = new Paragraph("中华人民共和国劳动法", blackFont);
        title.setAlignment(Element.ALIGN_CENTER);
        Section section = chapter.addSection(title);
        section.setBookmarkTitle("劳动法");
        section.setTitle(title);
        section.setIndentation(30);
        section.setBookmarkOpen(false);
        section.setNumberStyle(
                Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

        Section subsection = section.addSection(new Paragraph("Sub Section A"));
        subsection.setIndentationLeft(20);
        subsection.setNumberDepth(1);

        document.add(section);

//        section.setBookmarkTitle("中华人民共和国劳动法");
//        section.setIndentation(30);
//        section.setBookmarkOpen(false);
//        section.setNumberStyle(
//                Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
//        document.add(section);
////        Chapter chapter = new Chapter(title, 1);
////        document.add(chapter);
//        document.add(chapter.addSection(new Paragraph("基本信息", blackFont), 2));
//        List unorderedList = new List(List.UNORDERED);
//        unorderedList.add(new ListItem(new Paragraph("发文字号：中华人民共和国主席令第24号", blackFont)));
//        unorderedList.add(new ListItem(new Paragraph("效力级别：法律", blackFont)));
//        unorderedList.add(new ListItem(new Paragraph("文件时效：现行有效", blackFont)));
//        unorderedList.add(new ListItem(new Paragraph("发布日期：2018-12-29", blackFont)));
//        unorderedList.add(new ListItem(new Paragraph("实施日期：2018-12-29", blackFont)));
//        unorderedList.add(new ListItem(new Paragraph("发布机关：全国人大常委会", blackFont)));
//        document.add(unorderedList);


        document.newPage();

//        title = new Paragraph("Section A");
//        Section section = chapter.addSection(title);
//        section.setBookmarkTitle("bmk");
//        section.setIndentation(30);
//        section.setBookmarkOpen(false);
//        section.setNumberStyle(
//                Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
//
//        Section subsection = section.addSection(new Paragraph("Sub Section A"));
//        subsection.setIndentationLeft(20);
//        subsection.setBookmarkTitle("");
//        subsection.setNumberDepth(1);
//
//        document.add(chapter);




//        //定义段落
//        Paragraph paragraph = new Paragraph();
//        //添加段落内容
//        paragraph.add(new Phrase("中华人民共和国劳动法", blackFont));
//        //定义章
//        Chapter chapter = new Chapter(paragraph, 1);
//        //添加章节内容
//        Section section1 = chapter.addSection("基本信息", 2);
//        document.add(section1);
//        // 添加无序列表
//        List unorderedList = new List(List.UNORDERED);
//        unorderedList.add(new ListItem("Item 1"));
//        unorderedList.add(new ListItem("Item 2"));
//        unorderedList.add(new ListItem("Item 3"));
//        document.add(unorderedList);
//        // 添加一个内容段落
//        document.add(new Paragraph("文件时效", blackFont));
//
//        document.newPage();
//
//
//
//        Section section2 = chapter.addSection("发文部门", 2);
//        document.add(section2);
//        document.add(new Paragraph("你好", blueFont));

        //添加章节
//        document.add(chapter);



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
