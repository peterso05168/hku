package com.accentrix.hku.util.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.domain.common.ResponseData;
import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.util.jwt.JWT;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月2日 上午10:22:53
 * @version 1.0
 */

public class CheckTokenFilter implements Filter {

    public static final String INDEX_URL = "/index.xhtml";

    public static final ThreadLocal<Long> USER_EXP = new ThreadLocal<>();

    private static final String TOKEN = "token";
    private static final String EXP_TIME = "expTime";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("utf-8");
        ResponseData responseData = ResponseData.ok();

        String token = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(UserUtils.TOKEN)) {
                    token = cookie.getValue();
                }
            }
        }

        // token不存在
        if (StringUtils.isNotBlank(token)) {
            String loginId = token.substring(0, 32);
            Map<String, String> map = extendTokenTime(loginId, token.substring(32, token.length()), httpServletRequest,
                    httpServletResponse);
            AccountVo vo = JWT.unsign(map.get(TOKEN), AccountVo.class);
            // 解密token后的loginId与用户传来的loginId不一致，一般都是token过期

            if (null != loginId && null != vo) {
                if (!loginId.equals(vo.getId())) {
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + INDEX_URL);
                    responseData = ResponseData.forbidden();
                    responseMessage(httpServletResponse, httpServletResponse.getWriter(), responseData);
                    return;
                }
                USER_EXP.set(Long.parseLong(map.get(EXP_TIME)));
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + INDEX_URL);
                responseData = ResponseData.forbidden();
                responseMessage(httpServletResponse, httpServletResponse.getWriter(), responseData);
                return;
            }
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + INDEX_URL);
            responseData = ResponseData.forbidden();
            responseMessage(httpServletResponse, httpServletResponse.getWriter(), responseData);
            return;
        }
    }

    private Map<String, String> extendTokenTime(String loginId, String token, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            final JWTVerifier verifier = new JWTVerifier(JWT.SECRET);
            final Map<String, Object> claims = verifier.verify(token);
            if (claims.containsKey(JWT.EXP) && claims.containsKey(JWT.PAYLOAD)) {
                long exp = (Long) claims.get(JWT.EXP);
                long currentTimeMillis = System.currentTimeMillis();
                long minTime = ConstantsUtils.MIN_TIME;
                if (exp > currentTimeMillis && exp - currentTimeMillis > 0L && exp - currentTimeMillis <= minTime) {
                    final JWTSigner signer = new JWTSigner(JWT.SECRET);
                    claims.put(JWT.EXP, System.currentTimeMillis() + ConstantsUtils.EXTEND_TIME);
                    token = signer.sign(claims);

                    Cookie cookie = new Cookie(UserUtils.TOKEN, loginId + token);
                    cookie.setPath(request.getContextPath() + "/");
                    response.addCookie(cookie);

                    map.put(TOKEN, token);
                    map.put(EXP_TIME, (System.currentTimeMillis() + ConstantsUtils.EXTEND_TIME) / 1000000 + "");
                } else {
                    map.put(TOKEN, token);
                    map.put(EXP_TIME, exp / 1000000 + "");
                }
            }
        } catch (Exception e) {
            map.put(TOKEN, token);
            map.put(EXP_TIME, "0L");
            return map;
        }
        return map;
    }

    @Override
    public void destroy() {

    }

    // 请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }

}
