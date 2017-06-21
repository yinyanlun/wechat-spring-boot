package com.yin.wechat.utils;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpRequest {

	 /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param)  throws Exception{
        String result = "";
        BufferedReader in = null;
  //      try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("charsert", "UTF-8");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
//        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) throws Exception{
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
//        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            System.out.println("URLConnection  Go");
            
            //entity为请求体部分内容  
            //如果有中文则以UTF-8编码为username=%E4%B8%AD%E5%9B%BD&password=123    
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("charsert", "UTF-8");

            System.out.println("URLConnection  GoGO");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            System.out.println("URLConnection  GoGOGO");
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
   
            // 发送请求参数
            out.write(param);
           
            // flush输出流的缓冲
            out.flush();
            System.out.println("URLConnection  GoGOGOGO");
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("result====="+result);
//            byte[] b = result.getBytes("utf-8");//编码  
//            result = new String(b, "utf-8");
//            result= result.replace("?/", "</");
        
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！"+e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
        return result;
    }   
    @SuppressWarnings("resource")
	public static String sendHttpPost(String url,String paramName,String param,String charSet){
		 String response="";
		HttpClient httpClient = new DefaultHttpClient();
	      HttpPost httpPost = new HttpPost(url);
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair(paramName,param));
	      UrlEncodedFormEntity entity = null;
	      try {
	          entity = new UrlEncodedFormEntity(params,charSet);
	      } catch (UnsupportedEncodingException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      }
	      httpPost.setEntity(entity);
	      try {
	          HttpResponse httpResponse = httpClient.execute(httpPost);
	          if(httpResponse.getStatusLine().getStatusCode() == 200){
	              System.out.println("请求和响应成功");
	              HttpEntity httpEntity = httpResponse.getEntity();
	              response = EntityUtils.toString(httpEntity);
	              System.out.println(response);
	          }
	      } catch (ClientProtocolException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      }
		return  response;
		
	}
}
