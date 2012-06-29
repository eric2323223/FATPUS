package component.entity.model;

public class Screen extends AbstractModel{
		String name;
		String key;
		String backGroundColor;
		String action;

		public String getName() {
			return this.name;
		}
		
		public String getKey() {
			return this.key;
		}
		
		public String getBackGroundColor() {
			return this.backGroundColor;
		}
		
		public String getAction() {
			return this.action;
		}
		
		public Screen name(String name){
			this.name = name;
			return this;
		}
		
		public Screen key(String key) {
			this.key = key;
			return this;
		}
		
		public Screen backGroundColor(String backGroundColor) {
			this.backGroundColor = backGroundColor;
			return this;
		}
		
		public Screen action(String action) {
			this.action = action;
			return this;
		}
}
