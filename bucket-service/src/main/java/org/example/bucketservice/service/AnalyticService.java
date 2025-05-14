package org.example.bucketservice.service;

import org.example.bucketservice.dao.AnalyticFrameDao;
import org.example.bucketservice.model.AnalyticalFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticService {

    private final AnalyticFrameDao analyticFrameDao;

    @Autowired
    public AnalyticService(AnalyticFrameDao analyticFrameDao) {
        this.analyticFrameDao = analyticFrameDao;
    }

    public void save(AnalyticalFrame frame) {
        analyticFrameDao.save(frame);
    }

    public List<AnalyticalFrame> getFrames(){
        return analyticFrameDao.getLastFramesByTime();
    }
}
