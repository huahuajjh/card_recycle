package com.tqmars.cardrecycle.infrastructure.serialization;

/**
 * Created by jjh on 1/15/17.
 */
public class PageFormatter extends Formatter{
    private int count;


    private PageFormatter() {
        super();
    }

    public static PageFormatter getInstance(Object _data,String _msg,int _code,int _count){
        PageFormatter pageFormatter = new PageFormatter();
        pageFormatter.setData(_data);
        pageFormatter.setMsg(_msg);
        pageFormatter.setCode(_code);
        pageFormatter.setCount(_count);
        return pageFormatter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
