package util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.august.model.DemoExcelBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ExcelDemo {
    
    public final String FILE_PATH = "excel/demo.xlsx";

    /**
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

    
    
}
