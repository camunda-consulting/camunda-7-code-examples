package com.camunda.demo.dmntraining.taskrouting.fitnesse;

import java.util.ArrayList;
import java.util.List;

import fit.Binding;
import fit.ColumnFixture;
import fit.Parse;

public class DmnColumnFixture extends ColumnFixture {

	private String[] columnNames;
	private int outputColumn;

	@Override
	protected void bind(Parse heads) {
		try {
			columnNames = new String[heads.size()];
			for (int i = 0; heads != null; i++, heads = heads.more) {
				columnNames[i] = heads.text();
				if ("".equals(heads.text())) {
					// do...
				}
			}
		} catch (Throwable throwable) { // NOSONAR
			exception(heads, throwable);
		}
	}

	@Override
	public void doCell(Parse cell, int column) {
		String name = columnNames[column];
		cell.text();

	}

}
