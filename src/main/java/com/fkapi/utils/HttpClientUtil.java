package com.fkapi.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({AssertionListener.class})
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
			.setConnectionRequestTimeout(30000).build();

	private static HttpClientUtil instance = null;

	private HttpClientUtil() {
	}

	public static HttpClientUtil getInstance() {
		if (instance == null) {
			instance = new HttpClientUtil();
		}
		return instance;
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public String sendHttpPost(String httpUrl) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 */
	public static String sendHttpPost(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	/**
	 * 发送 post请求
	 * @param httpUrl
	 * @param json
	 * @return
	 */
	public static String sendHttpPost(String httpUrl, JSONObject json) {
		HttpPost httpPost = new HttpPost(httpUrl);
		try {
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	/**
	 * 
	 * @param CookieUrl
	 * @param Cookiejson
	 * @param httpUrl
	 * @param json
	 * @return
	 * @throws Exception 
	 */
	public static String sendHttpPost(String CookieUrl, String Cookiejson,String httpUrl,String json) throws Exception {
		String cookieValue = setCookie(CookieUrl, Cookiejson);
		HttpPost httpPost = new HttpPost(httpUrl);
		try {
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			httpPost.addHeader(new BasicHeader("Cookie", cookieValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 */
//	public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
//		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
//		// 创建参数队列
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		for (String key : maps.keySet()) {
//			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
//		}
//		try {
//			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return sendHttpPost(httpPost);
//	}

	/**
	 * 发送 post请求（带文件）
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 */
	public static String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		System.out.println(sendHttpPost(httpPost));
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost) {
		BasicConfigurator.configure();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity ;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().toString().contains("200")) {
				Reporter.log("请求成功接口返回:"+ response.getStatusLine());
				entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}else {
				Reporter.log("请求失败接口返回:"+ response.getStatusLine());
				Assertion.verifyTure(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 */
	public static String sendHttpGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpGet(httpGet);
	}

	/**
	 * 发送 get请求Https
	 * 
	 * @param httpUrl
	 */
	public static String sendHttpsGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpsGet(httpGet);
	}

	/**
	 * 发送Get请求
	 * 
	 * @param httpGet
	 * @return
	 */
	private static String sendHttpGet(HttpGet httpGet) {
		BasicConfigurator.configure();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().toString().contains("200")) {
				logger.info("接口返回:"+ response.getStatusLine());
				entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}else {
				logger.info("接口返回:"+ response.getStatusLine());
				Assertion.verifyTure(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送Get请求Https
	 * 
	 * @param httpGet
	 * @return
	 */
	private static String sendHttpsGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
					.load(new URL(httpGet.getURI().toString()));
			DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
			httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().toString().contains("200")) {
				logger.info("接口返回:"+ response.getStatusLine());
				entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}else {
				logger.info("接口返回:"+ response.getStatusLine());
				Assertion.verifyTure(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	
	
	/**
	 * 发送 put请求
	 * @param httpUrl
	 * @param json
	 * @return
	 */
	public static String sendHttpPut(String httpUrl, JSONObject json) {
		HttpPut httpPut = new HttpPut(httpUrl);
		try {
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
			stringEntity.setContentType("application/json");
			httpPut.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPut(httpPut);
	}

	/**
	 * 发送 put请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 */
//	public static String sendHttpPut(String httpUrl, Map<String, String> maps) {
//		HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
//		// 创建参数队列
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		for (String key : maps.keySet()) {
//			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
//		}
//		try {
//			httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return sendHttpPut(httpPut);
//	}

	/**
	 * 发送 put请求（带文件）
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 */
	public static String sendHttpPut(String httpUrl, Map<String, String> maps, List<File> fileLists) {
		HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPut.setEntity(reqEntity);
		System.out.println(sendHttpPut(httpPut));
		return sendHttpPut(httpPut);
	}
	/**
	 * 发送Put请求
	 * 
	 * @param httpPut
	 * @return
	 */
	private static String sendHttpPut(HttpPut httpPut) {
		BasicConfigurator.configure();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPut.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPut);
			if (response.getStatusLine().toString().contains("200")) {
				logger.info("接口返回:"+ response.getStatusLine());
				entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}else {
				logger.info("接口返回:"+ response.getStatusLine());
				Assertion.verifyTure(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	
	public static Map<String,String> cookieMap = new HashMap<String, String>(64);
    //从响应信息中获取cookie
    public static String setCookie(String url,String json) throws Exception
    {
    	RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
		stringEntity.setContentType("application/json");
		post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println("----setCookieStore");
        Header headers[] = response.getHeaders("Set-Cookie");
        if (headers == null || headers.length==0)
        {
            System.out.println("----there are no cookies");
            return null;
        }
        String cookie = "";
        for (int i = 0; i < headers.length; i++) {
            cookie += headers[i].getValue();
            if(i != headers.length-1)
            {
                cookie += ";";
            }
        }
 
        String cookies[] = cookie.split(";");
        for (String c : cookies)
        {
            c = c.trim();
            if(cookieMap.containsKey(c.split("=")[0]))
            {
                cookieMap.remove(c.split("=")[0]);
            }
            cookieMap.put(c.split("=")[0], c.split("=").length == 1 ? "":(c.split("=").length ==2?c.split("=")[1]:c.split("=",2)[1]));
        }
        System.out.println("----setCookieStore success");
        String cookiesTmp = "";
        for (String key :cookieMap.keySet())
        {
            cookiesTmp +=key+"="+cookieMap.get(key)+";";
        }
        System.out.println(cookiesTmp.substring(0,cookiesTmp.length()-2));
        return cookiesTmp.substring(0,cookiesTmp.length()-2);
    }
    
    @Test
    public void test() throws Exception{
    	setCookie("http://192.168.101.201:3000/login", "{\"uname\":\"hu_999\",\"pswd\":\"8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92\",\"vcode\":\"\"}");
    }
}