package com.main.tgcareer.common.jason;

import lombok.Getter;

/**
 * 对返回信息进行统一维护
 */
public enum AjaxEnum {
    UNKOWN_ERROR("-1","未知错误"),
    REGISTER_ERROR("101","未注册"),
    WxMaJscode2SessionResult_ERROR("102","获取sessionKey失败"),
    NO_COMMENTS("103","该用户没有评论"),
    NO_TOKEN("104","缺少token"),
    TOKEN_ERROR("105","token错误"),
    NO_PERMISSION("106","没有权限"),
    SUCCESS("0","操作成功");

    @Getter
    private String code;
    @Getter
    private String msg;

    AjaxEnum(String code,String msg){

        this.code = code;
        this.msg = msg;
    }
}
