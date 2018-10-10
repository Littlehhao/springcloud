package com.sztech.springcloudbean.commen;

/**
 * @program: springcloud
 * @description: 枚举工具类
 * @author: jiefu
 * @create: 2018-09-12 10:50
 **/
public class Constants {

    public enum ResultEnums{
        SUCCESS("1", "成功"), ERROR("0", "发生异常");

        private String code;

        private String msg;

        ResultEnums(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
