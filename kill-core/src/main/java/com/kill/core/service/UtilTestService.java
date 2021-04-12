package com.kill.core.service;

import com.kill.core.service.impl.process.entity.SchoolDataCheckResult;
import org.springframework.web.multipart.MultipartFile;

public interface UtilTestService {

    SchoolDataCheckResult analysisDataList(MultipartFile file);

}
