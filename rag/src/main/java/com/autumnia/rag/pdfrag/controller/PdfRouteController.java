package com.autumnia.rag.pdfrag.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ai")
public class PdfRouteController {
    @GetMapping("/rag/pdfrag")
    public String korea(){
        return "pdfrag"; // rag.html
    }
}
