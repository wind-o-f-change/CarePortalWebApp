package ru.careportal.core.security;

import ru.careportal.core.db.model.Role;

import javax.persistence.RollbackException;

/**
 * Defines pages depends on users or their roles
 */
class UserStartPageDeterminant {
	/**
	 * @param role user role
	 * @return user start page
	 */
	String definePageByRole(Role role) {
		switch (role) {
			case ROLE_ADMIN:
				return "/admin";
			case ROLE_DOCTOR:
				return "/doctor";
			case ROLE_PATIENT:
				return "/patient";
			default:
				throw new IllegalStateException("Unexpected value: " + role);
		}
	}
}
