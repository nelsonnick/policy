package com.wts.util;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.IOException;

public class WaterPrintHandler implements IEventHandler {
    protected String info;
    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdfDoc.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(
                page.newContentStreamBefore(), page.getResources(), pdfDoc);

        //Set background
        Color limeColor = new DeviceCmyk(0, 0, 0, 0);
        pdfCanvas.saveState()
                .setFillColor(limeColor)
                .rectangle(pageSize.getLeft(), pageSize.getBottom(),
                        pageSize.getWidth(), pageSize.getHeight())
                .fill().restoreState();
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(Parameter.kaiti, PdfEncodings.IDENTITY_H, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Add header and footer
//        pdfCanvas.beginText()
//                .setFontAndSize(font, 9)
//                .moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20)
//                .showText("THE TRUTH IS OUT THERE")
//                .moveText(60, -pageSize.getTop() + 30)
//                .showText(String.valueOf(pageNumber))
//                .endText();
        //Add watermark
        Canvas canvas = new Canvas(pdfCanvas, page.getPageSize());
        canvas.showTextAligned(
                new Paragraph(info)
                        .setFontSize(100)
                        .setFont(font)
                        .setFontColor(new DeviceRgb(255, 180, 0)),
                298, 421, pdfDoc.getPageNumber(page),
                TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
        pdfCanvas.release();
    }
}
