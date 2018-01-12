
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class Demo1 {
	@Test
	public void testHttpClient() {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.itheima.com");
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			String string = EntityUtils.toString(entity);
			System.out.println(string);
			httpResponse.close();
			httpClient.close();

		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

	@Test
	public void testHttpClientPost() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8082/posttest.html");
		try {
			List<NameValuePair> formList = new ArrayList<>();
			formList.add(new BasicNameValuePair("name", "张三"));
			formList.add(new BasicNameValuePair("pass", "1243"));
			
			StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
			httpPost.setEntity(entity);
			// 第五步：执行请求。
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// 第六步：接收返回结果
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity);
			System.out.println(result);
			// 第七步：关闭流。
			response.close();
			httpClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
