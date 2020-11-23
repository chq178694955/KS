package com.king.framework.excel;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;

/**
 * @创建人 chq
 * @创建时间 2020/9/4
 * @描述
 */
public class ExcelEventModel {

    public void processOneSheet(String path)throws Exception{
        OPCPackage pkg = OPCPackage.open(path);
        XSSFReader reader = new XSSFReader(pkg);
        SharedStringsTable table = reader.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(table);
        InputStream sheet2 = reader.getSheet("rId1");
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);
        sheet2.close();
    }

    private XMLReader fetchSheetParser(SharedStringsTable table) throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        ContentHandler handler = new SheetHandler(table);
        parser.setContentHandler(handler);
        return parser;
    }

    private static class SheetHandler extends DefaultHandler{
        private SharedStringsTable table;
        private String lastContents;
        private boolean nextIsString;
        private SheetHandler(SharedStringsTable table){
            this.table = table;
        }

        public void startElement(String uri, String localName, String name, Attributes attributes)throws SAXException{
            if (name.equals("c")) {
                System.out.print(attributes.getValue("r") + " - ");
                String cellType = attributes.getValue("t");
                if (cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }
            }
            lastContents = "";
        }

        public void endElement(String uri, String localName, String name) throws SAXException {
            if (nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(table.getEntryAt(idx)).toString();
                nextIsString = false;
            }

            if (name.equals("v")) {
                System.out.println(lastContents);
            }
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            lastContents += new String(ch, start, length);
        }

    }

    public static void main(String[] args) throws Exception{
        //InputStream stream = new FileInputStream("C:\\Users\\17869\\Desktop\\SIM卡日用量信息导入模板.xlsx");
        Thread.sleep(5000);
        for(int i=0;i<100;i++){
            ExcelEventModel model = new ExcelEventModel();
            model.processOneSheet("C:\\Users\\17869\\Desktop\\SIM卡日用量信息导入模板.xlsx");
            Thread.sleep(1000);
        }
    }

}
