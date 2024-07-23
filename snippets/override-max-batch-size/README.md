# Override history cleanup batch size

There is a fixed limit of 500 for the batch size of the history cleanup.

As this limit is checked on `init` of the process engine, there is an easy way to work around this.

This application shows the workaround:

A process engine plugin is added that:

* pre-init: checks the configured batch size and saves it to a field. If this is bigger than the configured limit, it sets the config to the limit
* post-init: sets the original value. This will prevent the validation to fail, allowing for **any** batch size

>CAUTION: There are good reasons for the configured limit (transaction duration, fault tolerance, ...), please be mindful before using this workaround.