package org.camunda.create.bpmndi;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GenerateBPMNFileWithDiagramInstructions {

    public static void main(String[] args)  {
        try {

            // Read and parse file
            // Need to pass in two args - arg[0] is the input file and arg[1] is the output file
            if(args.length < 2) {
                System.out.println("Two arguments are required for this BPMNDI generator program. One for the input file followed by one for the output file.");
                return;
            }

            // First, we need to add the bpmndi, dc, and di namespaces to the original file as well as remove the </definitions>
            // tag as we'll add it to the end of the bpmndi stanza
            StringBuffer sbo = CleanUpOriginalFile.addNamespacesAndRemoveDefTag(args[0]);

            // Next, read and prepare the original file to be scanned for various elements to generate the bpmndi
            File fXmlFile = new File(args[0]);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            StringBuffer sb = new StringBuffer();

            // Run the BPMNDI generator
            sb = BPMNDIGenerator.createBPMNDIStanza(sb, doc);

            // Now that we have the generated BPMNDI in a StringBuffer, read the contents of the original file and write out the contents,
            // appending the generated BPMNDI
            FileInputStream inputStream = new FileInputStream(args[0]);
            FileOutputStream outputStream = new FileOutputStream(args[1]);

            byte[] originalFile = String.valueOf(sbo).getBytes(); //(IOUtils.toByteArray(inputStream));

            byte[] diagram = String.valueOf(sb).getBytes();

            byte[] outputWithDiagram = new byte[originalFile.length + diagram.length];

            // copy original into start of destination (from pos 0, copy originalFile.length bytes)
            System.arraycopy(originalFile, 0, outputWithDiagram, 0, originalFile.length);

            // copy diagram into end of destination (from pos originalFile.length, copy diagram.length bytes)
            System.arraycopy(diagram, 0, outputWithDiagram, originalFile.length, diagram.length);

            outputStream.write(outputWithDiagram);

            outputStream.close();
            System.out.println("Diagram completed. ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

