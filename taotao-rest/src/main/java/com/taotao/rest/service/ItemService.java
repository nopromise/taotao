package com.taotao.rest.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * 查询商品实体对象Service
 * 
 * @author fanjunlin
 *
 */
public interface ItemService {
	TbItem getItemById(Long itemId);

	// 获得商品详情对象
	TbItemDesc getItemDescById(Long itemId);

	// 获得规格参数对象
	TbItemParamItem getItemParamById(Long itemId);

}
