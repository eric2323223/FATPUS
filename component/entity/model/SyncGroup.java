package component.entity.model;

public class SyncGroup {
	private String name;
	private Interval interval;

	public SyncGroup(String group, Interval interval) {
		this.name = group;
		this.interval = interval;
	}

	public Interval getInterval() {
		return this.interval;
	}

}
