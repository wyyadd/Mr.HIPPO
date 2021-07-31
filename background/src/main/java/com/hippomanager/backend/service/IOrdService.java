package com.hippomanager.backend.service;

import com.hippomanager.backend.Mapper.entity.Ord;
import java.util.List;

public interface IOrdService {
    List<Ord> selectOrdAll();
}
