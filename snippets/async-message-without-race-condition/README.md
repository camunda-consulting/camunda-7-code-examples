# Global Message Correlation

Example how to prevent `MissmatchingMessageCorrelationException` beacuse of race conditions in an async Messaging Pattern.

## Idea and Creation

Instead of directly communicating with the response event in a process, an intermediate process is called. This process has a message start event and a message end event. As variable, it will take an implementation of `MessageCorrelationRunner`. Do not start this process directly but call the Bean `GlobalMessageCorrelator.correlateMessage(messageCorrelationRunner)` instead. 

## Background

Race conditions sometimes appear if a async response arrives faster than the process can proceed to the waiting state for this message. By using a process to prevent this, 3 things will be achieved: 
1. No Message is lost: The process is started anyway, saving the data provided by the `MessageCorrelationRunner`
2. All happens inside the engine: Everything the engine can do (which is quite a lot) can be used to correlate the message or find a possible mistake in the response message.
3. No need to change the original process: Before creating this, I have seen some solutions including conditional events and event subprocesses that changed the semantics of the original process. This approach does not need any changes at the original process.