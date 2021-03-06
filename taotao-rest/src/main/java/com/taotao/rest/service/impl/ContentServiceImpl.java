package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;// 注入jedisClient实例，具体实现在配置中选择是用单机版还是集群版

	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;

	@Override
	public List<TbContent> getContentList(Long cid) {
		// 添加缓存
		// 查询数据库之前先查询缓存，如果有直接返回

		try {
			// 从redis中取缓存数据
			String json = jedisClient.hget(REDIS_CONTENT_KEY, cid + "");
			if (!StringUtils.isBlank(json)) {
				// 把json转换成List
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				System.out.println("从redis中获取数据");
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据cid查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

		// 返回结果之前，向缓存中添加数据【不能影响正常业务逻辑，即便出错，还能正常返回】
		try {
			// 为了规范key，可以用hash
			// 定义一个保存内容的key，hash中每个项就是cid
			// value是list，需要把list转换成json数据。
			System.out.println("数据保存到了redis中");
			jedisClient.hset(REDIS_CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TaotaoResult syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid + "");
		return TaotaoResult.ok();
	}

}
