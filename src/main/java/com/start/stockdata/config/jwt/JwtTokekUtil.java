package com.start.stockdata.config.jwt;

import com.start.stockdata.util.constants.GlobalConstants;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static com.start.stockdata.util.constants.GlobalConstants.*;

@Component
public class JwtTokekUtil {

    public Long getUserIdFromToken(String token) {
        Optional<Long> optionalUserId = Optional.ofNullable(getClaimFromToken(token, x->x.get(USER_ID,Long.class)));
        if(!optionalUserId.isPresent()){
            throw new IllegalArgumentException(USER_ID + " doesn't present in the token");
        } else {
            return optionalUserId.get();
        }
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        // claims.getString("sub");
        // claims.get(Claims.EXPIRATION, Date.class);
        return claimsResolver.apply(claims);
    }

    //Evaluate: {sub=user2, scopes=ROLE_USER, iat=1584700835, exp=1584718835}
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                // Very important to specify encoding
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private byte[] getSigningKey() {
        return SIGNING_KEY.getBytes(StandardCharsets.UTF_8);
    }

    public boolean isTokenNotExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                // key - "sub"
                .setSubject(authentication.getName())
                //Sets a custom JWT Claims parameter value
                .claim(GlobalConstants.ROLES, authorities)
                .signWith(SignatureAlgorithm.forName(SIGNATURE_ALGORITHM), getSigningKey())
                // key - 'iat'
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 18000 seconds = 5 hours
                // key - 'exp'
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY_MILLISECONDS))
                .compact();
    }

/*    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }*/

    UsernamePasswordAuthenticationToken getAuthentication(final String token, final UserDetails userDetails) {

        Collection<GrantedAuthority> authorities = getAuthoritiesFromToken(token);

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public Collection<GrantedAuthority> getAuthoritiesFromToken(String token) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(getSigningKey());
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();

        List<String> authorities = (ArrayList<String>)claims.get(ROLES);

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String getUserEmailFromToken(String token) {
        Optional<String> optionalUserId = Optional.ofNullable(getClaimFromToken(token, x->x.get(EMAIL,String.class)));
        if(!optionalUserId.isPresent()){
            throw new IllegalArgumentException(EMAIL + " doesn't present in the token");
        } else {
            return optionalUserId.get();
        }
    }
}
