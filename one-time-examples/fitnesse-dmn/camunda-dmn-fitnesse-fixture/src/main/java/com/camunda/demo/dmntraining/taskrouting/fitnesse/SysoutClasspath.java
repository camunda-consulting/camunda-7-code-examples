package com.camunda.demo.dmntraining.taskrouting.fitnesse;

import java.net.URL;
import java.net.URLClassLoader;

public class SysoutClasspath {

	public static void main (String args[]) {

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
         
   }
}
