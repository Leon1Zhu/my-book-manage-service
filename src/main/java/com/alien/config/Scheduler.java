package com.alien.config;

import com.alienlab.jsoup.PdfDataGrab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by zhuliang on 2017/5/10.
 */
@Component
public class Scheduler {

    @Autowired
    private PdfDataGrab pdfDataGrab;
    @Scheduled(fixedRate=1000*60*60*2)
    public void testTasks() {
        pdfDataGrab.getPdfUrl();
    }
}
