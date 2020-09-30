package com.wts.abandon;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.BufferedReader;
import java.io.FileReader;

public class tt {
    public static final String DEST = "D:/中华人民共和国劳动法.pdf";
    public static PdfOutline createOutline(
            PdfOutline outline, PdfDocument pdf, String title, String name) {
        if (outline == null) {
            outline = pdf.getOutlines(false);
            outline = outline.addOutline(title);
            outline.addDestination(
                    PdfDestination.makeDestination(new PdfString(name)));
            return outline;
        }
        PdfOutline kid = outline.addOutline(title);
        kid.addDestination(PdfDestination.makeDestination(new PdfString(name)));
        return outline;
    }

    public static void main(String[] args) throws Exception{
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdf);
        PdfFont titleFont = PdfFontFactory.createFont("C:/Windows/Fonts/黑体.ttf", PdfEncodings.IDENTITY_H,false);
        PdfFont subFont = PdfFontFactory.createFont("C:/Windows/Fonts/黑体.ttf", PdfEncodings.IDENTITY_H,true);
        PdfFont listFont = PdfFontFactory.createFont("C:/Windows/Fonts/simsun.ttc,1", PdfEncodings.IDENTITY_H,true);
        PdfFont textFont = PdfFontFactory.createFont("C:/Windows/Fonts/simsun.ttc,1", PdfEncodings.IDENTITY_H,true);

        BufferedReader br = new BufferedReader(new FileReader("D:/中华人民共和国劳动法.txt"));
        String name, line;
        Paragraph p;
        boolean title = true;
        int counter = 0;
        PdfOutline outline = null;
        while ((line = br.readLine()) != null) {
            p = new Paragraph(line);
            p.setKeepTogether(true);
            if (title) {
                name = String.format("title%02d", counter++);
                outline = createOutline(outline, pdf, line, name);
                p.setFont(titleFont)
                        .setFontSize(12)
                        .setKeepWithNext(true)
                        .setDestination(name);
                title = false;
                document.add(p);
            } else {
                p.setFirstLineIndent(36);
                if (line.isEmpty()) {
                    p.setFont(textFont).setMarginBottom(12);
                    title = true;
                } else {
                    p.setFont(textFont).setMarginBottom(0);
                }
                document.add(p);
            }
        }
        document.close();
        System.out.println("Awesome PDF just got created.");
    }
}
