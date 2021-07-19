package com.hippo.fresh.service;

import com.hippo.fresh.utils.ResponseUtils;

public interface MailService {

    public ResponseUtils sendVerCode(String receiver, String verCode);
}
