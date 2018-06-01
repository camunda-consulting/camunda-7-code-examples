#!/bin/sh

echo "Start Bugfix Mailbug"

cd export
for FILENAME in ./*.bpmn; do
    echo "fix $FILENAME"
    xmlstarlet edit --inplace --pf \
        -N activiti="http://activiti.org/bpmn" \
        --update "//activiti:field[@name='charset' or @name='to' or @name='subject' or @name='text']/activiti:expression" -x "normalize-space(.)" \
        --update "//activiti:field[@name='charset']/activiti:string" -x "normalize-space(.)" \
        $FILENAME
done
echo "Finished Bugfix Mailbug"
