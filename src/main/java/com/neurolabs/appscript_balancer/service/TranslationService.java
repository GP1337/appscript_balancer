package com.neurolabs.appscript_balancer.service;

import com.neurolabs.appscript_balancer.pool.ServicePool;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TranslationService {

    @Autowired
    private ServicePool servicePool;

    public String translate(String languageSrc, String languageTarget, String text) throws IOException {

        String url = servicePool.getServiceUrlFromPool();

        if (url != null) {

            url = url + "?q=" + URLEncoder.encode(text, "UTF-8") +
                    "&target=" + languageTarget +
                    "&source=" + languageSrc;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            return response.body().string();
        }

        return "";

    }

}
