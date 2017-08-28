#!/bin/sh
# run this script inside the folder with the BPMN files that need to be checked

# perform XML schema validation
find . -iname '*.bpmn' | xargs -L1 validbpmn > ../validate-exported-bpmns.log 2>&1

# find all activiti extensions
find . -iname '*.bpmn' | xargs grep -hiro 'activiti:[^ >=]\+' | sort | uniq > ../activiti-extensions.unique.txt

# find activiti extension elements
grep -hiro '<activiti:[^ />]\+' . | sort | uniq > ../activiti-extension-elements.unique.txt
grep -hiro '<activiti:[^/>]\+/\?>' . | sort | uniq > ../activiti-extension-elements-with-attributes.unique.txt

# find Activiti extension attributes
grep -hiro 'activiti:[^=]\+=' .| sort | uniq > ../activiti-extension-attributes.unique.txt
grep -hiro 'activiti:[^=]\+=[^ />]\+' . | sort | uniq > ../activiti-extension-attributes-with-values.unique.txt

# find unsupported activiti extensions
grep -hiro '<[^>]\+activiti:autoStoreVariables="true"[^>]*>' . |sort|uniq > ../activiti-autoStoreVariables-elements.unique.txt
grep -Hirl '<[^>]\+activiti:autoStoreVariables="true"[^>]*>' . |sort|uniq > ../activiti-autoStoreVariables.affected-files.txt
grep -Hirn '<[^>]\+activiti:autoStoreVariables="true"[^>]*>' -A2 . # script tasks with script code
xmlstarlet select -N bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" -N activiti="http://activiti.org/bpmn" -t -v "//bpmn:scriptTask[@activiti:autoStoreVariables]" *.bpmn
xmlstarlet select -N bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" -N activiti="http://activiti.org/bpmn" -t -v "//bpmn:scriptTask[@activiti:autoStoreVariables]" *.bpmn|grep ' = '|sort |uniq
xmlstarlet select -N bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" -N activiti="http://activiti.org/bpmn" -t -v "//bpmn:scriptTask[@activiti:autoStoreVariables='true']" *.bpmn|grep = # dangerous if not empty

grep -hiro '<[^>]\+activiti:skipExpression[^>]\+>' . |sort|uniq  > ../activiti-skipExpression-elements.unique.txt
grep -Hirl '<[^>]\+activiti:skipExpression[^>]\+>' . |sort|uniq > ../activiti-skipExpression.affected-files.txt

grep -hiro '<[^>]\+activiti:scope[^>]\+>' . |sort|uniq > ../activiti-scope-elements.unique.txt
grep -Hirl '<[^>]\+activiti:scope[^>]\+>' . |sort|uniq > ../activiti-scope.affected-files.txt

grep -hiro '<[^>]\+activiti:type[^>]\+>' . |sort|uniq > ../activiti-type-elements.unique.txt
grep -hiro 'activiti:type[^ >]\+' . |sort|uniq > ../activiti-type-with-values.unique.txt

# find sub-processes (activiti does not set the attribute isExpanded)
grep -hiro '<subProcess[^>]*>' . |sort|uniq
grep -hiro '<[^>/]*id="[^"]*subprocess[^"]*"[^>]*>' . |sort|uniq # all elements that contain subprocess in their id
