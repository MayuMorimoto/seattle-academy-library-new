package jp.co.seattle.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jp.co.seattle.library.dto.LendingManegesInfo;
import jp.co.seattle.library.rowMapper.LendingManegesRowMapper;

@Service
public class LendingService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 書籍を借りる
	 * 
	 * @param bookId
	 */
	public void lendBook(int bookId) {
		String sql = "INSERT INTO lending_manages (book_id,lending_date) values (?,now())";
		jdbcTemplate.update(sql, bookId);
	}

	/**
	 * 書籍が貸出し済みか確認する
	 * 
	 * @param bookId
	 * @return isLend
	 */
	public Integer checkLendingStatus(int bookId) {
		String sql = "SELECT count(*) FROM lending_manages WHERE book_id = ? AND return_date IS null;";
		Integer isLendCount;
		try {
			isLendCount = jdbcTemplate.queryForObject(sql, Integer.class, bookId);
		}catch (Exception e) {
			isLendCount = 0;
		}
		
		return isLendCount;
	}

	/**
	 * 書籍を返す
	 * 
	 * @param bookId
	 */
	public void returnBook(int bookId) {
		String sql = "UPDATE lending_manages set return_date = now() where book_id = ?";
		jdbcTemplate.update(sql, bookId);
	}
	
	/**
	 * 貸出情報を取得する
	 * 
	 */
	public List <LendingManegesInfo>  getLendingBookInfo() {
		String sql = "SELECT * FROM lending_manages";
		List <LendingManegesInfo> lendingManegesInfoList = 
		jdbcTemplate.query(sql, new LendingManegesRowMapper());
		
		return lendingManegesInfoList;
	}
	
	/**
	 * 貸出管理IDを取得し、booksTBLを更新する
	 * @param bookId 書籍ID
	 */
	public void updateBooksLendingBookId(int bookId) {
		String sql = "update books set lending_id = (SELECT id FROM lending_manages where book_id = ? AND return_date IS NULL) where id = ?";
		jdbcTemplate.update(sql,bookId,bookId);
	}
	
	
	/**
	 * 貸出管理IDを取得する
	 * @param bookId 書籍ID
	 */
	public Integer getLendingBookId(int bookId) {
		String sql = "SELECT id FROM lending_manages where book_id = ? AND return_date IS NULL";
		try {
			Integer lendingBookId = jdbcTemplate.queryForObject(sql, Integer.class,bookId);
			return lendingBookId;
		}catch (Exception e) {
			return null;
		}
	}
}
