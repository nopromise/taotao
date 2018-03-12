package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;

public interface LoginService {

	TaotaoResult login(String userName, String password, HttpServletRequest request, HttpServletResponse responseF);

	TaotaoResult getUserByToken(String token);
}
