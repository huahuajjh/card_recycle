package com.tqmars.cardrecycle.application.admin.user.dto;

/**
 * Created by jjh on 1/17/17.
 */
public class QueryUserListInput {
    private int index;
    private int count;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
