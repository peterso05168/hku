package com.accentrix.hku.util;

import java.io.UnsupportedEncodingException;
import java.util.BitSet;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);

    public final static String CHARSET = "UTF-8";

    private HttpClient httpclient;

    private Credentials credentials;

    private HttpClientUtil() {
    }

    public void setHttpclient(HttpClient httpclient) {
        this.httpclient = httpclient;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * send get request
     * 
     * @param url
     * @return
     */
    public String sendRequestAsGet(String url) throws HttpException {
        GetMethod method = new GetMethod(url);
        int statusCode = 0;
        try {
            // TODO
            // getMethod.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            httpclient.getState().setCredentials(AuthScope.ANY, credentials);

            statusCode = httpclient.executeMethod(method);
            String result = method.getResponseBodyAsString();
            if (LOG.isInfoEnabled()) {
                LOG.info("GET URL:" + url);
                LOG.info("get rquest status:" + statusCode);
                LOG.info("result:" + result);

                Header[] headers = method.getRequestHeaders();
                LOG.info("-------- RequestHeaders ------");
                for (Header header : headers) {
                    LOG.info("{} = {}", header.getName(), header.getValue());
                }

                headers = method.getResponseHeaders();
                LOG.info("-------- ResponseHeaders ------");
                for (Header header : headers) {
                    LOG.info("{} = {}", header.getName(), header.getValue());
                }
            }
            return result;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new HttpException(e.getMessage(), e);
        } finally {
            method.releaseConnection();
            if (statusCode != 200) {
                throw new HttpException("statusCode is not 200");
            }
        }
    }

    /**
     * judge request is available
     * 
     * @param url
     * @return
     */
    public boolean isActive(String url) {
        boolean flag = false;
        GetMethod getMethod = new GetMethod(URLEncode(url, "UTF-8"));
        try {
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            int statusCode = httpclient.executeMethod(getMethod);
            if (statusCode == 200) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return flag;
        } finally {
            getMethod.releaseConnection();
        }
    }

    /**
     * send post request
     * 
     * @param url
     * @return
     */
    public int sendRequestAsPost(String url, String eq1Value) {
        PostMethod method = new PostMethod(url);
        try {
            // "http://dcms-ibm-dev.pd.ntt.hk:81/maxrest/rest/os/mxasset/9"
            // http://dcms-ibm-dev.pd.ntt.hk:81/maxrest/rest/os/mxasset?EQ1=accentrix&ASSETID=~eq~9&_action=Change
            LOG.info("POST URL:" + url);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

            method.addParameter("nttpmapchk", eq1Value);

            int statusCode = httpclient.executeMethod(method);

            if (LOG.isInfoEnabled()) {
                LOG.info("Post Rquest Status:" + statusCode);

                Header[] headers = method.getRequestHeaders();
                LOG.info("-------- RequestHeaders ------");
                for (Header header : headers) {
                    LOG.info("{} = {}", header.getName(), header.getValue());
                }

                headers = method.getResponseHeaders();
                LOG.info("-------- ResponseHeaders ------");
                for (Header header : headers) {
                    LOG.info("{} = {}", header.getName(), header.getValue());
                }
            }

            return statusCode;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return 500;
        } finally {
            method.releaseConnection();
        }
    }

    private static final BitSet allowed_query = new BitSet(256);

    static {
        for (int i = '0'; i <= '9'; i++) {
            allowed_query.set(i);
        }

        for (int i = 'a'; i <= 'z'; i++) {
            allowed_query.set(i);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            allowed_query.set(i);
        }

        allowed_query.set('-');
        allowed_query.set('_');
        allowed_query.set('.');
        allowed_query.set('!');
        allowed_query.set('~');
        allowed_query.set('*');
        allowed_query.set('\'');
        allowed_query.set('(');
        allowed_query.set(')');

        // Rick added = here i.e. do not encode =
        allowed_query.set('=');
        // Rick: added & here i.e. do not encode &
        allowed_query.set('&');
    }

    /**
     * 
     * @param original
     * @return
     */
    public static String URLEncode(String original) {
        return URLEncode(original, CHARSET);
    }

    /**
     * Encodes URI string. This is a replacement for the
     * java.net.URLEncode#encode(String, String) class which is broken under JDK
     * 1.3.
     * <p/>
     * 
     * @param original
     *            the original character sequence
     * @param charset
     *            the protocol charset
     * @return URI character sequence
     * @throws UnsupportedEncodingException
     *             unsupported character encoding
     */
    public static String URLEncode(String original, String charset) {
        // encode original to uri characters.
        if (original == null) {
            return null;
        }

        // escape octet to uri characters.
        byte[] octets = null;

        try {
            octets = original.getBytes(charset);
        } catch (UnsupportedEncodingException error) {
            LOG.error(error.getMessage(), error);
            return original;
        }

        StringBuilder buf = new StringBuilder(octets.length);

        for (byte octet : octets) {
            char c = (char) octet;
            if (allowed_query.get(c)) {
                buf.append(c);
            } else {
                buf.append('%');
                char hexadecimal = Character.forDigit((octet >> 4) & 0xF, 16);
                buf.append(Character.toUpperCase(hexadecimal)); // high
                hexadecimal = Character.forDigit(octet & 0xF, 16);
                buf.append(Character.toUpperCase(hexadecimal)); // low
            }
        }

        return buf.toString();
    }
}
