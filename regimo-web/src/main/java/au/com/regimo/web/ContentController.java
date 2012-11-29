package au.com.regimo.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import au.com.regimo.cms.domain.Article;
import au.com.regimo.cms.domain.Category;
import au.com.regimo.cms.service.ArticleService;
import au.com.regimo.cms.service.CategoryService;
import au.com.regimo.core.domain.UserDashlet;
import au.com.regimo.core.repository.UserDashletRepository;
import au.com.regimo.core.service.SecurityService;
import au.com.regimo.core.utils.SecurityUtils;
import au.com.regimo.core.utils.TextGenerator;
import au.com.regimo.server.wordpress.domain.WpPost;
import au.com.regimo.server.wordpress.domain.WpTerm;
import au.com.regimo.server.wordpress.repository.WpPostRepository;
import au.com.regimo.server.wordpress.repository.WpTermRepository;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Controller
@RequestMapping("/content")
public class ContentController {

	private CategoryService categoryService;
	private ArticleService articleService;

	private WpTermRepository wpTermRepository;
	private WpPostRepository wpPostRepository;

	private UserDashletRepository userDashletRepository;
	private SecurityService securityService;

	private String cmsProvider = ""; // WordPress

	@RequestMapping(value = "/category/{slug}")
	public String getPostsByCategory(@PathVariable String slug, ModelMap map) {
		if(fromWordPress()){
			WpTerm wpTerm = wpTermRepository.findBySlug(slug);
			map.addAttribute("category", getMappedCategory(wpTerm));
		}
		else{
			Category category = categoryService.findBySlug(slug);
			map.addAttribute("category", category);
		}
		return "content/category";
	}

	@RequestMapping(value = "/article/{slug}")
	public String getPost(@PathVariable String slug, ModelMap map) {
		map.addAttribute("article", fromWordPress() ?
			wpPostRepository.findByPostName(slug):
			articleService.findBySlug(slug));
		return "content/article";
	}

	@RequestMapping(value = "/importFromWordPress")
	public String importFromWorldPress() {
		List<WpTerm> terms = wpTermRepository.findByTaxonomyCategory();
		for(WpTerm term : terms){
			Category category = getMappedCategory(term);
			Set<Category> categories = Sets.newHashSet(category);
			categoryService.save(category);
			for(Article article : category.getArticles()){
				article.setCategories(categories);
				articleService.save(article);
			}
		}
		return "redirect:/article";
	}

	@RequestMapping(value = "/dashlet/{userDashletId}")
	@ResponseBody
	public String browse(@PathVariable Long userDashletId, ModelMap map) {
		UserDashlet userDashlet = userDashletRepository.findOne(userDashletId);
		String dashModel = userDashlet.getDashlet().getModel();
		if(dashModel!=null){
			if("categories".equals(dashModel)){
				map.addAttribute("categories", fromWordPress() ?
					wpTermRepository.findByTaxonomyCategory():
					categoryService.findAll());
			}
			else if("articles".equals(dashModel)){
				map.addAttribute("articles", fromWordPress() ?
						wpPostRepository.findBySlug(userDashlet.getDashlet().getParameter()):
					categoryService.findBySlug(userDashlet.getDashlet().getParameter()).getArticles());
			}
			else if("article".equals(dashModel)){
				map.addAttribute("article", fromWordPress() ?
						wpPostRepository.findByPostName(userDashlet.getDashlet().getParameter()):
					articleService.findBySlug(userDashlet.getDashlet().getParameter()));
			}
			else if("menu".equals(dashModel)){
				if(fromWordPress()){
					List<Category> categories = Lists.newLinkedList();
					List<WpTerm> terms = wpTermRepository.findByTaxonomyCategory();
					for(WpTerm term : terms){
						categories.add(getMappedCategory(term));
					}
					map.addAttribute("menu", categories);
				}
				else{
					map.addAttribute("menu", categoryService.findAll());
				}
			}
			else{
				return "TODO: Freemarkered content "+ userDashlet.getDashlet().getContent();
			}
		}
		map.addAttribute("user", SecurityUtils.getCurrentUser());
		map.addAttribute("security", securityService);
		return TextGenerator.generateText(String.format(
				"[#macro acl attribute][#if security.isAuthorized(attribute)][#nested][/#if][/#macro]%s%s",
				"[#macro url attribute][#local link=security.getAuthorizedUrl(attribute)][#if link!=''][#nested link][/#if][/#macro]",
				userDashlet.getDashlet().getContent()), map);
	}

	private Category getMappedCategory(WpTerm term){
		Category category = new Category();
		BeanUtils.copyProperties(term, category);
		category.setArticles(new HashSet<Article>());
		for(WpPost post : wpPostRepository.findBySlug(term.getSlug())){
			Article article = new Article();
			BeanUtils.copyProperties(post, article);
			category.getArticles().add(article);
		}
		return category;
	}

	private boolean fromWordPress(){
		return cmsProvider.equals("WordPress");
	}

	@Inject
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Inject
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@Inject
	public void setWpTermRepository(WpTermRepository wpTermRepository) {
		this.wpTermRepository = wpTermRepository;
	}

	@Inject
	public void setWpPostRepository(WpPostRepository wpPostRepository) {
		this.wpPostRepository = wpPostRepository;
	}

	@Value(value="${cms.provider}")
	public void setCmsProvider(String cmsProvider) {
		this.cmsProvider = cmsProvider;
	}

	@Inject
	public void setUserDashletRepository(UserDashletRepository userDashletRepository) {
		this.userDashletRepository = userDashletRepository;
	}

	@Inject
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

}
