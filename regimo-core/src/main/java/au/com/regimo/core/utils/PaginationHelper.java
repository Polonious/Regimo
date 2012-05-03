package au.com.regimo.core.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class PaginationHelper<E> {

    public Page<E> fetchPage(
            final JdbcTemplate jt,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Object args[],
            final Page<E> page,
            final ParameterizedRowMapper<E> rowMapper) {

        // determine how many rows are available
        final int rowCount = jt.queryForInt(sqlCountRows, args);

        // calculate the number of pages
        int pageCount = rowCount / page.getPageSize();
        if (rowCount > page.getPageSize() * pageCount) {
            pageCount++;
        }

        page.setRowCount(rowCount);
        page.setPagesAvailable(pageCount);

        // fetch a single page of results
        final int startRow = (page.getPageNumber() - 1) * page.getPageSize() + 1;
        jt.query(
                sqlFetchRows,
                args,	
                new ResultSetExtractor<Page<E>>() {
                    public Page<E> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    	rs.setFetchSize(page.getPageSize());
                    	rs.setFetchDirection(ResultSet.FETCH_FORWARD);
                    	rs.absolute(startRow);
                        final List<E> items = page.getItems();
                        int currentRow = 0;
                        while (rs.next()) {
                            items.add(rowMapper.mapRow(rs, currentRow));
                            currentRow++;
                        }
                        return page;
                    }
                });
        return page;
    }

}

