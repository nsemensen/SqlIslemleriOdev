package classes;

public class Position {
	private Long id;
	private String name;
	private Integer durum;
	
	public Position(Long id, String name, Integer durum) {
		super();
		this.id = id;
		this.name = name;
		this.durum = durum;
	}
	public Position(String name) {
		super();
		this.id =null;
		this.name = name;
		this.durum=1;
	}
	public Position() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDurum() {
		return durum;
	}
	public void setDurum(Integer durum) {
		this.durum = durum;
	}
	
	@Override
	public String toString() {
		return "UNVAN ID=" + id + ",  Adı=" + name ;
	}
	
}
