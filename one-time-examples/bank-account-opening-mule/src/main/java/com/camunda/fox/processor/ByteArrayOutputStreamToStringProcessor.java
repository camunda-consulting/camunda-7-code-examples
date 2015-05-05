package com.camunda.fox.processor;

import java.io.ByteArrayOutputStream;

public class ByteArrayOutputStreamToStringProcessor {

	public String process(ByteArrayOutputStream stream) {
		return stream.toString();
	}
}
