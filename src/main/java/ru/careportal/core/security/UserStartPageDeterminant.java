package ru.careportal.core.security;

import ru.careportal.core.db.model.Role;

/**
 * Defines pages depends on users or their roles.
 * Класс опредляет стартовую/первую страницу в зависимости от пользователя или роли пользовтеля
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
