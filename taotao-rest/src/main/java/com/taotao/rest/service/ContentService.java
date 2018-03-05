package com.taotao.rest.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(Long cid);
	/**
	 * 同步redis缓存与mysql数据库的方法，当mysql数据库中内容改变，就把redis的缓存删掉。
	 */
	TaotaoResult syncContent(Long cid);
}
