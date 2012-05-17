package au.com.regimo.server.wordpress.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import au.com.regimo.server.wordpress.domain.WpTerm;
import au.com.regimo.server.wordpress.domain.WpTermTaxonomy;
import au.com.regimo.server.wordpress.repository.WpTermRepository;

public class WpTermRepositoryImpl implements WpTermRepository {

	private JdbcTemplate jdbcTemplate;
	
	private final ParameterizedRowMapper<WpTerm> mapper = new ParameterizedRowMapper<WpTerm>() {
		public WpTerm mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			WpTerm entity = new WpTerm();
			entity.setTermId(resultSet.getLong("term_id"));
			entity.setName(resultSet.getString("name"));
			entity.setSlug(resultSet.getString("slug"));
			return entity;
		}
	};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public List<WpTerm> findByTaxonomy(String taxonomy) {
		return jdbcTemplate.query("select * from wp_terms where term_id in (select term_id from wp_term_taxonomy where count>0 and taxonomy = ?)", 
				new Object[]{taxonomy}, mapper);
	}
	
	public List<WpTerm> findByTaxonomyCategory() {
		return findByTaxonomy(WpTermTaxonomy.AXONOMY_CATEGORY);
	}
	
	public String getNameBySlug(String slug) {
		return jdbcTemplate.queryForObject("select * from wp_terms where slug = ?",
				new Object[]{slug}, mapper).getName();
		
	}
}
