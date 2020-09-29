package com.wts;
// maven依赖
//<dependency>
//    <groupId>com.itextpdf</groupId>
//    <artifactId>itext7-core</artifactId>
//    <version>7.1.12</version>
//    <type>pom</type>
//</dependency>
//<dependency>
//    <groupId>com.itextpdf</groupId>
//    <artifactId>itextpdf</artifactId>
//    <version>5.5.13.2</version>
//</dependency>
//<dependency>
//    <groupId>com.itextpdf</groupId>
//    <artifactId>itext-asian</artifactId>
//    <version>5.2.0</version>
//</dependency>
//<dependency>
//    <groupId>org.bouncycastle</groupId>
//    <artifactId>bcprov-jdk15on</artifactId>
//    <version>1.54</version>
//</dependency>

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class mdUtil {
    /**
     * 创建子目录
     */
    public static PdfOutline createOutline(PdfOutline outline, PdfDocument pdf, String title, String name) {
        if (outline == null) {
            outline = pdf.getOutlines(false);
            outline = outline.addOutline(title);
            outline.addDestination(
                    PdfDestination.makeDestination(new PdfString(name)));
            return outline;
        }
        PdfOutline kid = outline.addOutline(title);
        kid.addDestination(PdfDestination.makeDestination(new PdfString(name)));
        return kid;
    }

    /**
     * 将单个MD文件变更为PDF文件
     */
    public static void Md2Pdf(String MdFilePath, String PdfFilePath, String policyName) throws IOException {
        String simhei = "C:/Windows/Fonts/simhei.ttf";//黑体
        String simfang = "C:/Windows/Fonts/simfang.ttf";//仿宋
        String simkai = "C:/Windows/Fonts/simfang.ttf";//楷体
        String simsun = "C:/Windows/Fonts/simsun.ttf";//宋体
        PdfDocument pdf = new PdfDocument(new PdfWriter(PdfFilePath));
        Document document = new Document(pdf);
        PdfFont titleFont = PdfFontFactory.createFont(simhei, PdfEncodings.IDENTITY_H, true);
        PdfFont font = PdfFontFactory.createFont(simfang, PdfEncodings.IDENTITY_H, true);

        BufferedReader br = new BufferedReader(new FileReader(MdFilePath));
        String name, line;
        Paragraph p;
        boolean title = true;
        int counter = 0;
        PdfOutline outline1 = null;
        PdfOutline outline2 = null;
        PdfOutline outline3 = null;
        PdfOutline outline4 = null;
        PdfOutline outline5 = null;
        PdfOutline outline6 = null;
        while ((line = br.readLine()) != null) {
            if (!line.equals("")) {
                if (line.startsWith(policyName)) {
                    p = new Paragraph(line);
                    name = String.format("title%02d", counter++);
                    outline1 = createOutline(outline1, pdf, line, name);
                    p.setKeepTogether(true)
                            .setFont(titleFont)
                            .setFontSize(26)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setTextAlignment(TextAlignment.CENTER)
                            .setKeepWithNext(true)
                            .setDestination(name);
                } else if (line.startsWith("# 基本信息") || line.startsWith("# 法律修订") || line.startsWith("# 正文") || line.startsWith("# 附")) {
                    p = new Paragraph(line.replace("# ", ""));
                    name = String.format("title%02d", counter++);
                    outline2 = createOutline(outline1, pdf, line.replace("# ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(18)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setKeepWithNext(true)
                            .setDestination(name);
                } else if (line.startsWith("* 发文字号") || line.startsWith("* 效力级别") || line.startsWith("* 文件时效")
                        || line.startsWith("* 发布日期") || line.startsWith("* 实施日期") || line.startsWith("* 发布机关") || line.startsWith("* 文件来源")) {
                    p = new Paragraph(line.replace("* ", ""));
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setFirstLineIndent(24);
                } else if (line.startsWith("* ")) {
                    p = new Paragraph(line.replace("* ", ""));
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setFirstLineIndent(24);
                } else if (line.startsWith("## ")) {
                    p = new Paragraph(line.replace("## ", ""));
                    name = String.format("title%02d", counter++);
                    outline3 = createOutline(outline2, pdf, line.replace("## ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setMarginBottom(12)
                            .setDestination(name);
                } else if (line.startsWith("### ")) {
                    p = new Paragraph(line.replace("### ", ""));
                    name = String.format("title%02d", counter++);
                    outline4 = createOutline(outline3, pdf, line.replace("### ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setDestination(name);
                } else if (line.startsWith("#### ")) {
                    p = new Paragraph(line.replace("#### ", ""));
                    name = String.format("title%02d", counter++);
                    outline5 = createOutline(outline4, pdf, line.replace("#### ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setDestination(name);
                } else if (line.startsWith("##### ")) {
                    p = new Paragraph(line.replace("##### ", ""));
                    name = String.format("title%02d", counter++);
                    outline6 = createOutline(outline5, pdf, line.replace("##### ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setDestination(name);
                } else if (line.startsWith("&#8195;&#8195;")) {
                    p = new Paragraph(line.replace("&#8195;&#8195;", ""));
                    p.setKeepTogether(true)
                            .setFont(font)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setFirstLineIndent(24);
                } else {
                    p = new Paragraph(line);
                    p.setKeepTogether(true).setFont(font);

                }
                document.add(p);
            }
        }

        document.close();
        System.out.println("转换完成：" + MdFilePath + "--->" + PdfFilePath);

    }


    public static void main(String[] args) throws IOException {
        Md2Pdf("D:/中华人民共和国劳动法.md", "D:/中华人民共和国劳动法.pdf", "中华人民共和国劳动法");
    }


}
