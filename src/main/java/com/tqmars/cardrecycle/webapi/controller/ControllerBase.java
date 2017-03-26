package com.tqmars.cardrecycle.webapi.controller;

import com.excel.util.ExcelFactory;
import com.excel.util.intefaces.IExcelOutput;
import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import com.tqmars.cardrecycle.infrastructure.serialization.Code;
import com.tqmars.cardrecycle.infrastructure.serialization.Serialization;
import com.tqmars.cardrecycle.infrastructure.servicelocator.ServiceLocator;
import com.tqmars.cardrecycle.webapi.controller.admin.order.AdminOrderController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * Created by jjh on 1/14/17.
 */
public abstract class ControllerBase {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected <TAppService> TAppService getService(String serviceId, Class<TAppService> serviceClass) {
        return ServiceLocator.getInstance().getService(serviceId, serviceClass);
    }

    public ControllerBase(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    protected HttpSession getSession() {
        return this.request.getSession();
    }

    protected String toSucessMsg() {
        return toSucessMsg("操作成功");
    }

    protected String toSucessMsg(String msg) {
        String callback = this.request.getParameter("callback");
        String script = callback + "(" + Serialization.toJsonWithFormatter(null, msg, Code.SUCCESS) + ")";
        return script;
    }

    protected String toSuccessMsg(String msg, int code) {
        String callback = this.request.getParameter("callback");
        String script = callback + "(" + Serialization.toJsonWithFormatter(null, msg, code) + ")";
        return script;
    }

    protected String toJsonWithFormatter(Object data, String msg, int code) {
        String callback = this.request.getParameter("callback");
        String script = callback + "(" + Serialization.toJsonWithFormatter(data, msg, code) + ")";
        return script;
    }

    protected String toJsonWithPageFormatter(Object data, String msg, int code, int count) {
        String callback = this.request.getParameter("callback");
        String script = callback + "(" + Serialization.toJsonWithPageFormatter(data, msg, code, count) + ")";
        return script;
    }

    protected String toFailMsg(String msg, int code) {
        return toSuccessMsg(msg, code);
    }

    protected String toFailMsg(String msg) {
        return toFailMsg(msg, Code.FAIL);
    }

    private String getCallback() {
        return this.request.getParameter("callback");
    }

    protected String toJsonp(String data) {
        String callback = getCallback();
        if (null == callback) {
            return data;
        }
        return callback + "(" + data + ")";
    }

    protected <T> ResponseEntity<T> getResponseEntity(InputStream in, String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity r = null;
        try {
            r = ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(in.available())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    protected <TOutput> void export(String templatePath, Class<TOutput> outputEntity , List<TOutput> list,HttpServletResponse res,String filename){
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename="+filename);

        String path = AdminOrderController.class.getResource(templatePath).getFile();
        FileInputStream in = null;

        try {
            in = new FileInputStream(path);

            IExcelOutput output = ExcelFactory.getExcelOutput(in);
            output.writeDatas(outputEntity,list,1);

            output.writeStream(res.getOutputStream());
        } catch (FileNotFoundException e) {
            LoggerFactory.getLogger().error(e.getLocalizedMessage());
        }catch (IOException e){
            LoggerFactory.getLogger().error(e.getLocalizedMessage());
        } catch (IllegalAccessException e) {
            LoggerFactory.getLogger().error(e.getLocalizedMessage());
        } catch (NoSuchMethodException e) {
            LoggerFactory.getLogger().error(e.getLocalizedMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LoggerFactory.getLogger().error(e.getLocalizedMessage());
            }
        }
    }

}
