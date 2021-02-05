package com.neurolabs.appscript_balancer.pool;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServicePool {

    private static final int QUOTAS = 5000;

    private final List<TranslationService> servicePool = new ArrayList<>();

    public ServicePool() {

        try {

            File file = new File("src/main/resources/services");

            FileReader fr = new FileReader(file);

            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {

                servicePool.add(new TranslationService(line));
                line = reader.readLine();

            }

        }

        catch (Exception e){

        }

    }

    public String getServiceUrlFromPool(){

        for (TranslationService service : servicePool){
            if (service.getRequests() < QUOTAS){

                service.incAndGetRequests();

                return service.getUrl();
            }
        }

        return null;

    }

}
