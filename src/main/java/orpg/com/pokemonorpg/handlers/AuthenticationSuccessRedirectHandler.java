package orpg.com.pokemonorpg.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import orpg.com.pokemonorpg.entities.trainer.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessRedirectHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        redirect(request, response, authentication);
    }

    private void redirect(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          Authentication authentication) throws IOException {
        User user = (User)authentication.getPrincipal();
        String redirectURL = "/user/" + user.getUserDetails().getUsername();
        strategy.sendRedirect(httpServletRequest, httpServletResponse, redirectURL);
    }
}
