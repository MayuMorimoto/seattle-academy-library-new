package jp.co.seattle.library.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import jp.co.seattle.library.dto.LendingManegesInfo;

@Configuration
public class LendingManegesRowMapper implements RowMapper<LendingManegesInfo>{

	@Override
	public LendingManegesInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// Query結果（ResultSet rs）を、オブジェクトに格納する実装
		LendingManegesInfo lendingManegesInfo = new LendingManegesInfo();

		lendingManegesInfo.setBookId(rs.getInt("id"));
		lendingManegesInfo.setTitle(rs.getString("title"));
		lendingManegesInfo.setLendingDate(rs.getDate("lending_date"));
		lendingManegesInfo.setReturnDate(rs.getDate("return_date"));
		
		return lendingManegesInfo;
	}
}
