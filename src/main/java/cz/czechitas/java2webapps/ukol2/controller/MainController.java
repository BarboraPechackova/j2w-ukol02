package cz.czechitas.java2webapps.ukol2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {


    private static List<String> readAllLines(String resource)throws IOException {
        //Soubory z resources se získávají pomocí classloaderu. Nejprve musíme získat aktuální classloader.
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

        //Pomocí metody getResourceAsStream() získáme z classloaderu InpuStream, který čte z příslušného souboru.
        //Následně InputStream převedeme na BufferedRead, který čte text v kódování UTF-8
        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        ){

            //Metoda lines() vrací stream řádků ze souboru. Pomocí kolektoru převedeme Stream<String> na List<String>.
            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/")

    public ModelAndView stranka() throws IOException {
        Random nahodny = new Random();

        ModelAndView result = new ModelAndView("stranka");

        List<String> citaty = readAllLines("citaty.txt");
        int cislocitatu = nahodny.nextInt(citaty.size());
        result.addObject("citat", citaty.get(cislocitatu));

        List<String> obrazky=Arrays.asList("xIG1sDfZoSA", "e8KIBbJw21A", "lRwGMe1MFj4", "onpxyxjwKm0", "IaJ2Ec1TJF0", "enuCEimS1p4");
        int cisloobrazku = nahodny.nextInt(obrazky.size());
        result.addObject("obrazek", obrazky.get(cisloobrazku));


        return result;


    }




   // result.addObject("oprasek", "https://source.unsplash.com/random");


}





