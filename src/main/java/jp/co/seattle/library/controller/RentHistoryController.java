package jp.co.seattle.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.seattle.library.dto.LendingManegesInfo;
import jp.co.seattle.library.service.LendingService;

/**
 * 貸出履歴コントローラー
 */
@Controller // APIの入り口
public class RentHistoryController {
	final static Logger logger = LoggerFactory.getLogger(RentHistoryController.class);
	
	@Autowired LendingService lendingService;
	@RequestMapping(value = "/rentHistory", method = RequestMethod.GET)
	/**
	 * 貸出履歴画面初期表示
	 * @param model
	 * @return 貸出履歴画面に遷移
	 */
	public String createAccount(Model model) {
		//画面表示内容を取得する
		List<LendingManegesInfo> lendingManegesInfoList = lendingService.getLendingBookInfo();
		model.addAttribute("lendingManegesInfoList", lendingManegesInfoList);
		return "rentHistory";
	}
	
}
