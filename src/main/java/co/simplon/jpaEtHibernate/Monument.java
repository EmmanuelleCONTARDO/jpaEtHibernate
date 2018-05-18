package co.simplon.jpaEtHibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MONUMENTS")
@NamedQueries({
	@NamedQuery(name = "Monument.findAll", query = " SELECT m FROM Monument m ORDER BY m.name, m.id "),
	@NamedQuery(name = "Monument.deleteById", query = " DELETE FROM Monument m WHERE m.id = :id") })

public class Monument {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monument_generator")
	@SequenceGenerator(name = "monument_generator", sequenceName = "monument_sequence", allocationSize = 1)
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city")
	private City city;

	@ManyToMany(mappedBy = "monuments")
	private Set<User> users = new HashSet<User>();

	public Monument(String name) {
		super();
		this.name = name;
	}

	public Monument() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUser(Set<User> user) {
		this.users = user;
	}

	@Override
	public String toString() {
		return "Monument [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
}
