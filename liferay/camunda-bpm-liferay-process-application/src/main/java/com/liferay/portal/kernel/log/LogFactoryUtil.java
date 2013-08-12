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

/**
 * This class has been copied from the Liferay Portal API and refactored so that this CDI demo portlet can be tested in
 * Pluto, since the Liferay CDI Portlet Bridge has a dependency on the Liferay Portal logging API. For more info, see <a
 * href="http://issues.liferay.com/browse/FACES-1589">FACES-1589</a>.
 *
 * @author  Neil Griffin
 */
public class LogFactoryUtil {

	public static Log getLog(Class<?> c) {
		return new LogImpl(c);
	}

	public static Log getLog(String name) {
		return new LogImpl(name);
	}

}
