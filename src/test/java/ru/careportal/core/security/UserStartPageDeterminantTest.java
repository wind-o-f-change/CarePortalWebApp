package ru.careportal.core.security;

import org.junit.jupiter.api.Test;
import ru.careportal.core.db.model.Role;

import static org.junit.jupiter.api.Assertions.*;

class UserStartPageDeterminantTest {

	@Test
	void shouldDefineNotNullPages() {
		UserStartPageDeterminant determinant = new UserStartPageDeterminant();
		for (Role role : Role.values()) {
			assertNotNull(determinant.definePageByRole(role));
		}
	}

	@Test
	void shouldDefineNotEmptyPages() {
		UserStartPageDeterminant determinant = new UserStartPageDeterminant();
		for (Role role : Role.values()) {
			assertFalse(determinant.definePageByRole(role).isEmpty());
		}
	}
}