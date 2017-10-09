package all.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import all.entity.Blog;
import all.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item> findByBlog(Blog blog,Pageable pageable);
	
	Item findByBlogAndLink(Blog blog, String link);

}
