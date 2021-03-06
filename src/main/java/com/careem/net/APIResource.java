package com.careem.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.careem.lib.InMemoryStore;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.careem.Constants;
import com.careem.exception.*;
import com.careem.model.ShippoObject;
import com.careem.model.ShippoRawJsonObject;
import com.careem.model.ShippoRawJsonObjectDeserializer;

public abstract class APIResource extends ShippoObject {

	public static final Gson GSON = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.registerTypeAdapter(ShippoRawJsonObject.class,
					new ShippoRawJsonObjectDeserializer()).create();

	private static String className(Class<?> clazz) {
		String className = clazz.getSimpleName().toLowerCase()
				.replace("$", " ");

		if (className.equals("address")) {
			return "address";
		} else if (className.equals("customsitem")) {
			return "customs/item";
		} else if (className.equals("customsdeclaration")) {
			return "customs/declaration";
		} else if (className.equals("carrieraccount")) {
			return "carrier_account";
		} else {
			return className;
		}
	}

	protected static String singleClassURL(Class<?> clazz) {
		return String.format("v1/%s", className(clazz));
	}

	protected static String classURL(Class<?> clazz) {
		return String.format("%ss", singleClassURL(clazz));
	}

	protected static String classURLWithTrailingSlash(Class<?> clazz) {
		return String.format("%ss/", singleClassURL(clazz));
	}

	protected static String instanceURL(Class<?> clazz, String id)
			throws InvalidRequestException {
		try {
			return String.format("%s/%s", classURL(clazz), urlEncode(id));
		} catch (UnsupportedEncodingException e) {
			throw new InvalidRequestException("Unable to encode parameters to "
					+ CHARSET
					+ ". Please contact support@gocareem.com for assistance.",
					null, e);
		}
	}

	public static final String CHARSET = "UTF-8";



	private static final String CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME = "com.careem.net.customURLStreamHandler";

	protected enum RequestMethod {
		GET, POST, PUT
	}

	protected static String urlEncode(String str)
			throws UnsupportedEncodingException {

		if (str == null) {
			return null;
		} else {
			return URLEncoder.encode(str, CHARSET);
		}
	}

	private static String urlEncodePair(String k, String v)
			throws UnsupportedEncodingException {
		return String.format("%s=%s", urlEncode(k), urlEncode(v));
	}

	static Map<String, String> getHeaders(String apiKey) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-Charset", CHARSET);
		headers.put("User-Agent",
				String.format("Careem/v1 JavaBindings/%s", Constants.VERSION));

		if (apiKey == null) {
			apiKey = Constants.apiKey;
		}

		headers.put("Authorization", String.format("CareemToken %s", apiKey));
		headers.put("Accept", "application/json");

		// debug headers
		String[] propertyNames = { "os.name", "os.version", "os.arch",
				"java.version", "java.vendor", "java.vm.version",
				"java.vm.vendor" };

		Map<String, String> propertyMap = new HashMap<String, String>();
		for (String propertyName : propertyNames) {
			propertyMap.put(propertyName, System.getProperty(propertyName));
		}
		headers.put("User-Agent", GSON.toJson(propertyMap));
		if (Constants.apiVersion != null) {
		    headers.put("Careem-API-Version", Constants.apiVersion);
		}
		return headers;
	}

	private static java.net.HttpURLConnection createShippoConnection(
			String url, String apiKey) throws IOException {
		URL shippoURL = null;
		String customURLStreamHandlerClassName = System.getProperty(
				CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, null);
		if (customURLStreamHandlerClassName != null) {
			try {
				Class<URLStreamHandler> clazz = (Class<URLStreamHandler>) Class
						.forName(customURLStreamHandlerClassName);
				Constructor<URLStreamHandler> constructor = clazz
						.getConstructor();
				URLStreamHandler customHandler = constructor.newInstance();
				shippoURL = new URL(null, url, customHandler);
			} catch (Exception e) {
			}
		} else {
			shippoURL = new URL(url);
		}
		java.net.HttpURLConnection conn = (java.net.HttpURLConnection) shippoURL
				.openConnection();
		conn.setConnectTimeout(30 * 1000);
		conn.setReadTimeout(80 * 1000);
		conn.setUseCaches(false);
		for (Map.Entry<String, String> header : getHeaders(apiKey).entrySet()) {
			conn.setRequestProperty(header.getKey(), header.getValue());
		}

		return conn;
	}

	private static void throwInvalidCertificateException()
			throws APIConnectionException {
		throw new APIConnectionException(
				"Invalid server certificate. You tried to connect to a server that has a revoked SSL certificate, which means we cannot securely send data to that server. Please email support@gocareem.com if you need help connecting to the correct API server.");
	}


	private static String formatURL(String url, String query) {
		if (query == null) {
			return url;
		} else {
			String separator = url.contains("?") ? "&" : "?";
			return String.format("%s%s%s", url, separator, query);
		}
	}

	private static java.net.HttpURLConnection createGetConnection(String url,
			String query, String apiKey) throws IOException,
			APIConnectionException {
		String getURL = formatURL(url, query);
		java.net.HttpURLConnection conn = createShippoConnection(getURL, apiKey);
		conn.setRequestMethod("GET");


		return conn;
	}

	private static java.net.HttpURLConnection createPostPutConnection(String url,
			String query, RequestMethod method, String apiKey) throws IOException,
			APIConnectionException {
		java.net.HttpURLConnection conn = createShippoConnection(url, apiKey);
		conn.setDoOutput(true);
		conn.setRequestMethod(method.toString());
		conn.setRequestProperty("Content-Type", "application/json");


		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;

	}

	private static java.net.HttpURLConnection createPutConnection(String url,
			String query, String apiKey) throws IOException,
			APIConnectionException {
		if (Constants.isDEBUG()) {
			System.out.println("PUT URL: " + url);
		}

		java.net.HttpURLConnection conn = createShippoConnection(url, apiKey);
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/json");


		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;

	}

	private static String mapToJson(Map<String, Object> params) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(params);

	}

	private static Map<String, String> flattenParams(Map<String, Object> params)
			throws InvalidRequestException {
		if (params == null) {
			return new HashMap<String, String>();
		}
		Map<String, String> flatParams = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Map<?, ?>) {
				Map<String, Object> flatNestedMap = new HashMap<String, Object>();
				Map<?, ?> nestedMap = (Map<?, ?>) value;
				for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
					flatNestedMap.put(
							String.format("%s[%s]", key, nestedEntry.getKey()),
							nestedEntry.getValue());
				}
				flatParams.putAll(flattenParams(flatNestedMap));
			} else if (value == null) {
				flatParams.put(key, "");
			} else {
				flatParams.put(key, value.toString());
			}
		}
		return flatParams;
	}


	private static class ErrorContainer {
		private APIResource.Error error;
	}

	private static class Error {
		String type;
		String message;
		String code;
		String param;
	}

	private static String getResponseBody(InputStream responseStream)
			throws IOException {
		String rBody = new Scanner(responseStream, CHARSET).useDelimiter("\\A")
				.next(); //

		responseStream.close();
		return rBody;
	}

	private static ShippoResponse makeURLConnectionRequest(
			APIResource.RequestMethod method, String url, String query,
			String apiKey) throws APIConnectionException {
		java.net.HttpURLConnection conn = null;

		try {
			if(method.equals(RequestMethod.GET)){
				conn = createGetConnection(url, query, apiKey);
			}
			else if (method.equals(RequestMethod.POST) || method.equals(RequestMethod.PUT)) {
				conn = createPostPutConnection(url, query, method, apiKey);
			}else{
				throw new APIConnectionException(
						String.format(
								"Unrecognized HTTP method",
								method));
			}

			int rCode = conn.getResponseCode();

			String rBody;
			Map<String, List<String>> headers;

			if (rCode >= 200 && rCode < 300) {
				rBody = getResponseBody(conn.getInputStream());
			} else {
				rBody = getResponseBody(conn.getErrorStream());
			}
			headers = conn.getHeaderFields();

			return new ShippoResponse(rCode, rBody, headers);
		} catch (IOException e) {
			throw new APIConnectionException(
					String.format(""), e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	protected static <T> T request(APIResource.RequestMethod method,
			String url, Map<String, Object> params, Class<T> clazz,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
			return _request(method, url, params, clazz, apiKey);
	}

	protected static <T> T _request(APIResource.RequestMethod method,
			String url, Map<String, Object> params, Class<T> clazz,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {

		if(Constants.inMemory()){
			return GSON.fromJson(InMemoryStore.processRequest(method.toString(), url, params), clazz);
		}else {
			if (apiKey == null) {
				apiKey = Constants.apiKey;
			}

			String query;
			try {
				query = createQuery(params, method);
			} catch (UnsupportedEncodingException e) {
				throw new InvalidRequestException("Unable to encode parameters to", null, null);
			}
			ShippoResponse response = makeURLConnectionRequest(method, url, query,
					apiKey);

			int rCode = response.responseCode;
			String rBody = response.responseBody;

			if (rCode < 200 || rCode >= 300) {
				handleAPIError(rBody, rCode);
			}
			return GSON.fromJson(rBody, clazz);
		}
	}

	private static void handleAPIError(String rBody, int rCode)
			throws InvalidRequestException, AuthenticationException,
			APIException {
		
		APIResource.Error error = new Error();
		error.message = rBody;
		error.code = rCode + "";

		switch (rCode) {
		case 400:
			throw new InvalidRequestException(error.message, error.param, null);
		case 404:
			throw new InvalidRequestException(error.message, error.param, null);
		case 401:
			throw new AuthenticationException(error.message);
		default:
			throw new APIException(error.message, null);
		}
	}
	
    private static String createGETQuery(Map<String, Object> params) throws UnsupportedEncodingException,
    InvalidRequestException {
		Map<String, String> flatParams = flattenParams(params);
		StringBuilder queryStringBuffer = new StringBuilder();
		for (Map.Entry<String, String> entry : flatParams.entrySet()) {
			if (queryStringBuffer.length() > 0) {
				queryStringBuffer.append("&");
			}
			queryStringBuffer.append(urlEncodePair(entry.getKey(), entry.getValue()));
		}
		return queryStringBuffer.toString();
	}

    private static String createQuery(Map<String, Object> params, APIResource.RequestMethod method) throws UnsupportedEncodingException,
    InvalidRequestException {
    	 
		switch (method) {
		case GET:
			return createGETQuery(params);
		case POST:
			return mapToJson(params);
		default:
			return mapToJson(params);
    }

}
}
