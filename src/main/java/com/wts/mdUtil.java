package com.wts;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.*;

import java.io.*;

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
     * 加密
     *
     * @param fileName 文件名
     * @param password 密码
     */
    public static void addPassword(String fileName, String password) throws Exception {
        File file = new File(fileName);
        PDDocument pdDocument = PDDocument.load(file);
        AccessPermission permissions = new AccessPermission();
        permissions.setCanExtractContent(false);
        permissions.setCanModify(false);
        permissions.setCanPrint(false);
        permissions.setReadOnly();
        StandardProtectionPolicy p = new StandardProtectionPolicy(password, password, permissions);
        SecurityHandler sh = new StandardSecurityHandler(p);
        sh.prepareDocumentForEncryption(pdDocument);
        PDEncryption encryptionOptions = new PDEncryption();
        encryptionOptions.setSecurityHandler(sh);
        pdDocument.setEncryptionDictionary(encryptionOptions);
        //最后的保存路径，这里用的是原来的路径覆盖操作        可以设为其他路径
        pdDocument.save(fileName);
    }

    /**
     * 将单个MD文件变更为PDF文件
     */
    public static void Md2Pdf(String MdFilePath, String PdfFilePath, String policyName,
                              String waterPrintStr, String password) throws Exception {
        String simhei = "C:/Windows/Fonts/simhei.ttf";//黑体
        String simfang = "C:/Windows/Fonts/simfang.ttf";//仿宋
        String simkai = "C:/Windows/Fonts/simfang.ttf";//楷体
        String simsun = "C:/Windows/Fonts/simsun.ttf";//宋体

        PdfDocument pdf = new PdfDocument(new PdfWriter(PdfFilePath));

        HeadertFooterHandler headertFooterHandler = new HeadertFooterHandler();
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE, headertFooterHandler);

        WaterPrintHandler waterPrintHandler = new WaterPrintHandler();
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, waterPrintHandler);
        waterPrintHandler.setInfo(waterPrintStr);

        Document document = new Document(pdf);
        PdfFont heiti = PdfFontFactory.createFont(simhei, PdfEncodings.IDENTITY_H, true);
        PdfFont fangsong = PdfFontFactory.createFont(simfang, PdfEncodings.IDENTITY_H, true);
        PdfFont kaiti = PdfFontFactory.createFont(simkai, PdfEncodings.IDENTITY_H, true);

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
                            .setFont(heiti)
                            .setFontSize(26)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setTextAlignment(TextAlignment.CENTER)
                            .setKeepWithNext(true)
                            .setDestination(name);
                } else if (line.startsWith("# ")) {
                    p = new Paragraph(line.replace("# ", ""));
                    name = String.format("title%02d", counter++);
                    outline2 = createOutline(outline1, pdf, line.replace("# ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(heiti).setBold()
                            .setFontSize(18)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setKeepWithNext(true)
                            .setDestination(name);
                } else if (line.startsWith("* 发文字号") || line.startsWith("* 效力级别") || line.startsWith("* 文件时效")
                        || line.startsWith("* 发布日期") || line.startsWith("* 实施日期") || line.startsWith("* 发布机关") || line.startsWith("* 文件来源")) {
                    p = new Paragraph(line.replace("* ", ""));
                    p.setKeepTogether(true)
                            .setFont(fangsong)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setFirstLineIndent(24);
                } else if (line.startsWith("* ")) {
                    p = new Paragraph(line.replace("* ", ""));
                    p.setKeepTogether(true)
                            .setFont(fangsong)
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
                            .setFont(kaiti).setBold()
                            .setFontSize(14)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setDestination(name);
                } else if (line.startsWith("### ")) {
                    p = new Paragraph(line.replace("### ", ""));
                    name = String.format("title%02d", counter++);
                    outline4 = createOutline(outline3, pdf, line.replace("### ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(kaiti).setBold()
                            .setFontSize(13)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setDestination(name);
                } else if (line.startsWith("#### ")) {
                    p = new Paragraph(line.replace("#### ", ""));
                    name = String.format("title%02d", counter++);
                    outline5 = createOutline(outline4, pdf, line.replace("#### ", ""), name);
                    p.setKeepTogether(true)
                            .setFont(fangsong)
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
                            .setFont(fangsong)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setDestination(name);
                } else if (line.startsWith("&#8195;&#8195;")) {
                    p = new Paragraph(line.replace("&#8195;&#8195;", ""));
                    p.setKeepTogether(true)
                            .setFont(fangsong)
                            .setFontSize(12)
                            .setFontColor(new DeviceRgb(0, 0, 0))
                            .setPadding(0)
                            .setMargin(0)
                            .setFirstLineIndent(24);
                } else {
                    p = new Paragraph(line);
                    p.setKeepTogether(true).setFont(fangsong);

                }
                document.add(p);
            }
        }
        document.close();
//        addPassword(PdfFilePath, password);
        System.out.println("转换完成：" + MdFilePath + "--->" + PdfFilePath);
    }


    /**
     * 使用递归遍历文件夹及子文件夹中文件
     *
     * @param MdFilesPath MD文件夹目录
     */
    public static void filesDirs(String MdFilesPath) throws Exception {
        File file = new File(MdFilesPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File flies2 : files) {
                filesDirs(flies2.getPath());
            }
        } else {
            String policyName = file.getName().replace(".md","");
            String MdFilePath = file.getAbsolutePath();
            String PdfFilePath = file.getAbsolutePath().replace("C:\\Users\\nelso\\OneDrive\\人社政策文档库", "D:\\人社政策文档库").replace(".md", ".pdf");
            Md2Pdf(MdFilePath, PdfFilePath, policyName, "", "");
        }
    }

    public static void main(String[] args) throws Exception {
        String MdFilesPath = "C:\\Users\\nelso\\OneDrive\\人社政策文档库";

        File file = new File("D:\\人社政策文档库");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\A法律");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\B行政法规");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\B行政法规\\国务院令");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\B行政法规\\省政府令");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\B行政法规\\市政府令");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\C部颁规章");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\C部颁规章\\劳动部令");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\C部颁规章\\人社部令");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\C部颁规章\\人事部令");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\C部颁规章\\其他");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\D地方性法规");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\D地方性法规\\省人大");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\D地方性法规\\市人大");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\E司法解释");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\E司法解释\\最高法");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\E司法解释\\最高检");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\E司法解释\\省高院");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\A政府");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\A政府\\国务院");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\A政府\\省政府");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\A政府\\市政府");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\财政部");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\教育部");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\劳动部");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\民政部");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\人社部");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\人事部");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\总工会");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\B部委\\其他");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\C省厅");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\C省厅\\劳动厅");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\C省厅\\人社厅");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\C省厅\\人事厅");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\C省厅\\其他");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\D市局");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\D市局\\劳动局");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\D市局\\人事局");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\D市局\\人社局");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        file = new File("D:\\人社政策文档库\\F规范性文件\\D市局\\其他");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }


        filesDirs(MdFilesPath);
    }


}
