package com.start.stockdata.config.jwt;

import com.start.stockdata.config.userDetails.StockUserInfo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.start.stockdata.util.constants.GlobalConstants.*;



public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //private final UserDetailsService userDetailsService;
    private final JwtTokekUtil jwtTokenUtil;

 /*   public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtTokekUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }*/

    public JwtAuthenticationFilter(JwtTokekUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        Long userId = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX_WITH_SPACE)) {
            authToken = header.replace(TOKEN_PREFIX_WITH_SPACE,"");
            try {
              /*   first it will be getAllClaimsFromToken() and check sign!!!!
                 then claims.getString("sub");*/
                userId = jwtTokenUtil.getUserIdFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occurred during getting userId from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.", e);
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // load user from db(bad approach)
            UserDetails userDetails = this.getUserByToken(authToken);

            if (jwtTokenUtil.isTokenNotExpired(authToken)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken,  userDetails);
                /**
                 * Stores additional details about the authentication request. These might be an IP
                 * address, certificate serial number etc.
                 *
                 * @return additional details about the authentication request, or <code>null</code>
                 * if not used
                 */
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + userId + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }

    private User getUserByToken(String authToken) {
        Long userIdFromToken = jwtTokenUtil.getUserIdFromToken(authToken);
        String userEmailFromToken = jwtTokenUtil.getUserEmailFromToken(authToken);
        Collection<GrantedAuthority> authorities = jwtTokenUtil.getAuthoritiesFromToken(authToken);
       return new StockUserInfo(userIdFromToken, userEmailFromToken, authorities);
    }
}