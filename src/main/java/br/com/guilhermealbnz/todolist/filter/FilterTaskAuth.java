package br.com.guilhermealbnz.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.guilhermealbnz.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {
            var auth = request.getHeader("Authorization");
            var tokenEncoded = auth.substring(5).trim();
            byte[] tokenDecoded = Base64.getDecoder().decode(tokenEncoded);
            String[] authCredentials = new String(tokenDecoded).split(":");

            var user = this.userRepository.findByUsername(authCredentials[0]);
            if (user == null) {
                response.sendError(403);
            } else {
                var pass = BCrypt.verifyer().verify(authCredentials[1].toCharArray(), user.getPassword());
                if (pass.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(402);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
