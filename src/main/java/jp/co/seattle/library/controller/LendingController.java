package jp.co.seattle.library.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.seattle.library.service.LendingService;

@Controller
public class LendingController {
	final static Logger logger = LoggerFactory.getLogger(LendingController.class);

	@Autowired
	private LendingService lendingService;

	/**
	 * 本を借りる
	 * 
	 * @param locale
	 * @param bookId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/lendBook", method = RequestMethod.POST)
	public String lendBook(Locale locale, int bookId, RedirectAttributes redirectAttributes) {
		logger.info("Welcome lendBook! The client locale is {}.", locale);

		// 貸出し状態を確認する
		boolean isLendCount = lendingService.checkLendingStatus(bookId);
		//
		if (isLendCount) {
			redirectAttributes.addFlashAttribute("error", "貸出し済みです。");
		} else {
			// 本を借りる
			//既に貸出管理TBLに登録されているか確認
			Integer lendingBookId = lendingService.getLendingBookId(bookId);
			if(lendingBookId != null) {
				//既にあるレコードの貸出日と返却日を更新
				lendingService.alradyLendBook(bookId);
			}else {
				//貸出管理TBLにINSERT
				lendingService.lendBook(bookId);
			}
		}
		// 詳細画面に遷移する
		return "redirect:/details?bookId=" + bookId;
	}

	/**
	 * 本を返す
	 * 
	 * @param locale
	 * @param bookId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/returnBook", method = RequestMethod.POST)
	public String returnBook(Locale locale, int bookId, RedirectAttributes redirectAttributes) {
		logger.info("Welcome returnBook! The client locale is {}.", locale);

		// 貸出し状態を確認する
		boolean isLendCount = lendingService.checkLendingStatus(bookId);
		//貸出されている場合、返却処理実施。そうではない場合、エラーメッセージ表示
		if (isLendCount) {
			lendingService.returnBook(bookId);
		} else {
			redirectAttributes.addFlashAttribute("error", "貸出しされていません。");
		}

		// 詳細画面に遷移する
		return "redirect:/details?bookId=" + bookId;
	}
}
