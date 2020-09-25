package com.wts;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.otf.Glyph;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.Chapter;

import java.io.*;

public class aa {
    public static final String DEST = "D:/test1.pdf";

    public static void main(String args[]) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdf);

        PdfFont titleFont = PdfFontFactory.createFont("C:/Windows/Fonts/simhei.ttf", PdfEncodings.IDENTITY_H,false);
        PdfFont subFont = PdfFontFactory.createFont("C:/Windows/Fonts/simhei.ttf", PdfEncodings.IDENTITY_H,true);
        PdfFont listFont = PdfFontFactory.createFont("C:/Windows/Fonts/simsun.ttc,1", PdfEncodings.IDENTITY_H,true);
        PdfFont textFont = PdfFontFactory.createFont("C:/Windows/Fonts/simsun.ttc,1", PdfEncodings.IDENTITY_H,true);

        document.add(new Paragraph("中华人民共和国劳动法")
                .setFont(titleFont)
                .setFontSize(26)
                .setFontColor(new DeviceRgb(0,0,0))
                .setTextAlignment(TextAlignment.CENTER)
        );


        document.add(new Paragraph("基本信息")
                .setFont(subFont)
                .setFontSize(18)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        List list1 = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(listFont);
        list1.add(new ListItem("发文字号：中华人民共和国主席令第24号"))
                .add(new ListItem("效力级别：法律"))
                .add(new ListItem("文件时效：现行有效"))
                .add(new ListItem("发布日期：2018-12-29"))
                .add(new ListItem("实施日期：2018-12-29"))
                .add(new ListItem("全国人大常委会"));
        document.add(list1);
        document.add(new Paragraph("法律修订")
                .setFont(subFont)
                .setFontSize(18)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        List list2 = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(listFont);
        list2.add(new ListItem("1994年7月5日第八届全国人民代表大会常务委员会第八次会议通过"))
                .add(new ListItem("根据2009年8月27日第十一届全国人民代表大会常务委员会第十次会议《关于修改部分法律的决定》第一次修正"))
                .add(new ListItem("根据2018年12月29日第十三届全国人民代表大会常务委员会第七次会议《关于修改〈中华人民共和国劳动法〉等七部法律的决定》第二次修正"));
        document.add(list2);
        document.add(new Paragraph("正文")
                .setFont(subFont)
                .setFontSize(18)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        document.add(new Paragraph("第一章　总则")
                .setFont(subFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        document.add(new Paragraph("第一条")
                .setFont(subFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        document.add(new Paragraph("为了保护劳动者的合法权益，调整劳动关系，建立和维护适应社会主义市场经济的劳动制度，促进经济发展和社会进步，根据宪法，制定本法。")
                .setFont(textFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
                .setFirstLineIndent(24)
        );
        document.add(new Paragraph("第二条")
                .setFont(subFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        document.add(new Paragraph("在中华人民共和国境内的企业、个体经济组织（以下统称用人单位）和与之形成劳动关系的劳动者，适用本法。")
                .setFont(textFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
                .setFirstLineIndent(24)
        );
        document.add(new Paragraph("国家机关、事业组织、社会团体和与之建立劳动合同关系的劳动者，依照本法执行。")
                .setFont(textFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
                .setFirstLineIndent(24)
        );
        document.add(new Paragraph("第三条")
                .setFont(subFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        document.add(new Paragraph("劳动者享有平等就业和选择职业的权利、取得劳动报酬的权利、休息休假的权利、获得劳动安全卫生保护的权利、接受职业技能培训的权利、享受社会保险和福利的权利、提请劳动争议处理的权利以及法律规定的其他劳动权利。")
                .setFont(textFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
                .setFirstLineIndent(24)
        );
        document.add(new Paragraph("劳动者应当完成劳动任务，提高职业技能，执行劳动安全卫生规程，遵守劳动纪律和职业道德。")
                .setFont(textFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
                .setFirstLineIndent(24)
        );
        document.add(new Paragraph("第四条")
                .setFont(subFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        document.add(new Paragraph("用人单位应当依法建立和完善规章制度，保障劳动者享有劳动权利和履行劳动义务。")
                .setFont(textFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
                .setFirstLineIndent(24)
        );
        document.add(new Paragraph("第五条")
                .setFont(subFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
        );
        document.add(new Paragraph("国家采取各种措施，促进劳动就业，发展职业教育，制定劳动标准，调节社会收入，完善社会保险，协调劳动关系，逐步提高劳动者的生活水平。")
                .setFont(textFont)
                .setFontSize(12)
                .setFontColor(new DeviceRgb(0,0,0))
                .setFirstLineIndent(24)
        );





        //Add new page
        PageSize ps = PageSize.A4;
        PdfPage page = pdf.addNewPage(ps);

        document.close();

        System.out.println("Awesome PDF just got created.");
    }
}
