#!/bin/sh
# run this script inside the folder with the BPMN files that need to be patched

# the subnode part is rather heuristic, finding a subprocess based on a part of the id

for filename in ./*.bpmn; do
    xmlstarlet edit --inplace --pf \
      -N bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" \
      -N bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" \
      -N activiti="http://activiti.org/bpmn" \
      --delete "//@activiti:autoStoreVariables" \
      --subnode "//bpmndi:BPMNShape[contains(@bpmnElement, 'subprocess')]" -t attr -n isExpanded -v true \
      --update "//bpmn:signal[@activiti:scope='processInstance']/@name" -x "concat(., '-\${execution.getProcessInstanceId()}')" \
      --delete "//bpmn:signal/@activiti:scope" \
      $filename
done
