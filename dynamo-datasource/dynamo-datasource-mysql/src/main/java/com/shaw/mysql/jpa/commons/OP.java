package com.shaw.mysql.jpa.commons;

/**
 * @author shaw
 * @date 2023/7/18
 */
public enum OP {
	// like
	LIKE,
	// =
	EQ,
	// !=
	NOT_EQ,
	// >
	GT,
	// >=
	GT_EQ,
	// <
	LT,
	// <=
	LT_EQ,
	// is null
	NULL,
	// is not null
	NOTNULL,
	// in
	IN,
	// not in
	NOTIN,

	AND,

	OR,

	NOT
}
