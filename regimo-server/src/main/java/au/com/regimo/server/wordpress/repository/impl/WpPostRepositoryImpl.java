package au.com.regimo.server.wordpress.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import au.com.regimo.server.wordpress.domain.WpPost;
import au.com.regimo.server.wordpress.repository.WpPostRepository;

public class WpPostRepositoryImpl implements WpPostRepository {

	private JdbcTemplate jdbcTemplate;
	
	private final ParameterizedRowMapper<WpPost> mapper = new ParameterizedRowMapper<WpPost>() {
		public WpPost mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			WpPost entity = new WpPost();
			entity.setId(resultSet.getLong("ID"));
			entity.setPostTitle(resultSet.getString("post_title"));
			String noCaptionString = resultSet.getString("post_content").replaceAll("\\[caption.*?\\]", "").replace("[/caption]", "");
			entity.setPostContent(noCaptionString);
			entity.setPostName(resultSet.getString("post_name"));
			entity.setPostExcerpt(resultSet.getString("post_excerpt"));
			entity.setPostDate(resultSet.getDate("post_date"));
			return entity;
		}
	};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<WpPost> findBySlug(String slug) {
		return jdbcTemplate.query("select p.* FROM wp_posts p, wp_term_relationships r, wp_term_taxonomy t, wp_terms te where p.ID=r.object_id and t.term_taxonomy_id=r.term_taxonomy_id and t.term_id = te.term_id and p.post_status='publish' and te.slug = ? and p.post_type= ? order by p.post_date desc", 
				new Object[]{slug, WpPost.TYPE_POST}, mapper);
	}

	public WpPost findByPostName(String postName) {
		return jdbcTemplate.queryForObject("select * FROM wp_posts where post_name= ?", 
				new Object[]{postName}, mapper);
	}

}
