package com.kill.core.service;

import com.kill.core.service.impl.process.entity.AnalysisResult;
import org.springframework.web.multipart.MultipartFile;

public interface UtilTestService {

    AnalysisResult analysisDataList(MultipartFile file);

}
