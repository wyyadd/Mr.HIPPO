package com.hippo.fresh.service;

import java.util.List;
import java.util.Map;

public interface CartService {

    public List<Map<String,Object>> findCartInformationByUserId(Long id);
}
