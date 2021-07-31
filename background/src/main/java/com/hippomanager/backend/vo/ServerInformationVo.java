package com.hippomanager.backend.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ServerInformationVo {
    private String serverIP;		//服务器IP
    private String serverURL;		//服务器Url
    private String serverType;		//服务器类型
    private Date serverTime;		//服务器时间
    private String databaseType;	//数据库类型
}
