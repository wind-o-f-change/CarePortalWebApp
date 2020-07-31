package ru.careportal.core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.careportal.core.db.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Пользовательский обработчик успешной аутентификации.
 * Определяет стратегию, используемую для обработки успешной аутентификации пользователя.
 */
@Slf4j
public class DependsOnRoleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    /**
     * Вызывается, когда пользователь успешно прошел аутентификацию.
     * @param request запрос, вызвавший успешную аутентификацию
     * @param response ответ
     * @param authentication объект Authentication, который был создан во время
     * процесса аутентификации.
     */
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    /**
     * Вспомогательный метод для определения целевой URL и последующего перенаправления пользователя
     * @param request  запрос, вызвавший успешную аутентификацию
     * @param response ответ
     * @param authentication объект Authentication, который был создан во время
     * процесса аутентификации.
     */
    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.warn("Статус ответа уже получен, невозможно перенаправить на " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Определяет целевую URL для перенаправления пользователя после аутентификации
     * исходя из роли
     * @param authentication объект Authentication, который был создан во время
     * процесса аутентификации.
     * @return String  - строка, содержащая целевую URL
     */

    protected String determineTargetUrl(final Authentication authentication) {
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {

            String authorityName = grantedAuthority.getAuthority();
            Role role = Role.valueOf(authorityName);
            return new UserStartPageDeterminant().definePageByRole(role);
        }

        throw new IllegalStateException();
    }

    /**
     * Удаляет временные данные, связанные с аутентификацией, которые могли быть сохранены в сеансе
     * во время процесса аутентификации.
     */
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
