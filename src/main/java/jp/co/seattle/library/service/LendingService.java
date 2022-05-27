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
	 * 書籍を借りる（新規で借りる場合）
	 * 
	 * @param bookId
	 */
	public void lendBook(int bookId) {
		String sql = "INSERT INTO lending_manages (book_id,lending_date) values (?,now())";
		jdbcTemplate.update(sql, bookId);
	}
	
	/**
	 * 書籍を借りる（既に借りたことがある場合）
	 * 
	 * @param bookId
	 */
	public void alradyLendBook(int bookId) {
		String sql = "UPDATE lending_manages SET lending_date = now(), return_date = null where book_id = ?";
		jdbcTemplate.update(sql, bookId);
	}

	/**
	 * 書籍が貸出し済みか確認する
	 * 
	 * @param bookId
	 * @return isLend
	 */
	public boolean checkLendingStatus(int bookId) {
		String sql = "SELECT EXISTS(SELECT * FROM lending_manages WHERE book_id = ? AND lending_date IS NOT null) AS is_lend;";
		boolean isLend = jdbcTemplate.queryForObject(sql, boolean.class, bookId);
		return isLend;
	}

	/**
	 * 書籍を返す
	 * 
	 * @param bookId
	 */
	public void returnBook(int bookId) {
		String sql = "UPDATE lending_manages set return_date = now(), lending_date = null where book_id = ?";
		jdbcTemplate.update(sql, bookId);
	}
	
	/**
	 * 貸出情報を取得する
	 * 
	 */
	public List <LendingManegesInfo>  getLendingBookInfo() {
		String sql = "SELECT books.id,books.title,lending_manages.lending_date,lending_manages.return_date FROM lending_manages,books where books.id = lending_manages.book_id";
		List <LendingManegesInfo> lendingManegesInfoList = 
		jdbcTemplate.query(sql, new LendingManegesRowMapper());
		
		return lendingManegesInfoList;
	}
	
	/**
	 * 貸出管理IDを取得する
	 * @param bookId 書籍ID
	 */
	public Integer getLendingBookId(int bookId) {
		String sql = "SELECT id FROM lending_manages where book_id = ?";
		try {
			Integer lendingBookId = jdbcTemplate.queryForObject(sql, Integer.class,bookId);
			return lendingBookId;
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 貸出管理情報を削除する
	 * @param bookId 書籍ID
	 */
	public void deleteLendingInfo(int bookId) {
		String sql = "DELETE FROM lending_manages WHERE book_id = ?";
		jdbcTemplate.update(sql, bookId);
	}
}
