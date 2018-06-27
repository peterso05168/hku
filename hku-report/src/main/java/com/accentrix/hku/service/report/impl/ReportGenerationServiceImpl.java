package com.accentrix.hku.service.report.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.service.report.ReportGenerationService;
import com.accentrix.hku.timer.annotation.Timer;

@Service
@Transactional(readOnly = true)
@Timer
@Path("report")
public class ReportGenerationServiceImpl implements ReportGenerationService {

    @Override
    public List<String> getReportData() {
        List<String> list = new ArrayList<String>();
        list.add("Report Test Data");
        return list;
    }

}
