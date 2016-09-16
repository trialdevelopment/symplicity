package symplicity.voting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries ({
	@NamedQuery(name="Fruit.findByName", query="SELECT m FROM Fruit m WHERE m.title = :pTitle")
})
public class Fruit {

	@Id
	@GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy="uuid2")
	private String fruitID;
	
	@Column(unique=true)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String poster;
	
	private int vote;
	
	
	public String getFruitID() {
		return fruitID;
	}
	public void setFruitID(String fruitID) {
		this.fruitID = fruitID;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
}
