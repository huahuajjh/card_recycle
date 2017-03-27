package com.tqmars.test.third;

import com.excel.util.ExcelFactory;
import com.excel.util.intefaces.IExcelOutput;
import com.tqmars.cardrecycle.application.admin.order.dto.QueryOrderListAsListOutput;
import com.tqmars.cardrecycle.infrastructure.StringTools.DateTool;
import com.tqmars.cardrecycle.webapi.controller.admin.order.AdminOrderController;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjh on 17-3-25.
 */
public class TestSpringFs {
    @Test
    public void testGetFileFromResourcesDirectory() throws Exception {
        String path = AdminOrderController.class.getResource("/template/order.xlsx").getFile();
        FileInputStream in = new FileInputStream(path);

        IExcelOutput output = ExcelFactory.getExcelOutput(in);

        output.writeDatas(QueryOrderListAsListOutput.class,getData(),1);

        output.writeStream(new FileOutputStream("/home/jjh/1.xlsx"));
    }

    private List<QueryOrderListAsListOutput> getData(){
        List<QueryOrderListAsListOutput> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            QueryOrderListAsListOutput e = new QueryOrderListAsListOutput();
            e.setName("a");
            e.setActualAmount(new BigDecimal("10"));
            e.setCardAmount(new BigDecimal("12"));
            e.setCardNum("1325131");
            e.setCardTypeName("asd");
            e.setOrderNum("123");
            e.setIdCardNum("123123");
            e.setOrderStatus(1);
            e.setOrderTime(DateTool.getInstance().getNowSqlTime());
            e.setProcessTime(DateTool.getInstance().getNowSqlTime());
            e.setSaleRatio(1.2f);
            e.setTel("1231");
            e.setThirdMsg("adds");

            list.add(e);
        }

        return list;
    }

}
