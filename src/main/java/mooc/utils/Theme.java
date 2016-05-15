package mooc.utils;

public class Theme {

	private int id;

	private String displayName;

	private String name;

	public Theme() {
	}

	public Theme(final int id, final String displayName, final String name) {
		this.id = id;
		this.displayName = displayName;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}