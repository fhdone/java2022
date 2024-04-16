package util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.august.model.DemoExcelBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelDemo {

//    public final String FILE_PATH = "excel/demo_图片属性.xlsx";
    public final String FILE_PATH = "excel/demo_多一张图片.xlsx";

    /**
     * https://blog.csdn.net/yangyuscript/article/details/134860765
     * org.apache.poi.xssf.usermodel.XSSFClientAnchor.setCol2(XSSFClientAnchor.java:231)
     */
    @Test
    public void readExcel() throws Exception {

        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(4);
        importParams.setStartSheetIndex(1);
        List<DemoExcelBean> excelDatas = ExcelImportUtil.importExcel(
                TestFileUtil.readFile(FILE_PATH),
                DemoExcelBean.class, importParams);
        log.info(JSON.toJSONString(excelDatas));

//        List<XSSFPictureData> listPic = (List<XSSFPictureData>) ExcelUtil.pictures(ResourceUtil.getStream(FILE_PATH));
//        log.info(listPic.size()+"");


    }


    @Test
    public void readPic() throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(TestFileUtil.readFile(FILE_PATH)));
        XSSFSheet sheet = workbook.getSheetAt(1);

        //读取全部图片
        List<XSSFPictureData> pics = workbook.getAllPictures();
        log.info("all pics size:{}", pics.size());
        
        //按sheet读取
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    String picIndex = String.valueOf(1) + "_" + ctMarker.getRow() + "_" + ctMarker.getCol();
                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
                }
            }
        }
        log.info(sheetIndexPicMap.toString());
        
        log.info("sheet pics size:{}", sheetIndexPicMap.size());
        for (Map.Entry<String, PictureData> entry : sheetIndexPicMap.entrySet()) {
            log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

    }
}
