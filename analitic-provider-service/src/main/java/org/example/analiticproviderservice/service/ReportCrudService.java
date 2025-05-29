package org.example.analiticproviderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.analiticproviderservice.dao.OperationPercentDao;
import org.example.analiticproviderservice.dao.ReportDao;
import org.example.analiticproviderservice.dao.RequestPercentDao;
import org.example.analiticproviderservice.model.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportCrudService {

    private final ReportDao reportDao;
    private final RequestPercentDao requestPercentDao;
    private final OperationPercentDao operationPercentDao;

    @Transactional
    public Report saveReport(Report report){
        var requestPercent = requestPercentDao.save(report.getRequestPercent());
        var operationPercent = operationPercentDao.save(report.getOperationPercent());
        Report reportSaved = reportDao.save(report, requestPercent.getId(), operationPercent.getId());
        reportSaved.setRequestPercent(requestPercent);
        reportSaved.setOperationPercent(operationPercent);
        return reportSaved;
    }
}
