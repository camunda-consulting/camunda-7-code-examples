/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.portal.kernel.log;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * This class exists so that this CDI demo portlet can be tested in Pluto, since the Liferay CDI Portlet Bridge has a
 * dependency on the Liferay Portal logging API. For more info, see <a
 * href="http://issues.liferay.com/browse/FACES-1589">FACES-1589</a>.
 *
 * @author  Neil Griffin
 */
public class LogImpl implements Log {

	// Private Data Members
	private Logger logger;

	public LogImpl(Class<?> clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}

	public LogImpl(String className) {
		logger = LoggerFactory.getLogger(className);
	}

	public void debug(Object msg) {
		logger.debug(msg.toString());
	}

	public void debug(Throwable t) {
		logger.debug(t.getMessage(), t);
	}

	public void debug(Object msg, Throwable t) {
		logger.debug(msg.toString(), t);
	}

	public void error(Object msg) {
		logger.debug(msg.toString());
	}

	public void error(Throwable t) {
		logger.error(t);
	}

	public void error(Object msg, Throwable t) {
		logger.error(msg.toString(), t);
	}

	public void fatal(Object msg) {
		logger.error(msg.toString());
	}

	public void fatal(Throwable t) {
		logger.error(t);
	}

	public void fatal(Object msg, Throwable t) {
		logger.error(t);
	}

	public void info(Object msg) {
		logger.info(msg.toString());
	}

	public void info(Throwable t) {
		logger.info(t.getMessage());
	}

	public void info(Object msg, Throwable t) {
		logger.info(msg.toString(), t);
	}

	public void trace(Object msg) {
		logger.trace(msg.toString());
	}

	public void trace(Throwable t) {
		logger.trace(t.getMessage());
	}

	public void trace(Object msg, Throwable t) {
		logger.trace(msg.toString());
	}

	public void warn(Object msg) {
		logger.warn(msg.toString());
	}

	public void warn(Throwable t) {
		logger.warn(t.getMessage());
	}

	public void warn(Object msg, Throwable t) {
		logger.warn(msg.toString());
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

}
