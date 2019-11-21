package org.camunda.create.bpmndi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CleanUpOriginalFile {

    public static StringBuffer addNamespacesAndRemoveDefTag(String file) {
        StringBuffer sb = new StringBuffer();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if(line.contains("definitions")) {
                    if(line.startsWith("</definitions>")) {
                        // Ignore line to delete it from output.
                    } else {
                        // Add relevant namespaces
                        String[] strSplit = line.split(">");
                        System.out.println(strSplit[0]);
                        sb.append(strSplit[0] + " xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" >\n");

                    }
                } else {
                    sb.append(line + "\n");
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb;
    }
}
