package com.zqdy.dao.po;

/**
 * AllviewId entity. @author MyEclipse Persistence Tools
 */

public class AllviewId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String directorName;
	private String actorName;
	private String imageUrl;
	private Long type;
	private Long sourceId;
	private Long tvSourceId;

	// Constructors

	/** default constructor */
	public AllviewId() {
	}

	/** minimal constructor */
	public AllviewId(Integer id, String directorName, String actorName,
			Long type, Long tvSourceId) {
		this.id = id;
		this.directorName = directorName;
		this.actorName = actorName;
		this.type = type;
		this.tvSourceId = tvSourceId;
	}

	/** full constructor */
	public AllviewId(Integer id, String name, String directorName,
			String actorName, String imageUrl, Long type, Long sourceId,
			Long tvSourceId) {
		this.id = id;
		this.name = name;
		this.directorName = directorName;
		this.actorName = actorName;
		this.imageUrl = imageUrl;
		this.type = type;
		this.sourceId = sourceId;
		this.tvSourceId = tvSourceId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectorName() {
		return this.directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getActorName() {
		return this.actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getTvSourceId() {
		return this.tvSourceId;
	}

	public void setTvSourceId(Long tvSourceId) {
		this.tvSourceId = tvSourceId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AllviewId))
			return false;
		AllviewId castOther = (AllviewId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getDirectorName() == castOther.getDirectorName()) || (this
						.getDirectorName() != null
						&& castOther.getDirectorName() != null && this
						.getDirectorName().equals(castOther.getDirectorName())))
				&& ((this.getActorName() == castOther.getActorName()) || (this
						.getActorName() != null
						&& castOther.getActorName() != null && this
						.getActorName().equals(castOther.getActorName())))
				&& ((this.getImageUrl() == castOther.getImageUrl()) || (this
						.getImageUrl() != null
						&& castOther.getImageUrl() != null && this
						.getImageUrl().equals(castOther.getImageUrl())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getSourceId() == castOther.getSourceId()) || (this
						.getSourceId() != null
						&& castOther.getSourceId() != null && this
						.getSourceId().equals(castOther.getSourceId())))
				&& ((this.getTvSourceId() == castOther.getTvSourceId()) || (this
						.getTvSourceId() != null
						&& castOther.getTvSourceId() != null && this
						.getTvSourceId().equals(castOther.getTvSourceId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37
				* result
				+ (getDirectorName() == null ? 0 : this.getDirectorName()
						.hashCode());
		result = 37 * result
				+ (getActorName() == null ? 0 : this.getActorName().hashCode());
		result = 37 * result
				+ (getImageUrl() == null ? 0 : this.getImageUrl().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getSourceId() == null ? 0 : this.getSourceId().hashCode());
		result = 37
				* result
				+ (getTvSourceId() == null ? 0 : this.getTvSourceId()
						.hashCode());
		return result;
	}

}