package com.shaw.mysql.jpa.po;

import com.shaw.mysql.jpa.commons.OP;

import lombok.Data;

/**
 * @author shaw
 * @date 2023/7/18
 */
@Data
public class Predication<T> {

	private OP operator;
	private String name;
	private T value;

	private Predication() {
	}

	public static <T> Predication<T> get(OP operator, String name, T value) {
		return new Builder<T>().operator(operator).name(name).value(value).build();
	}

	public static class Builder<T> {
		private final Predication<T> p;

		public Builder() {
			this.p = new Predication<T>();
		}

		public Builder<T> operator(OP op) {
			this.p.operator = op;
			return this;
		}

		public Builder<T> name(String name) {
			this.p.name = name;
			return this;
		}

		public Builder<T> value(T value) {
			this.p.value = value;
			return this;
		}

		public Predication<T> build() {
			return this.p;
		}

	}
}
