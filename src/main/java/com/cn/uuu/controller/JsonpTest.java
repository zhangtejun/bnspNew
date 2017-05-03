package com.cn.uuu.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsonpTest {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("https://note.youdao.com/web/#/file/recent/note/WEB2ae09f32ffb0ae0b6bbb2908d9144be0/").get();
	}
}
