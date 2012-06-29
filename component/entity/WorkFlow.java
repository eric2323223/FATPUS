package component.entity;

import component.entity.model.AbstractModel;
import component.entity.model.IWizardEntity;
import component.entity.model.VerificationCriteria;
import component.entity.model.Widget;

public class WorkFlow implements IWizardEntity, IEditable{

	public static final String SP_CLIENT_INIT = "Client-initiated";
	public static final String SP_SERVER_INIT = "Server-initiated";
	public static final String SP_ACTIVATE = "Activate";
	public static final String SP_CREDENTIAL_REQUEST = "Credential Request";
	String project;
	String name;
	String option;
	String version;

	String mbo;
	String parametervalue;
	String objectQuery;
	String from;
	String subject;
	String to;
	String cc;
	String body;
	String fromMatchingRule;
	String subjectMatchingRule;
	String toMatchingRule;
	String ccMatchingRule;
	String bodyMatchingRule;
	String parameterMatchingRule;
	private String parentFolder;
	
	//ff10.18 add>>>>>>>>>>>>
	String received;
	
	VerificationCriteria verifySubject;
	VerificationCriteria verifyBody;
	VerificationCriteria verifyTo;
	VerificationCriteria verifyCc;
	VerificationCriteria verifyFrom;
	
	//ff12.8>>>>>>>>>>>>>>>>>>>>>>>>>
	VerificationCriteria verifyParameterList;
	VerificationCriteria verifyParameterValue;
	public VerificationCriteria verifyParameterList(){
		return this.verifyParameterList;
	}
	
	public WorkFlow verifyParameterList(String str, boolean b){
		this.verifyParameterList = new VerificationCriteria(str, b);
		return this;
	}
	
	public VerificationCriteria verifyParameterValue(){
		return this.verifyParameterValue;
	}
	
	public WorkFlow verifyParameterValue(String str, boolean b){
		this.verifyParameterValue = new VerificationCriteria(str, b);
		return this;
	}
	//ff12.8<<<<<<<<<<<<<<<<<<<<<<<
	
	public VerificationCriteria verifySubject(){
		return this.verifySubject;
	}
	
	public WorkFlow verifySubject(String str, boolean b){
		this.verifySubject = new VerificationCriteria(str, b);
		return this;
	}
	
	public VerificationCriteria verifyBody(){
		return this.verifyBody;
	}
	
	public WorkFlow verifyBody(String str, boolean b){
		this.verifyBody = new VerificationCriteria(str, b);
		return this;
	}
	
	public VerificationCriteria verifyCc(){
		return this.verifyCc;
	}
	
	public WorkFlow verifyCc(String str, boolean b){
		this.verifyCc = new VerificationCriteria(str, b);
		return this;
	}
	public VerificationCriteria verifyTo(){
		return this.verifyTo;
	}
	
	public WorkFlow verifyTo(String str, boolean b){
		this.verifyTo = new VerificationCriteria(str, b);
		return this;
	}
	public VerificationCriteria verifyFrom(){
		return this.verifyFrom;
	}
	
	public WorkFlow verifyFrom(String str, boolean b){
		this.verifyFrom = new VerificationCriteria(str, b);
		return this;
	}
	//ff10.18<<<<<<<<<
	
	public String getParentFolder(){
		return this.parentFolder;
	}
	
	public String getParameterValue() {
		return parametervalue;
	}

	public String getTo() {
		return to;
	}
	
	public String getProject(){
		return this.project;
	}
	
	public WorkFlow project(String str){
		this.project = str;
		return this;
	}
	
	public WorkFlow to(String str){
		this.to = str;
		return this;
	}

	public String getCc() {
		return cc;
	}
	
	public WorkFlow cc(String str){
		this.cc = str;
		return this;
	}
	
	public String getBody() {
		return body;
	}
	
	public WorkFlow body(String str){
		this.body = str;
		return this;
	}

	public String getFromMatchingRule() {
		return fromMatchingRule;
	}
	
	public WorkFlow fromMatchingRule(String str){
		this.fromMatchingRule = str;
		return this;
	}

	public String getSubjectMatchingRule() {
		return subjectMatchingRule;
	}
	
	public WorkFlow subjectMatchingRule(String str){
		this.subjectMatchingRule = str;
		return this;
	}

	public String getToMatchingRule() {
		return toMatchingRule;
	}
	
	public WorkFlow toMatchingRule(String str){
		this.toMatchingRule = str;
		return this;
	}

	public String getCcMatchingRule() {
		return ccMatchingRule;
	}
	
	public WorkFlow ccMatchingRule(String str){
		this.ccMatchingRule = str;
		return this;
	}

	public String getBodyMatchingRule() {
		return bodyMatchingRule;
	}
	
	public WorkFlow bodyMatchingRule(String str){
		this.bodyMatchingRule = str;
		return this;
	}

	public WorkFlow setParameterValue(String parametervalue) {
		this.parametervalue = parametervalue;
		return this;
	}
	
	public WorkFlow mbo(String str){
		mbo = str;
		return this;
	}
	
	public String getMbo(){
		return mbo;
	}
	
	public WorkFlow objectQuery(String str){
		objectQuery = str;
		return this;
	}
	
	public String getObjectQuery(){
		return objectQuery;
	}
	
	public WorkFlow from(String str){
		from = str;
		return this;
	}
	
	public String getFrom(){
		return from;
	}
	
	public WorkFlow subject(String str){
		subject = str;
		return this;
	}
	
	public String getSubject(){
		return subject;
	}
	
	
	//ff<<<<<<<<<<<<<<<////////////////////////////////////
	
	public WorkFlow name(String str){
		name = str;
		return this;
	}
	
	public String getName(){
		return name;
	}
	
	public String getOption(){
		return this.option;
	}
	
	public WorkFlow option(String str){
		if(option==null){
			option = str;
		}else{
			option = option+","+str;
		}
		return this;
	}
	
	public WorkFlow startParameter(String str){
		project = str;
		return this;
	}

	@Override
	public String sp() {
		return project;
	}

	public String getVersion(){
		return this.version;
	}
	
	public WorkFlow version(String string) {
		this.version = string;
		return this;
	}

	@Override
	public void openInPropertiesView() {
		// TODO Auto-generated method stub
		
	}

	public WorkFlow parentFolder(String string) {
		this.parentFolder=string;
		return this;
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	//ff>>>>>10.18
	public WorkFlow received(String string){
		this.received = string;
		return this;
	}
	
	public String getReceived(){
		return this.received;
	}

	public WorkFlow parameterMatchingRule(String string) {
		if(this.parameterMatchingRule==null){
			this.parameterMatchingRule = string;
		}else{
			this.parameterMatchingRule = this.parameterMatchingRule +":"+string;
		}
		return this;
	}
	
	public String getParameterMatchingRule(){
		return this.parameterMatchingRule;
	}




}
