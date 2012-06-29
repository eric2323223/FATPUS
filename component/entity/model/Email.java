package component.entity.model;

public class Email extends AbstractModel implements IWizardEntity{
	String unwiredServer;
	String from;
	String to;
	String cc;
	String bcc;
	String subject;
	String body;
	String selectFrom;
	String selectTo;
	String selectCc;
	String selectBcc;
	
	VerificationCriteria verifySelectFrom;
	VerificationCriteria verifySelectTo;
	VerificationCriteria verifySelectCc;
	VerificationCriteria verifySelectBcc;
	VerificationCriteria verifyUnwiredServer;
	
	VerificationCriteria verifyMessage;
	
	public String getUnwiredServer(){
		return unwiredServer;
	}

	
	public Email selectFrom(String str){
		this.selectFrom = str;
		return this;
	}
	
	public Email selectTo(String str){
		this.selectTo = str;
		return this;
	}
	
	public Email selectCc(String str){
		this.selectCc = str;
		return this;
	}
	
	public Email selectBcc(String str){
		this.selectBcc = str;
		return this;
	}
	
	public String getSelectFrom() {
		return selectFrom;
	}

	public String getSelectTo() {
		return selectTo;
	}

	public String getSelectCc() {
		return selectCc;
	}

	public String getSelectBcc() {
		return selectBcc;
	}

	public String getTo() {
		return this.to;
	}

	public String getFrom() {
		return from;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	
	public String getSubject() {
		return subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public Email to(String str){
		this.to = str;
		return this;
	}

	public Email from(String from) {
		this.from = from;
		return this;
	}

	public Email cc(String cc) {
		this.cc = cc;
		return this;
	}

	public Email bcc(String bcc) {
		this.bcc = bcc;
		return this;
	}

	public Email subject(String subject) {
		this.subject = subject;
		return this;
	}

	public Email body(String body) {
		this.body = body;
		return this;
	}

	public Email unwiredServer(String unwiredServer) {
		this.unwiredServer = unwiredServer;
		return this;
	}

	public Email verifySelectFrom(String str, boolean b){
		this.verifySelectFrom = new VerificationCriteria(str,b);
		return this;
	}
	
	public VerificationCriteria verifySelectFrom(){
		return this.verifySelectFrom;
	}
	
	public Email verifySelectTo(String str, boolean b){
		this.verifySelectTo = new VerificationCriteria(str,b);
		return this;
	}
	
	public VerificationCriteria verifySelectTo(){
		return this.verifySelectTo;
	}
	
	public Email verifySelectCc(String str, boolean b){
		this.verifySelectCc = new VerificationCriteria(str,b);
		return this;
	}
	
	public VerificationCriteria verifySelectCc(){
		return this.verifySelectCc;
	}
	
	public Email verifySelectBcc(String str, boolean b){
		this.verifySelectBcc = new VerificationCriteria(str,b);
		return this;
	}
	
	public VerificationCriteria verifySelectBcc(){
		return this.verifySelectBcc;
	}
	
	public Email verifyMessage(String str, boolean b){
		this.verifyMessage = new VerificationCriteria(str,b);
		return this;
	}
	
	public VerificationCriteria verifyMessage(){
		return this.verifyMessage;
	}
	
	public Email verifyUnwiredServer(String str, boolean b){
		this.verifyUnwiredServer = new VerificationCriteria(str, b);
		return this;
	}
	
	public VerificationCriteria verifyUnwiredServer(){
		return this.verifyUnwiredServer;
	}
	
	@Override
	public String sp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
