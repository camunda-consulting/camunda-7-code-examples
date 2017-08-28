#!/bin/sh
# run this script inside the folder with the BPMN files that need to be checked
# this script will output all signal definitions and intermediateThrowEvents so you can then check for processInstance signal scopes
# you might also want to check for intermediateCatchEvents and also filter out non-signal events

for filename in ./*.bpmn; do
    echo '\n---\n'$filename '\n' >> ../temp.txt

    xmlstarlet select -I -N bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" -t -c "//bpmn:signal" $filename >> ../temp.txt
    echo '' >> ../temp.txt
    xmlstarlet select -I -N bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" -t -c "//bpmn:intermediateThrowEvent" $filename >> ../temp.txt
done

sed -e 's#xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:activiti="http://activiti.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI"##g;s#><#>\'$'\n<#g' ../temp.txt > ../activiti-scope-signal-details.txt

rm ../temp.txt
