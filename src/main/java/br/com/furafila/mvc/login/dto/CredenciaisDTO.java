package br.com.furafila.mvc.login.dto;

public class CredenciaisDTO {

	private Long id;
	private String username;
	private String password;
	private Long levelId;
	private String initials;
	private String level;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

//	@JsonProperty("id")
//	private Long id;
//	
//	@JsonProperty("username")
//	private String usuario;
//	
//	@JsonProperty("password")
//	private String senha;
//	
//	@JsonProperty("levelId")
//	private Long idPermissao;
//	
//	@JsonProperty("initials")
//	private String siglaPermissao;
//	
//	@JsonProperty("level")
//	private String permissao;
//	

}