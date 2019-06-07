package com.example.weixin.menu.service;

import com.example.weixin.menu.domain.SelfMenu;

public interface SelfMenuService {

	SelfMenu getMenu();

	void saveMenu(SelfMenu selfMenu);

}
