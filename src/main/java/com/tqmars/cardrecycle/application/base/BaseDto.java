package com.tqmars.cardrecycle.application.base;

/**
 * Created by jjh on 1/15/17.
 */
public abstract class BaseDto {

    public abstract Result validate();

    public static class Result{
        private String msg;
        private boolean success;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

}
