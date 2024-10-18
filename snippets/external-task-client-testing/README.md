# External Task Worker Testing

This example shows 3 ways to test external tasks:

1. Test the worker: Mock an `ExternalTask` and `ExternalTaskService` and verify the interaction with these mocked objects.
2. Test the process: Run the engine, but disable the worker and complete external tasks with the java api.
3. Test everything together: Run the engine and the worker and test everything together.

For all tests, we have the java apis at hand.