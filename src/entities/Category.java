package entities;

public class Category {
	private int id;
	private String name,
		description;
        private Boolean is_active;
        private int order;
	
	public Category() {}
	
	public Category(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
          
	}

    public Category(String name, String description, int order, Boolean is_active) {
        this.name = name;
        this.description = description;
        this.is_active = is_active;
        this.order = order;
    }
        
        
        public Category(int id, String name, String description, Boolean is_active) {
		this.id = id;
		this.name = name;
		this.description = description;
                this.is_active = is_active;
	}
        
        public Category(int id, String name, String description, int order, Boolean is_active) {
		this.id = id;
		this.name = name;
		this.description = description;
                this.is_active = is_active;
                this.order = order;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
        
        public Boolean getIs_active(){
                return is_active;
        }
        
        public void setIs_active(Boolean is_active){
                this.is_active = is_active;
        
        }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
