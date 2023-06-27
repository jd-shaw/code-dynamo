package com.shaw.utils.web;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
public final class WebUtils {
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;
	public final static String ATTRIBUTES = "attributes";
	public final static String PARAMETERS = "parameters";
	public final static String HEADERS = "headers";
	private static final String XHR_OBJECT_NAME = "XMLHttpRequest";
	private static final String HEADER_REQUEST_WITH = "x-requested-with";
	private static final String TEXT_JSON = "text/json";
	private static final String APPLICATION_JSON = "application/json";
	private static final String X_FORWARDED_FOR = "X-Forwarded-For";
	private static final String HEADER_ACCEPT = "accept";
	private static final String UNKNOWN = "unknown";
	private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
	private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
	private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
	private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

	/**
	 * 获得所有的属性，存放在Map中
	 *
	 * @param request
	 * @return
	 */
	public static Map getRequestAttributes(HttpServletRequest request) {
		HashMap hm = new HashMap();
		if (request != null) {
			for (Enumeration e = request.getAttributeNames(); e.hasMoreElements();) {
				String next = (String) e.nextElement();
				hm.put(next, request.getAttribute(next));
			}
		}
		return hm;

	}

	/**
	 * 获得所有的参数，存放在Map中
	 *
	 * @param request
	 * @return
	 */
	public static Map getRequestParameters(HttpServletRequest request) {

		HashMap hm = new HashMap();
		if (request != null) {
			for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
				String next = (String) e.nextElement();
				List values = Arrays.asList(request.getParameterValues(next));
				if (values != null && values.size() == 1) {
					hm.put(next, values.get(0));
				} else {
					hm.put(next, values);
				}
			}
		}
		return hm;

	}

	public static Map getSessionAttributes(HttpSession session) {
		HashMap hm = new HashMap();
		if (session != null) {
			for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
				String next = (String) e.nextElement();
				hm.put(next, session.getAttribute(next));
			}
		}

		return hm;
	}

	/**
	 * 根据Cookie名字获取对应的cookie,如果没有找到，返回null
	 *
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {

		Cookie c = null;
		if (request != null) {
			Cookie[] cookies = request.getCookies();
			boolean found = false;
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length && !found; i++) {
					if (cookies[i] != null && StringUtils.equalsIgnoreCase(cookies[i].getName(), cookieName)) {
						c = cookies[i];
						found = true;
					}
				}
			}
		}
		return c;

	}

	/**
	 * 根据cookie的名字返回其中的值，如果没有此cookie,返回null
	 *
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {

		Cookie c = getCookie(request, cookieName);
		String value = null;
		if (c != null) {
			value = c.getValue();
		}
		return value;

	}

	/**
	 * 获得所有的cookies，存放在Map中
	 *
	 * @param request
	 * @return
	 */
	public static Map getCookies(HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		HashMap hm = new HashMap();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				hm.put(cookies[i].getName(), cookies[i].getValue());
			}
		}
		return hm;

	}

	/**
	 * 返回一个32位的唯一标识来确认此次的请求
	 *
	 * @param request
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnknownHostException
	 */
	public static String generateGUID(HttpServletRequest request)
			throws NoSuchAlgorithmException, UnknownHostException {
		String out = "";
		try {
			// Construct a string that is comprised of:
			// Remote IP Address + Host IP Address + Date (yyyyMMdd) +
			// Time (hhmmssSSa) + Requested Path + Session ID +
			// HashCode Of ParameterMap
			StringBuffer sb = new StringBuffer(1024);
			sb.append(request.getRemoteAddr());
			InetAddress ia = InetAddress.getLocalHost();
			sb.append(ia.getHostAddress());
			sb.append(new SimpleDateFormat("yyyyMMddhhmmssSSa").format(new Date()));
			String path = request.getServletPath();
			String pathInfo = request.getPathInfo();
			if (pathInfo != null) {
				path += pathInfo;
			}
			sb.append(path);
			sb.append(request.getSession(false));
			sb.append(request.getParameterMap().hashCode());
			String str = sb.toString();
			// Now encode the string using an MD5 encryption algorithm.
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(str.getBytes());
			byte[] digest = md.digest();
			StringBuffer hexStr = new StringBuffer(1024);
			for (int i = 0; i < digest.length; i++) {
				str = Integer.toHexString(0xFF & digest[i]);
				if (str.length() < 2) {
					str = "0" + str;
				}
				hexStr.append(str);
			}
			out = hexStr.toString();
		} catch (NoSuchAlgorithmException nsae) {
			throw nsae;
		} catch (UnknownHostException uhe) {
			throw uhe;
		}
		// Return the encrypted string. It should be unique based on the
		// components that comprise the plain text string, and should always be
		// 32 characters thanks to the MD5 algorithm.
		return out;

	}

	/**
	 * 根据提供的Request，返回其所有的内容
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getBodyContent(HttpServletRequest request) throws IOException {

		BufferedReader br = request.getReader();
		String nextLine = "";
		StringBuffer bodyContent = new StringBuffer();
		nextLine = br.readLine();
		while (nextLine != null) {
			bodyContent.append(nextLine);
			nextLine = br.readLine();
		}
		return bodyContent.toString();

	}

	/**
	 * 获得 request 的 Parameter, default 为 "" .
	 *
	 * @param request
	 * @param key
	 *            parameterName
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String key) {
		return getParameter(request, key, "");
	}

	/**
	 * 获得 request 的 Attribute, default 为 "" .
	 *
	 * @param request
	 * @param key
	 *            attributeName
	 * @return
	 */
	public static String getAttribute(HttpServletRequest request, String key) {
		return getAttribute(request, key, "");
	}

	/**
	 * 获得一个 request 的 Parameter.如果没有值，返回提供的默认值
	 *
	 * @param request
	 * @param key
	 * @param def
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String key, String def) {
		String tmpstr;
		if ((tmpstr = request.getParameter(key)) != null)
			return tmpstr;
		else
			return def;
	}

	/**
	 * 获得一个 request 的 attribute.如果没有值，返回提供的默认值
	 *
	 * @param request
	 * @param key
	 * @param def
	 * @return
	 */
	public static String getAttribute(HttpServletRequest request, String key, String def) {
		String tmpstr;
		if ((tmpstr = (String) request.getAttribute(key)) != null)
			return tmpstr;
		else
			return def;
	}

	/**
	 * 获得Web应用的物理根目录
	 *
	 * @param request
	 * @return
	 */
	public static String getRealServerPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 获得Web应用的根目录
	 *
	 * @param request
	 * @return
	 */
	public static String getServerPath(HttpServletRequest request) {
		return (request.getContextPath().equals("/") ? "" : request.getContextPath());
	}

	/**
	 * 获得Web应用的完整根目录url
	 *
	 * @param request
	 * @return
	 */
	public static String getServerUrlPath(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + getServerPath(request);
	}

	/**
	 * 获得有中文字的参数, 转成 UTF-8 encoding, default 为 "" .
	 *
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCParameter(HttpServletRequest request, String name) {
		return getCParameter(request, name, "");
	}

	/**
	 * 获得有中文字的参数, 转成 UTF-8 encoding, 如果没有值，返回默认值 .
	 *
	 * @param request
	 * @param name
	 * @param def
	 * @return
	 */
	public static String getCParameter(HttpServletRequest request, String name, String def) {
		String tmp = "";
		try {
			tmp = new String(getParameter(request, name, def).getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return tmp;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 *
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setDownloadableHeader(HttpServletResponse response, String fileName) {
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 设置过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "max-age=" + expiresSeconds);
	}

	/**
	 * 设置无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		// Http 1.1 header
		response.setHeader("Cache-Control", "max-age=0");
	}

	/**
	 * 检查浏览器客户端是否支持gzip编码.
	 */
	public static boolean checkAccetptGzip(HttpServletRequest request) {
		// Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");

		if (StringUtils.contains(acceptEncoding, "gzip")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置Gzip Header并返回GZIPOutputStream.
	 */
	public static OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已修改. 如果无修改, checkIfModify返回false
	 * ,设置304 not modify status.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header,计算Etag是否无效.
	 *
	 * 如果Etag有效,checkIfNoneMatch返回false, 设置304 not modify status.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!headerValue.equals("*")) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 取得带相同前缀的Request Parameters.
	 *
	 * 返回的结果Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 获取客户端IP
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(X_FORWARDED_FOR);
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(PROXY_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(WL_PROXY_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(HTTP_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(HTTP_X_FORWARDED_FOR);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader(HEADER_REQUEST_WITH);
		String accept = StringUtils.defaultString(request.getHeader(HEADER_ACCEPT));
		if (XHR_OBJECT_NAME.equalsIgnoreCase(requestType) || accept.contains(TEXT_JSON)
				|| accept.contains(APPLICATION_JSON)) {
			return true;
		} else {
			return false;
		}
	}

	public static String readRequestBodyToString(HttpServletRequest request) {
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			Scanner scan = new Scanner(inputStream);
			StringBuilder builder = new StringBuilder();
			while (scan.hasNextLine()) {
				builder.append(scan.nextLine());
			}
			scan.close();
			return builder.toString();
		} catch (IOException e) {
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return null;
	}
}
