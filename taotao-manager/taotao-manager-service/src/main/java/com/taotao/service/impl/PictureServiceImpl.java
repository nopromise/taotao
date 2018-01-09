package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.FastDFSClient;
import com.taotao.common.PictureResult;
import com.taotao.service.PictureService;

/**
 * 图片上传service
 * 
 * @author fanjunlin
 *
 */
@Service
public class PictureServiceImpl implements PictureService {
	@Value("${IMAGE_SERVER_HOST}")
	private String imageServeHost;

	@Override
	public PictureResult uploadPic(MultipartFile picFile) {
		PictureResult result = new PictureResult();
		// 判断图片是否为空
		if (picFile.isEmpty()) {
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		// 上传到图片服务器
		try {

			// 取图片扩展名
			String originalFilename = picFile.getOriginalFilename();
			// 取图片的扩展名不要“.”
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			FastDFSClient client = new FastDFSClient(
					"F:\\eclipse-oxygen-workspace\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
			String res = client.uploadFile(originalFilename, extName);
			res = imageServeHost + res;
			// 把url响应给客户端
			result.setError(0);
			result.setUrl(res);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);
			result.setMessage("图片上传失败");
		}
		return result;
	}

}
