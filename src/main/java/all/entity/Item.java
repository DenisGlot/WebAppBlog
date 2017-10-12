package all.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.jsoup.Jsoup;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 1000)
	private String title;

	@Lob
	@Type(type = "org.hibernate.type.MaterializedClobType")
	@Column(length = 1000000)
	private String description;

	@Column(length = 1000)
	private String link;

	@Column(name = "published_date")
	private Date publishedDate;

	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;
	
	@Lob
	@Type(type = "org.hibernate.type.MaterializedClobType")
	@Column(length = 1000000)
	private String textHtml;

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Integer getId() {
		return id;
	}

	public String getTextHtml() {
		textHtml = Jsoup.parse(description).text();
		return textHtml;
	}

	public void setTextHtml(String textHtml) {
		this.textHtml = textHtml;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
