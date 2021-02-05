package com.neurolabs.appscript_balancer.controller;

import com.neurolabs.appscript_balancer.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/translate")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @GetMapping
    public ResponseEntity<String> Translate(@RequestParam String languageSrc, @RequestParam String languageTarget, @RequestParam String text){

        ResponseEntity<String> responseEntity = null;

        try {

            String translatedText = translationService.translate(languageSrc, languageTarget, text);

            responseEntity = new ResponseEntity(translatedText, HttpStatus.OK);

        } catch (IOException e) {

            responseEntity = new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return responseEntity;

    }

}
