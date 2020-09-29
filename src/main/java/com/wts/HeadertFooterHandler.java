package com.wts;

import com.itextpdf.io.font.PdfEncodings;
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
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.IOException;

public class HeadertFooterHandler implements IEventHandler {
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
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        PdfDocument pdfDoc = ((PdfDocumentEvent) event).getDocument();
        String simkai = "C:/Windows/Fonts/simfang.ttf";//楷体
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(simkai, PdfEncodings.IDENTITY_H, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
        new Canvas(pdfCanvas, pdfDoc, pageSize).setFont(font).setFontSize(10)
                //header
                .showTextAligned("", 70, pageSize.getTop() - 20, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0)
                .showTextAligned("速读人社  普法先行", pageSize.getWidth() / 2, pageSize.getTop() - 20, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0)
                //footer
                .showTextAligned("第" + Integer.toString(pdfDoc.getPageNumber(page)) + "页", pageSize.getWidth() / 2, 30, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
//                .showTextAligned(info, pageSize.getWidth() - 60, 30, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
    }
}
