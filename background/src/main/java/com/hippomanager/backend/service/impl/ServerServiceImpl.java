package com.hippomanager.backend.service.impl;

import com.hippomanager.backend.service.IServerService;
import com.hippomanager.backend.vo.ServerInformationVo;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class ServerServiceImpl implements IServerService {
    @Override
    public ServerInformationVo getServerInfo() {
//            HttpServletRequest request = ServletActionContext.getRequest();
//            Runtime runTime = Runtime.getRuntime();
//            ServerInformationVo  serverInfoVo=new ServerInformationVo();
//            try {
//                InetAddress address = InetAddress.getLocalHost();
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//            serverInfoVo.setServerType(application.getServerInfo())
//

        return null;
    }
}
